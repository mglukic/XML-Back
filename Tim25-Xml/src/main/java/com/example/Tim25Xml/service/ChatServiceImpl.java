package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Message;
import com.example.Tim25Xml.repository.ChatRepository;
import com.example.Tim25Xml.repository.MessageRepository;
import com.example.Tim25Xml.soap.PostChat;
import com.example.Tim25Xml.soap.PostMessage;
import com.example.Tim25Xml.xsd.GetChatByAgentMailResponse;
import com.example.Tim25Xml.xsd.GetMessageByChatIdResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.Comparator;

@Service
public class ChatServiceImpl implements ChatService{
    final static Logger logger = LoggerFactory.getLogger(VoziloServiceImpl.class);

    @Autowired
     PostChat postChat;

    @Autowired
     PostMessage postMessage;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public ArrayList<Chat> findAllByAuthorEmail(String email) {
        ArrayList<Chat> chats = getChatsFromMicroservices(email);
        logger.debug("#####VRACENI CHATOVI PO EMAILU: " + chats);
        return chats;
    }

    private ArrayList<Chat> getChatsFromMicroservices(String email) {
        chatRepository.deleteAll();
        GetChatByAgentMailResponse getChatByAgentMailResponse = postChat.getChatByAgentMailResponse(email);
        ArrayList<Chat> ret = new ArrayList<Chat>();

        if (getChatByAgentMailResponse==null) {
            logger.debug("***ERROR ChatService > sendChatToMicroServices > postChat > returned NULL!");
        } else {
            logger.debug("***ChatService > sendChatToMicroServices > postChat > uspesno poslat chat!");
        }

        for(com.example.Tim25Xml.xsd.Chat chatXSD : getChatByAgentMailResponse.getChatovi()){
            Chat chatModel = new Chat();
            chatModel.setId(chatXSD.getId());
            chatModel.setUser1(chatXSD.getUser1());
            chatModel.setUser2(chatXSD.getUser2());
            ret.add(chatModel);
            chatRepository.save(chatModel);
            logger.debug("***ChatService > getChatsFromMicroservices > getChatByAgentMailResponse > sacuvan chatModel");
        }

        return ret;
    }

    @Override
    public Chat getById(Long id) {
        return chatRepository.findById(id).orElseGet(null);
    }

    @Override
    public ArrayList<Message> findAllMessagesByChatId(Long id) {
        ArrayList<Message> messages = getMessagesByChatId(id);

        ArrayList<Message> ret = new ArrayList<>();
        for (Message m: messageRepository.findAll()) {
            if(m.getChatId() == id){
                ret.add(m);
            }
        }
        //nadam se da radi
        ret.sort(Comparator.comparing(o -> o.getTimestamp()));
        logger.debug("#####VRACENE PORUKE PO CHAT ID: " + ret);
        return ret;
    }

    private ArrayList<Message> getMessagesByChatId(Long id) {
        messageRepository.deleteAll();
        GetMessageByChatIdResponse getMessageByChatIdResponse = postMessage.getMessageByChatIdResponse(id);
        ArrayList<Message> ret = new ArrayList<Message>();

        if (getMessageByChatIdResponse==null) {
            logger.debug("***ERROR ChatService > getMessageByChatIdResponse > postMessage > returned NULL!");
        } else {
            logger.debug("***ChatService > getMessageByChatIdResponse > postMessage > uspesno poslat chat!");
        }

        for(com.example.Tim25Xml.xsd.Message messageXSD : getMessageByChatIdResponse.getMessages()){
            Message messageModel = new Message();

            messageModel.setChatId(messageXSD.getChatId());
            messageModel.setContent(messageXSD.getContent());
            messageModel.setSenderEmail(messageXSD.getSenderEmail());
            messageModel.setTimestamp(messageXSD.getTimestamp().toGregorianCalendar().toZonedDateTime().toLocalDateTime());

            ret.add(messageModel);
            messageRepository.save(messageModel);
            logger.debug("***ChatService > getChatsFromMicroservices > getChatByAgentMailResponse > sacuvan chatModel");
        }

        return ret;
    }





    @Override
    public Message kreirajPoruku(Message message) throws DatatypeConfigurationException {
        Message ret = new Message();
        ret.setChatId(message.getChatId());
        ret.setContent(message.getContent());
        ret.setSenderEmail(message.getSenderEmail());
        ret.setTimestamp(message.getTimestamp());

        ret = messageRepository.save(ret);

        sendMessageToMicroservices(message);

        return ret;
    }

    @Override
    public Chat kreirajCet(Chat chat) throws DatatypeConfigurationException {
        Chat ret = new Chat();
        ret.setUser1(chat.getUser1());
        ret.setUser2(chat.getUser2());

        ret = chatRepository.save(ret);
        sendChatToMicroservices(chat);


        return ret;
    }

    private void sendChatToMicroservices(Chat chat) throws DatatypeConfigurationException {
        if (postChat.postChat(chat) == null) {
            logger.info("***ERROR ChatService > sendChatToMicroServices > postChat > returned NULL!");
        } else {
            logger.info("***ChatService > sendChatToMicroServices > postChat > uspesno poslat chat!");
        }
    }

    private void sendMessageToMicroservices(Message msg) throws DatatypeConfigurationException {
        if (postMessage.postMessage(msg) == null) {
            logger.info("***ERROR ChatService > sendMessageToMicroservices > postMessage > returned NULL!");
        } else {
            logger.info("***ChatService > sendMessageToMicroservices > postMessage > uspesno poslata message!");
        }
    }


}

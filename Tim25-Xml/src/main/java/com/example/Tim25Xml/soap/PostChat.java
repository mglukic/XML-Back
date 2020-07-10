package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.xsd.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;

public class PostChat  extends WebServiceGatewaySupport {


    public PostChatResponse postChat(Chat chatModel) throws DatatypeConfigurationException {

        com.example.Tim25Xml.xsd.Chat chatXSD = new com.example.Tim25Xml.xsd.Chat();

        chatXSD.setUser1(chatModel.getUser1());
        chatXSD.setUser2(chatModel.getUser2());
        chatXSD.setId(chatModel.getId());

        PostChatRequest request = new PostChatRequest();
        request.setChat(chatXSD);

        PostChatResponse response = null;


        try {
            response = (PostChatResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/user/ws/postChatResponse", request,
                    new SoapActionCallback("http://example.com/adninservice/xsd/PostChatRequest"));
        } catch (Exception e) {
            logger.info("***ERROR ChatClient > greska prilikom slanja!");
        }
        return response;
    }

    public GetChatByAgentMailResponse getChatByAgentMailResponse(String email) {
        GetChatByAgentMailRequest request = new GetChatByAgentMailRequest();
        request.setMail(email);

        logger.info("+-*GetChatByAgentMailResponse u agentskoj.. Pokusava da posalje web servisu!!");

        GetChatByAgentMailResponse response = null;
        try {
            response = (GetChatByAgentMailResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/user/ws/soap", request,
                    new SoapActionCallback("http://example.com/adninservice/xsd/GetChatByAgentMailRequest"));
        } catch (Exception e) {
            logger.info("***ERROR getChatByAgentMailResponse > greska prilikom slanja! Prilikom Slanja GetMailPodnosiocaRquest!!");
        }
        return response;
    }






}

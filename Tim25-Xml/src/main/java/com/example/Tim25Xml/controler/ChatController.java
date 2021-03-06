package com.example.Tim25Xml.controler;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Message;
import com.example.Tim25Xml.service.ChatService;
import com.example.Tim25Xml.soap.GetAgentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/chat")
public class ChatController {


    @Autowired
    ChatService chatService;

    @Autowired
    private GetAgentClient getAgentClient;

    private String mailAgenta=null;

    @GetMapping(value = "/autor/{email}")
    public Collection<Chat> vratiChatovePoKorisniku(@PathVariable("email") String email) {

        Collection<Chat> chats = chatService.findAllByAuthorEmail(email);

        return chats;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Chat> vratiChat(@PathVariable("id") Long id) throws Exception {

        Chat chat = chatService.getById(id);

        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @GetMapping(value = "/message/{id}")
    public Collection<Message> vratiPorukePoChatu(@PathVariable("id") Long id) {

        Collection<Message> messages = chatService.findAllMessagesByChatId(id);

        return messages;
    }

    @PostMapping( value = "/message")
    public Message posaljiPorukuNaCet(@RequestBody Message message) throws Exception {

        return chatService.kreirajPoruku(message);
    }

    @PostMapping( value = "/{idPodnosilac}")
    public Chat kreirajChat(@PathVariable("idPodnosilac") Long  idPodnosilac) throws Exception {

        return chatService.kreirajCet(idPodnosilac);
    }

    @GetMapping(value = "/ulogovan")
    public String vratiAgenta() {

       mailAgenta=getAgentClient.getMailUlogovanogAgentaResponse().getVraceniMejl();

        return mailAgenta;
    }

}

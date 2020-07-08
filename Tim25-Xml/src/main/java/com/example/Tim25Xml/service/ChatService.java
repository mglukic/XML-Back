package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Message;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;

public interface ChatService {

    public ArrayList<Chat> findAllByAuthorEmail(String email);
    public Chat getById(Long id);
    public ArrayList<Message> findAllMessagesByChatId(Long id);
    public Message kreirajPoruku(Message message) throws DatatypeConfigurationException;
    public Chat kreirajCet(Chat chat) throws DatatypeConfigurationException;
}

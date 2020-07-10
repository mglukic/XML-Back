package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Message;
import com.example.Tim25Xml.xsd.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class PostMessage extends WebServiceGatewaySupport {

    public PostMessageResponse postMessage(Message messageModel) throws DatatypeConfigurationException {

        com.example.Tim25Xml.xsd.Message messageXSD = new com.example.Tim25Xml.xsd.Message();

        //valjda je dobro?
        LocalDateTime dateTimestamp = messageModel.getTimestamp();
        GregorianCalendar gcalTimestamp = GregorianCalendar.from(dateTimestamp.atZone(ZoneId.systemDefault()));
        XMLGregorianCalendar xcalTimestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalTimestamp);


        messageXSD.setContent(messageModel.getContent());
        messageXSD.setSenderEmail(messageModel.getSenderEmail());
        messageXSD.setChatId(messageModel.getChatId());
        messageXSD.setTimestamp(xcalTimestamp);


        PostMessageRequest request = new PostMessageRequest();
        request.setMessage(messageXSD);

        PostMessageResponse response = null;


        try {
            response = (PostMessageResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/user/ws/soap", request,
                    new SoapActionCallback("http://example.com/adninservice/xsd/PostMessageRequest"));
        } catch (Exception e) {
            logger.info("***ERROR PostMessage > greska prilikom slanja!");
        }
        return response;
    }

    public GetMessageByChatIdResponse getMessageByChatIdResponse(Long id) {
        GetMessageByChatIdRequest request = new GetMessageByChatIdRequest();
        request.setChatId(id);

        logger.info("+-*GetMessageByChatIdResponse u agentskoj.. Pokusava da posalje web servisu!!");

        GetMessageByChatIdResponse response = null;
        try {
            response = (GetMessageByChatIdResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/user/ws/soap", request,
                    new SoapActionCallback("http://example.com/adninservice/xsd/GetMessageByChatIdRequest"));
        } catch (Exception e) {
            logger.info("***ERROR GetMessageByChatIdResponse > greska prilikom slanja! Prilikom Slanja GetMailPodnosiocaRquest!!");
        }
        return response;
    }


}

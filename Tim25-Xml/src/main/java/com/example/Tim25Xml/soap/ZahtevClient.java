package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.xsd.GetZahteveByIzdavacMailRequset;
import com.example.Tim25Xml.xsd.GetZahteveByIzdavacMailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ZahtevClient extends WebServiceGatewaySupport {

    final static Logger logger = LoggerFactory.getLogger(ZahtevClient.class);

    public GetZahteveByIzdavacMailResponse getZahtevePoMejlu(String izdavacMail) {
        GetZahteveByIzdavacMailResponse response = null;

        GetZahteveByIzdavacMailRequset requset = new GetZahteveByIzdavacMailRequset();
        requset.setIzdavacMail(izdavacMail);

        logger.info("*-+Get Zahteve preko web servisa(Trenutno u agentskoj)... Pokusava da primi zahteve po MEJLU:: " + izdavacMail);

        try {
            response = (GetZahteveByIzdavacMailResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", requset,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/GetZahteveByIzdavacMailRequset"));
        } catch (Exception e) {
            logger.info("***ERROR ZahtevClient > greska prilikom primanja zahteva!");
        }

        logger.info("*-+Get Zahteve preko web servisa(Trenutno u agentskoj)... RESPONSE:: " + response);

        return response;
    }


}

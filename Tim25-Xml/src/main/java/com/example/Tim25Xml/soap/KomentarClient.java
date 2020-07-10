package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.xsd.GetKomentareByIdVozilaRequest;
import com.example.Tim25Xml.xsd.GetKomentareByIdVozilaResponse;
import com.example.Tim25Xml.xsd.PostVoziloResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class KomentarClient extends WebServiceGatewaySupport {

    final static Logger logger = LoggerFactory.getLogger(KomentarClient.class);

    public GetKomentareByIdVozilaResponse getKomentareByIdVozila(Long idVozila) {
        GetKomentareByIdVozilaResponse response = null;

        GetKomentareByIdVozilaRequest request = new GetKomentareByIdVozilaRequest();
        request.setIdVozila(idVozila);

        logger.info("*-+Get Komentare preko web servisa(Trenutno u agentskoj)... \nKomentarClient >> getKomentareByIdVozila():: " + idVozila);

        try {
            response = (GetKomentareByIdVozilaResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/GetKomentareByIdVozilaRequest"));
        } catch (Exception e) {
            logger.info("***ERROR KomentarClient > greska prilikom primanja komentara!");
        }

        return response;
    }



}

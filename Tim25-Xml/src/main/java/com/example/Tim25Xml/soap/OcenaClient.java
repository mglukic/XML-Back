package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.xsd.GetKomentareByIdVozilaRequest;
import com.example.Tim25Xml.xsd.GetKomentareByIdVozilaResponse;
import com.example.Tim25Xml.xsd.GetOceneByIdVozilaRequest;
import com.example.Tim25Xml.xsd.GetOceneByIdVozilaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OcenaClient extends WebServiceGatewaySupport {

    final static Logger logger = LoggerFactory.getLogger(OcenaClient.class);

    public GetOceneByIdVozilaResponse getOceneByIdVozila(Long idVozila) {
        GetOceneByIdVozilaResponse response = null;

        GetOceneByIdVozilaRequest request = new GetOceneByIdVozilaRequest();
        request.setIdVozila(idVozila);

        logger.info("*-+Get Ocene preko web servisa(Trenutno u agentskoj)... \nOcenaClient >> getOceneByIdVozila():: " + idVozila);

        try {
            response = (GetOceneByIdVozilaResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/GetOceneByIdVozilaRequest"));
        } catch (Exception e) {
            logger.info("***ERROR OcenaClient > greska prilikom primanja ocene!!");
        }

        return response;
    }
}

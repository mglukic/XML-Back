package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.xsd.GetMailPodnosiocaRequest;
import com.example.Tim25Xml.xsd.GetMailPodnosiocaResponse;
import com.example.Tim25Xml.xsd.PostProbaRequest;
import com.example.Tim25Xml.xsd.PostProbaResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class GetKorisnikEmailById  extends WebServiceGatewaySupport {

    public GetMailPodnosiocaResponse postGetKorisnikEmailById(Long idPodnosilac) {
        GetMailPodnosiocaRequest request = new GetMailPodnosiocaRequest();
        request.setIdPodnosilac(idPodnosilac);

        logger.info("+-*PostProbaResponse u agentskoj.. Pokusava da posalje web servisu!!");

        GetMailPodnosiocaResponse response = null;
        try {
            response = (GetMailPodnosiocaResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/GetMailPodnosiocaRequest"));
        } catch (Exception e) {
            logger.info("***ERROR VoziloClient > greska prilikom slanja! Prilikom Slanja GetMailPodnosiocaRquest!!");
        }
        return response;
    }


}

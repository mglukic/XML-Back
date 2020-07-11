package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.xsd.*;
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

    public PostKomentarResponse postKomentar(Komentar komentar) {
        com.example.Tim25Xml.xsd.Komentar komentarXSD = new com.example.Tim25Xml.xsd.Komentar();

        komentarXSD.setIdVozila(komentar.getIdVozila());
        komentarXSD.setKomentar(komentar.getKomentar());
        komentarXSD.setStanje(StanjeKomentara.ODOBREN);

        PostKomentarRequest request = new PostKomentarRequest();
        request.setKomentar(komentarXSD);

        PostKomentarResponse response = null;

        logger.info("*-+Post Komentar preko web servisa(Trenutno u agentskoj)... Pokusava da posalje web servisu!!\nSalje komentar:: " + komentar.toString());

        try {
            response = (PostKomentarResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/PostKomentarRequest"));
        } catch (Exception e) {
            logger.info("***ERROR KomentarClient > greska prilikom slanja komentara!!");
        }

        return response;

    }



}

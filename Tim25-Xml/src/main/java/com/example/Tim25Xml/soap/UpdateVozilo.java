package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.xsd.UpdateVoziloRequset;
import com.example.Tim25Xml.xsd.UpdateVoziloResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;

public class UpdateVozilo extends WebServiceGatewaySupport {

    final static Logger logger = LoggerFactory.getLogger(UpdateVozilo.class);

    public UpdateVoziloResponse updateVoziloResponse(Long idVozila, double kilometraza) throws DatatypeConfigurationException {

        UpdateVoziloRequset request = new UpdateVoziloRequset();
        request.setIdVozilo(idVozila);
        request.setPredjenaKilometraza(kilometraza);

        logger.info("+-*Update vozila u agentskoj.. Pokusava da posalje web servisu!!"+request.getIdVozilo(),request.getPredjenaKilometraza());

        UpdateVoziloResponse response = null;
        try {
            response = (UpdateVoziloResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/UpdateVoziloRequset"));
        } catch (Exception e) {
            logger.info("***ERROR UpdateVoziloClient > greska prilikom slanja!");
        }
        return response;
    }
}


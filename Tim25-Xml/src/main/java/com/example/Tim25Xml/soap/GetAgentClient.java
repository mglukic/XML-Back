package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.xsd.GetMailUlogovanogAgentaRequest;
import com.example.Tim25Xml.xsd.GetMailUlogovanogAgentaResponse;
import com.example.Tim25Xml.xsd.GetZahteveByIzdavacMailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class GetAgentClient extends WebServiceGatewaySupport {
    final static Logger logger = LoggerFactory.getLogger(GetAgentClient.class);

    public GetMailUlogovanogAgentaResponse getMailUlogovanogAgentaResponse() {
        GetMailUlogovanogAgentaResponse response = null;

        GetMailUlogovanogAgentaRequest request = new GetMailUlogovanogAgentaRequest();
        request.setGet("asd");

        logger.info("*-+Pokusava u agentskoj da dobije mejl ulogovanog:: ");

        try {
            response = (GetMailUlogovanogAgentaResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/soap", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/GetMailUlogovanogAgentaRequest"));
        } catch (Exception e) {
            logger.info("***ERROR GetAgentClient > greska prilikom preuzimanja mejla ulogovanog!");
        }

        return response;

    }

}

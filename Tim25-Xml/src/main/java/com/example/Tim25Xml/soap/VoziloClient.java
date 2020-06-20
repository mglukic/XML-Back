package com.example.Tim25Xml.soap;

import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.xsd.PostVoziloRequest;
import com.example.Tim25Xml.xsd.PostVoziloResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class VoziloClient extends WebServiceGatewaySupport {

    final static Logger logger = LoggerFactory.getLogger(VoziloClient.class);

    public PostVoziloResponse postVozilo(Vozilo voziloToXSD) throws DatatypeConfigurationException {
        com.example.Tim25Xml.xsd.Vozilo vozilo = new com.example.Tim25Xml.xsd.Vozilo();

        LocalDate dateOd = voziloToXSD.getZauzetoOd();
        LocalDate dateDo = voziloToXSD.getZauzetoDo();

        GregorianCalendar gcalOd = GregorianCalendar.from(dateOd.atStartOfDay(ZoneId.systemDefault()));
        GregorianCalendar gcalDo = GregorianCalendar.from(dateDo.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xcalOd = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalOd);
        XMLGregorianCalendar xcalDo = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalDo);

        vozilo.setVaziOd(xcalOd);
        vozilo.setVaziDo(xcalDo);
        vozilo.setMesto(vozilo.getMesto());
        vozilo.setMarkaAutomobila(voziloToXSD.getMarkaAutomobila());
        vozilo.setModelAutomobila(voziloToXSD.getModelAutomobila());
        vozilo.setTipMenjaca(voziloToXSD.getMenjac());
        vozilo.setTipGoriva(voziloToXSD.getGorivo());
        vozilo.setKlasaVozila(voziloToXSD.getKlasaAutomobila());
        vozilo.setCenovnikId("1"); //TODO: Vamo promeniti cenovnik id
        vozilo.setPredjenaKilometraza(voziloToXSD.getPredjenaKilometraza());
        vozilo.setOgranicenaKilometraza(voziloToXSD.getPlaniranoZaPreci()); //TODO: vamo proveriti dal promeniti planirano
        vozilo.setCDWProtection(voziloToXSD.isCwd());
        vozilo.setBrojSedistaDeca(voziloToXSD.getBrojSedistaZaDecu());
        vozilo.setIznajmljivacId(1); //TODO: Vamo promeniti ko iznamljuje

        PostVoziloRequest request = new PostVoziloRequest();
        request.setVozilo(vozilo);

        logger.info("Post Vozila preko web servisa... ");

        PostVoziloResponse response = null;

        try {
            response = (PostVoziloResponse) getWebServiceTemplate().marshalSendAndReceive(
                    "http://localhost:8080/car/ws/postVozilo", request,
                    new SoapActionCallback("http://example.com/voziloservice/xsd/PostVoziloRequest"));
        } catch (Exception e) {
            System.out.println("***ERROR VoziloClient > greska prilikom slanja!");
        }

        return response;

    }


}

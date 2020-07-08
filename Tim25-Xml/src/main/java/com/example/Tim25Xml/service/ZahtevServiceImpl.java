package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Stanje;
import com.example.Tim25Xml.model.Zahtev;
import com.example.Tim25Xml.soap.ZahtevClient;
import com.example.Tim25Xml.xsd.GetZahteveByIzdavacMailResponse;
import com.example.Tim25Xml.xsd.Vozilo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevServiceImpl implements ZahtevService {

    final static Logger logger = LoggerFactory.getLogger(ZahtevServiceImpl.class);

    @Autowired
    private ZahtevClient zahtevClient;

    @Override
    public List<Zahtev> findByEmail(String email) {
        GetZahteveByIzdavacMailResponse response = zahtevClient.getZahtevePoMejlu(email);

        if (response == null) {
            logger.info("***ERROR ZahtevService > sendToMicroServices > zahtevClient > returned NULL!");
            return null;
        } else {
            logger.info("***ZahtevService > sendToMicroServices > zahtevClient > Uspesno dobijeni zahtevi!");
        }

        List<Zahtev> zahtevi = new ArrayList<>();
        List<com.example.Tim25Xml.xsd.Zahtev> zahteviXSD = response.getZahtevi();

        logger.info("***ZahtevService > Dodavanje zahteva u listu!");

        for (com.example.Tim25Xml.xsd.Zahtev zahtevXSD : zahteviXSD) {
            Zahtev zahtev = napraviZahtevOdXSD(zahtevXSD);
            logger.info("***ZahtevService > Ispis zahteva pre add(): " + zahtev.toString());

            zahtevi.add(zahtev);
        }

        return zahtevi;
    }

    private Zahtev napraviZahtevOdXSD(com.example.Tim25Xml.xsd.Zahtev zahtevXSD) {
        Zahtev zahtev = new Zahtev();

        logger.info("***ZahtevService > usao u napraviZahtevOdXSD: ");

        for (Vozilo voziloXSD : zahtevXSD.getVozila()) {
            com.example.Tim25Xml.model.Vozilo vozilo = new com.example.Tim25Xml.model.Vozilo();

            vozilo.setZauzetoOd(voziloXSD.getVaziOd().toGregorianCalendar().toZonedDateTime().toLocalDate());
            vozilo.setZauzetoDo(voziloXSD.getVaziDo().toGregorianCalendar().toZonedDateTime().toLocalDate());
            vozilo.setMesto(voziloXSD.getMesto());
            vozilo.setMarkaAutomobila(voziloXSD.getMarkaAutomobila());
            vozilo.setModelAutomobila(voziloXSD.getModelAutomobila());
            vozilo.setMenjac(voziloXSD.getTipMenjaca());
            vozilo.setGorivo(voziloXSD.getTipGoriva());
            vozilo.setKlasaAutomobila(voziloXSD.getKlasaVozila());
            vozilo.setCenovnikId(voziloXSD.getCenovnikId());
            vozilo.setPredjenaKilometraza(voziloXSD.getPredjenaKilometraza());
            vozilo.setPlaniranoZaPreci(voziloXSD.getOgranicenaKilometraza());
            vozilo.setCwd(voziloXSD.isCDWProtection());
            vozilo.setBrojSedistaZaDecu(voziloXSD.getBrojSedistaDeca());
            vozilo.setIznajmljivacId(voziloXSD.getIznajmljivacId());
            vozilo.setIznajmljivacMail(voziloXSD.getIznajmljivacMail());
            vozilo.setPomId(voziloXSD.getPomId());

            zahtev.getVozila().add(vozilo);
        }

        if (zahtevXSD.getStanje().equals(com.example.Tim25Xml.xsd.Stanje.PENDING)) {
            zahtev.setStanje(Stanje.PENDING);
        } else if (zahtevXSD.getStanje().equals(com.example.Tim25Xml.xsd.Stanje.RESERVED)) {
            zahtev.setStanje(Stanje.RESERVED);
        } else if (zahtevXSD.getStanje().equals(com.example.Tim25Xml.xsd.Stanje.PAID)) {
            zahtev.setStanje(Stanje.PAID);
        } else if (zahtevXSD.getStanje().equals(com.example.Tim25Xml.xsd.Stanje.CANCELED)) {
            zahtev.setStanje(Stanje.CANCELED);
        } else if (zahtevXSD.getStanje().equals(com.example.Tim25Xml.xsd.Stanje.WAITING_REVIEW)) {
            zahtev.setStanje(Stanje.WAITING_REVIEW);
        } else if (zahtevXSD.getStanje().equals(com.example.Tim25Xml.xsd.Stanje.REVIEWED)) {
            zahtev.setStanje(Stanje.REVIEWED);
        }

        zahtev.setDatumOd(zahtevXSD.getDatumOd().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        zahtev.setDatumDo(zahtevXSD.getDatumDo().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        zahtev.setVremeOdobrenja(zahtevXSD.getVremeOdobrenja().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        zahtev.setVremeKreiranja(zahtevXSD.getVremeKreiranja().toGregorianCalendar().toZonedDateTime().toLocalDateTime());

        zahtev.setPodnosilac(zahtevXSD.getPodnosilac());
        zahtev.setIzdavac(zahtevXSD.getIzdavac());
        zahtev.setIzdavacMail(zahtevXSD.getIzdavacMail());


        return zahtev;
    }
}

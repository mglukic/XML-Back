package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.KomentrRepository;
import com.example.Tim25Xml.repository.VoziloRepository;
import com.example.Tim25Xml.soap.KomentarClient;
import com.example.Tim25Xml.xsd.GetKomentareByIdVozilaResponse;
import com.example.Tim25Xml.xsd.StanjeKomentara;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class KomentarServiceImpl implements KomentarService {

    final static Logger logger = LoggerFactory.getLogger(KomentarServiceImpl.class);

    @Autowired
   private KomentrRepository komentrRepository;

    @Autowired
    private VoziloService voziloRepository;

    @Autowired
    private KomentarClient komentarClient;

    @Override
    public Collection<Komentar> findAll() {
        return komentrRepository.findAll();
    }

    @Override
    public Collection<Komentar> findByIdVozila(Long id) {
        Collection<Komentar> ret = new ArrayList<>();

        GetKomentareByIdVozilaResponse response = komentarClient.getKomentareByIdVozila(id);

        if (response == null) {
            logger.info("***ERROR KomentarService > komentarClient > returned NULL!");
            return null;
        } else {
            logger.info("***KomentarService > komentarClient > uspesno dobijeni komentarti!");

            for (com.example.Tim25Xml.xsd.Komentar komentar : response.getKomentari()) {
                ret.add(napraviKomentarOdXSD(komentar));
            }
        }

        return ret;
        //return komentrRepository.findByIdVozila(id);

    }

    private Komentar napraviKomentarOdXSD(com.example.Tim25Xml.xsd.Komentar komentarXSD) {
        Komentar komentar = new Komentar();
        komentar.setIdVozila(komentarXSD.getIdVozila());
        komentar.setKomentar(komentarXSD.getKomentar());

        if (komentarXSD.getStanje().equals(StanjeKomentara.OBJAVLJEN)) {
            komentar.setStanje(com.example.Tim25Xml.model.StanjeKomentara.OBJAVLJEN);
        } else if (komentarXSD.getStanje().equals(StanjeKomentara.ODBIJEN)) {
            komentar.setStanje(com.example.Tim25Xml.model.StanjeKomentara.ODBIJEN);
        } else if (komentarXSD.getStanje().equals(StanjeKomentara.ODOBREN)) {
            komentar.setStanje(com.example.Tim25Xml.model.StanjeKomentara.ODOBREN);
        } else if (komentarXSD.getStanje().equals(StanjeKomentara.ODGOVOREN)) {
            komentar.setStanje(com.example.Tim25Xml.model.StanjeKomentara.ODGOVOREN);
        }
        logger.info("***KomentarService > komentarClient > Napravljen Komentar od XSD:: \n" + komentar.toString());

        return komentar;
    }

    @Override
    public Komentar findById(Long id) {
        return komentrRepository.findById(id).orElseGet(null);
    }

    @Override
    public void create(Long idVozila, String komentar) throws Exception {
        Komentar k=new Komentar(idVozila,komentar);
        k.setStanje(com.example.Tim25Xml.model.StanjeKomentara.ODOBREN);
        k = komentrRepository.save(k);

        sendToMicroServices(k);

        Vozilo zaIzmenu;
        zaIzmenu = voziloRepository.findById(idVozila);
        //zaIzmenu.setBrojKomentara(zaIzmenu.getBrojKomentara()+1);
        zaIzmenu=voziloRepository.update(zaIzmenu);

    }

    private void sendToMicroServices(Komentar komentar) {
        if (komentarClient.postKomentar(komentar) == null) {
            logger.info("***ERROR KomentarService > sendToMicroServices > komentarClient > returned NULL!");
        } else {
            logger.info("***KomentarService > sendToMicroServices > komentarClient > uspesno poslat komentar!");
        }
    }
}

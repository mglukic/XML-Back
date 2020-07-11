package com.example.Tim25Xml.service;


import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.KomentrRepository;
import com.example.Tim25Xml.repository.VoziloRepository;
import com.example.Tim25Xml.soap.GetAgentClient;
import com.example.Tim25Xml.soap.VoziloClient;
import com.example.Tim25Xml.xsd.PostProbaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.datatype.DatatypeConfigurationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class VoziloServiceImpl implements VoziloService {

    final static Logger logger = LoggerFactory.getLogger(VoziloServiceImpl.class);

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private GetAgentClient getAgentClient;

    @Autowired
    private KomentrRepository komentrRepository;

    @Autowired
    private VoziloClient voziloClient;

    @Override
    public List<Vozilo> findAll() {
        return voziloRepository.findAll();
    }

    @Override
    public List<Vozilo> sortiraj(String vrstraSortiranja) {
        List<Vozilo> svaVozila = voziloRepository.findAll();
        if (vrstraSortiranja.equals("KILOMETRAZA")) {
            svaVozila.sort(Comparator.comparingDouble(Vozilo::getPredjenaKilometraza));
            return svaVozila;
        } else if (vrstraSortiranja.equals("KOMENTARI")) {

            //svaVozila.sort(Comparator.comparingInt(Vozilo :: getBrojKomentara));
            return svaVozila;
        }
        return svaVozila;
    }

    @Override
    public Vozilo findById(Long id) {
        return voziloRepository.findById(id).orElseGet(null);
    }

    @Override
    public Vozilo create(Vozilo vozilo) throws Exception {
        Vozilo ret = new Vozilo();

        ret.copyValues(vozilo);
        ret.setIznajmljivacMail(getAgentClient.getMailUlogovanogAgentaResponse().getVraceniMejl());
        ret = voziloRepository.save(ret);

        ret.setPomId(ret.getId());
        ret = voziloRepository.save(ret);
        sendToMicroServices(ret);

        return ret;
    }

    @Override
    public Vozilo update(Vozilo vozilo) throws Exception {
        vozilo.copyValues(vozilo);
        vozilo = voziloRepository.save(vozilo);
        return vozilo;
    }


    private void sendToMicroServices(Vozilo vozilo) throws DatatypeConfigurationException {
        if (voziloClient.postVozilo(vozilo) == null) {
            logger.info("***ERROR VoziloService > sendToMicroServices > voziloClient > returned NULL!");
        } else {
            logger.info("***VoziloService > sendToMicroServices > voziloClient > uspesno poslat vozilo!");
        }

        PostProbaResponse response = voziloClient.postProba(vozilo.getMarkaAutomobila() + vozilo.getModelAutomobila());

        if (response == null) {
            logger.info("***ERROR VoziloService > sendToMicroServices > voziloClient > returned NULL! ((ZA PostProba))");
        } else {
            logger.info("***VoziloService > sendToMicroServices > voziloClient > uspesno proslo PostProba! VRATIO:: " + response.getPrima());
        }


    }

}

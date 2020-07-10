package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Izvestaj;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.IzvestajRepository;
import com.example.Tim25Xml.repository.VoziloRepository;
import com.example.Tim25Xml.soap.GetAgentClient;
import com.example.Tim25Xml.soap.VoziloClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IzvestajServiceImpl implements IzvestajService {
    final static Logger logger = LoggerFactory.getLogger(IzvestajService.class);

    @Autowired
    private IzvestajRepository izvestajRepository;

    @Autowired
    private GetAgentClient getAgentClient;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private VoziloRepository voziloRepository;

    @Override
    public Izvestaj kreirajIzvestaj(Izvestaj izvestaj) {
        Izvestaj ret = new Izvestaj();
        ret.setIdVozila(izvestaj.getIdVozila());
        ret.setPredjenaKilometraza(izvestaj.getPredjenaKilometraza());
        ret.setTekstIzvestaja(izvestaj.getTekstIzvestaja());
        ret.setIznajmljivacMail(getAgentClient.getMailUlogovanogAgentaResponse().getVraceniMejl());
        logger.info("######### Kreiran izvestaj: " + ret);
        ret = izvestajRepository.save(ret);


        //TODO: izvestaji u mikroservisu dodati na vozilo po pomid predjenu kilometrazu
        //TODO: izvestaji i na frontu uvezati sa kreiranjem izvestaja
        //TODO: chatovi i poruke endpoint ili soap
        //TODO: Blokiranje korisnika
        //TODO: poruke na mail?
        
        Vozilo v = voziloService.findById(ret.getIdVozila());
        v.setPredjenaKilometraza(v.getPredjenaKilometraza()+ret.getPredjenaKilometraza());
        voziloRepository.save(v);

        return ret;
    }

    @Override
    public Collection<Izvestaj> findAll() {
        return izvestajRepository.findAll();
    }

    @Override
    public Collection<Izvestaj> findAllByAuthorId(String id) {

        String email = getAgentClient.getMailUlogovanogAgentaResponse().getVraceniMejl();
        //String email = "agent@a.com";

        return izvestajRepository.findByIznajmljivacMail(email);
    }

    void test(){
        Izvestaj i1= new Izvestaj(678, "super", 1l);
        i1.setIznajmljivacMail("agent@a.com");
        Izvestaj i2= new Izvestaj(890, "ekstra", 2l);
        i2.setIznajmljivacMail("nekidrugi@a.com");
        Izvestaj i3= new Izvestaj(345, "strava", 3l);
        i3.setIznajmljivacMail("agent@a.com");

        izvestajRepository.save(i1);
        izvestajRepository.save(i2);

    };

}

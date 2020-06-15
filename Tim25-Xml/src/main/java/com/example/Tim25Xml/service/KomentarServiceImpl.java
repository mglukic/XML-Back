package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.KomentrRepository;
import com.example.Tim25Xml.repository.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class KomentarServiceImpl implements KomentarService {

    @Autowired
   private KomentrRepository komentrRepository;

    @Autowired
    private VoziloService voziloRepository;

    @Override
    public Collection<Komentar> findAll() {
        return komentrRepository.findAll();
    }

    @Override
    public Collection<Komentar> findByIdVozila(Long id) {

        return komentrRepository.findByIdVozila(id);
    }

    @Override
    public Komentar findById(Long id) {
        return komentrRepository.findById(id).orElseGet(null);
    }

    @Override
    public void create(Long idVozila, String komentar) throws Exception {
        Komentar k=new Komentar(idVozila,komentar);
        komentrRepository.save(k);
        Vozilo zaIzmenu;
        zaIzmenu = voziloRepository.findById(idVozila);
        zaIzmenu.setBrojKomentara(zaIzmenu.getBrojKomentara()+1);
        zaIzmenu=voziloRepository.update(zaIzmenu);

    }
}

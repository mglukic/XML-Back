package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.KomentrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class KomentarServiceImpl implements KomentarService {
   @Autowired
   private KomentrRepository komentrRepository;

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
    }
}

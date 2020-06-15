package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.ZauzeceVozila;
import com.example.Tim25Xml.repository.ZauzeceVozilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZauzeceVozilaServiceImpl implements ZauzeceVozilaService {

    @Autowired
    private ZauzeceVozilaRepository zauzeceVozilaRepository;

    @Override
    public ZauzeceVozila add(ZauzeceVozila zauzeceVozila) {
        ZauzeceVozila novoZauzece=new ZauzeceVozila(zauzeceVozila.getZauzetOd(),zauzeceVozila.getZauzetDo(),zauzeceVozila.getIdVozila());
        zauzeceVozilaRepository.save(novoZauzece);
        return novoZauzece;
    }

    @Override
    public ZauzeceVozila findById(Long id) {
        return zauzeceVozilaRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<ZauzeceVozila> findAll() {
        return zauzeceVozilaRepository.findAll();
    }
}

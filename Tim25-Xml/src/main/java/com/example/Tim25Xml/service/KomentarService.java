package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Vozilo;

import java.util.Collection;

public interface KomentarService {

    Collection<Komentar> findAll();
    Collection<Komentar> findByIdVozila(Long id);
    Komentar findById(Long id);
    void create(Long idVozila, String komentar) throws Exception;
}

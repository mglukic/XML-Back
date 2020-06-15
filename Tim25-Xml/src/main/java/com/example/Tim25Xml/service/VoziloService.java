package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Vozilo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public interface VoziloService {

    List<Vozilo> findAll();
    Collection<Vozilo> sortiraj(String vrstraSortiranja);
    Vozilo findById(Long id);
    Vozilo create(Vozilo vozilo) throws Exception;
    Vozilo update(Vozilo vozilo) throws Exception;

}

package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Vozilo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public interface VoziloService {

    Collection<Vozilo> findAll();
    Vozilo findById(Long id);
    Vozilo create(Vozilo vozilo) throws Exception;

}

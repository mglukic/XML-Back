package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Slika;
import com.example.Tim25Xml.model.Vozilo;
import org.apache.tomcat.jni.File;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public interface SlikaService {

    //Collection<Slika> findAll();
    //Collection<Slika> findByIdVozila(Long id);
    //Slika findById(Long id);
    void create(Long idVozila, ArrayList<Image> files) throws Exception;
}

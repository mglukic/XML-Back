package com.example.Tim25Xml.service;


import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.KomentrRepository;
import com.example.Tim25Xml.repository.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class VoziloServiceImpl implements VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private KomentrRepository komentrRepository;

    @Override
    public List<Vozilo> findAll() {
        return voziloRepository.findAll();
    }

    @Override
    public List<Vozilo> sortiraj(String vrstraSortiranja) {
        List<Vozilo>svaVozila=voziloRepository.findAll();
        if(vrstraSortiranja.equals("KILOMETRAZA")){
            svaVozila.sort(Comparator.comparingDouble(Vozilo :: getPredjenaKilometraza));
            return svaVozila;
        }
        else if(vrstraSortiranja.equals("KOMENTARI")){

            svaVozila.sort(Comparator.comparingInt(Vozilo :: getBrojKomentara));
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
        ret = voziloRepository.save(ret);
        return ret;
    }

    @Override
    public Vozilo update(Vozilo vozilo) throws Exception {
        vozilo.copyValues(vozilo);
        vozilo = voziloRepository.save(vozilo);
        return vozilo;
    }
}

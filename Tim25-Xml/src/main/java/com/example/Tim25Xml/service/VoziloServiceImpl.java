package com.example.Tim25Xml.service;


import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VoziloServiceImpl implements VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Override
    public Collection<Vozilo> findAll() {
        return voziloRepository.findAll();
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
}

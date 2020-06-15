package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.ZauzeceVozila;

import java.util.List;

public interface ZauzeceVozilaService {
    ZauzeceVozila add(ZauzeceVozila zauzeceVozila);
    ZauzeceVozila findById(Long id);
    List<ZauzeceVozila> findAll();
}

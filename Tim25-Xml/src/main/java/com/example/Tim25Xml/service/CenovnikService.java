package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Cenovnik;

import java.util.Collection;

public interface CenovnikService {

    public Collection<Cenovnik> findAll();
    public void removeCenovnik(Long id);
    public Cenovnik addNewCenovnik(Cenovnik newCenovnik);
    public Cenovnik getById(Long id);

    Cenovnik getByNaziv(String naziv);
}

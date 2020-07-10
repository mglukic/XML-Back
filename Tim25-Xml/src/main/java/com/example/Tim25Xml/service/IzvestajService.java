package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Izvestaj;

import java.util.Collection;

public interface IzvestajService {


    Izvestaj kreirajIzvestaj(Izvestaj izvestaj);
    Collection<Izvestaj> findAll();
    Collection<Izvestaj> findAllByAuthorId(String id);
}

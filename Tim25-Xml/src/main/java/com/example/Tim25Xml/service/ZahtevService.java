package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Zahtev;

import java.util.List;

public interface ZahtevService {

    List<Zahtev> findByEmail(String email);
}

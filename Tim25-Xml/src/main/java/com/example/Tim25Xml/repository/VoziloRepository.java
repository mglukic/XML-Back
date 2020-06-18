package com.example.Tim25Xml.repository;

import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.service.CenovnikService;
import com.example.Tim25Xml.service.KomentarService;
import com.example.Tim25Xml.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoziloRepository extends JpaRepository<Vozilo,Long> {






}

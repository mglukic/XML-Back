package com.example.Tim25Xml.repository;

import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Slika;
import com.example.Tim25Xml.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface KomentrRepository extends JpaRepository<Komentar,Long> {
    Collection<Komentar> findByIdVozila(Long id);

}

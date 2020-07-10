package com.example.Tim25Xml.repository;

import com.example.Tim25Xml.model.Izvestaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {
    Collection<Izvestaj> findByIznajmljivacMail(String email);

}

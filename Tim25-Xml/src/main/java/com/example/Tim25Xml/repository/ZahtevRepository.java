package com.example.Tim25Xml.repository;


import com.example.Tim25Xml.model.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtevRepository extends JpaRepository<Zahtev,Long> {
}

package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Cenovnik;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.repository.CenovnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CenovnikServiceImpl implements CenovnikService{

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @Override
    public Collection<Cenovnik> findAll() {
        return cenovnikRepository.findAll();
    }

    @Override
    public void removeCenovnik(Long id) {

        cenovnikRepository.deleteById(id);
    }

    @Override
    public Cenovnik addNewCenovnik(Cenovnik newCenovnik) {
        Cenovnik ret = new Cenovnik();
        ret.setCenaCDW(newCenovnik.getCenaCDW());
        ret.setCenaDan(newCenovnik.getCenaDan());
        ret.setCenaPrekoraceniKm(newCenovnik.getCenaPrekoraceniKm());
        ret.setNaziv(newCenovnik.getNaziv());
        ret.setPopustProcenat(newCenovnik.getPopustProcenat());


        ret = cenovnikRepository.save(ret);
        return ret;

    }

    @Override
    public Cenovnik getById(Long id) {
        return cenovnikRepository.findById(id).orElseGet(null);
    }
}

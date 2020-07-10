package com.example.Tim25Xml.controler;

import com.example.Tim25Xml.model.Chat;
import com.example.Tim25Xml.model.Izvestaj;
import com.example.Tim25Xml.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.Collection;

@RestController
@RequestMapping("/api/report")
public class IzestajController {

    @Autowired
    private IzvestajService izvestajService;

    @PostMapping( value = "")
    public Izvestaj create(@RequestBody Izvestaj izvestaj) throws Exception {

        return izvestajService.kreirajIzvestaj(izvestaj);
    }

    //get all
    @GetMapping( value = "")
    public Collection<Izvestaj> getAll() {
        Collection<Izvestaj> izvestaji = izvestajService.findAll();

        return izvestaji;
    }

    @GetMapping(value = "/autor/{email}")
    public Collection<Izvestaj> getByAuthorEmail(@PathVariable("email") String email) {

        Collection<Izvestaj> izvestaji = izvestajService.findAllByAuthorId(email);

        return izvestaji;
    }




}

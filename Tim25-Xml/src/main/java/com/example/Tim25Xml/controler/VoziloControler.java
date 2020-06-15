package com.example.Tim25Xml.controler;


import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Slika;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.model.ZauzeceVozila;
import com.example.Tim25Xml.model.dto.SlikaDTO;
import com.example.Tim25Xml.repository.SlikaRepository;
import com.example.Tim25Xml.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/vozilo")
public class VoziloControler {

    @Autowired
    private VoziloService voziloService;
    @Autowired
    private KomentarService komentarService;

    @Autowired
    private SlikaRepository slikaRepository;

    @Autowired
    private ZauzeceVozilaService zauzeceVozilaService;

   /* @Autowired
    private SlikaService slikaService;*/

    @RequestMapping(method = GET, value = "/vozila")
    public Collection<Vozilo> ucitajSve() {
        return voziloService.findAll();
    }

    @RequestMapping(method = GET, value = "listaVozila/{voziloId}")
    public Vozilo loadById(@PathVariable Long voziloId) {
        return voziloService.findById(voziloId);
    }

    @RequestMapping(method = POST, value = "/add")
    public ResponseEntity<?> dodajVozilo(@RequestBody Vozilo vozilo) throws Exception {


        Vozilo vozilo1 = voziloService.create(vozilo);
        return new ResponseEntity<Vozilo>(vozilo1, HttpStatus.CREATED);
    }

    @PostMapping(value = "/slika")
    public ResponseEntity<Slika> dodajSluku(@RequestBody SlikaDTO slikaDTO) throws Exception {

       /* String split[]=slikaDTO.getPutanja().split(",");
        slikaDTO.setPutanja(split[1]);*/

        byte[] imageByte;
        Base64.Decoder decoder=Base64.getDecoder();
        imageByte=decoder.decode(slikaDTO.getPutanja());

        Slika slika=new Slika();
        slika.setPicByte(imageByte);
        slika.setIdVozila(slikaDTO.getId());
        slikaRepository.save(slika);
        return new ResponseEntity<>(slika,HttpStatus.CREATED);

    }

    @RequestMapping(method = GET, value = "/vratiSvaVozila")
    public Collection<Vozilo> vratiVozila() {


        return voziloService.findAll();
    }

    @PostMapping(value = "/dodajKomentar")
    public void dodajVozilo(@RequestBody Komentar komentar) throws Exception {

        komentarService.create(komentar.getIdVozila(),komentar.getKomentar());
    }

    @RequestMapping(method = GET, value = "listaVozila/vratiKomentare/{idVozila}")
    public Collection<Komentar> vratiKometareVozila(@PathVariable Long idVozila) {


        return komentarService.findByIdVozila(idVozila);
    }

    @GetMapping(value = "/sortiraj/{vrstaSortiranja}")
    public Collection<Vozilo> sortirajVozila(@PathVariable String vrstaSortiranja) {


        return voziloService.sortiraj(vrstaSortiranja);
    }

    @PostMapping(value = "/rezervisi")
    public void rezervisiVozilo(@RequestBody ZauzeceVozila zauzeceVozila) throws Exception {

        zauzeceVozilaService.add(zauzeceVozila);
    }
}

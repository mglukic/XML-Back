package com.example.Tim25Xml.controler;


import com.example.Tim25Xml.model.Komentar;
import com.example.Tim25Xml.model.Slika;
import com.example.Tim25Xml.model.Vozilo;
import com.example.Tim25Xml.service.KomentarService;
import com.example.Tim25Xml.service.SlikaService;
import com.example.Tim25Xml.service.VoziloService;
import com.example.Tim25Xml.service.VoziloServiceImpl;
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

    @RequestMapping(method = POST, value = "/dodajSliku")
    @ResponseBody
    public void dodajSluku(@RequestBody Slika product) throws Exception {
        String folder="/slike/"+product.getIdVozila();

        ArrayList<File> files = product.getImages();
        for (File file:files) {
            String fileName = file.getAbsolutePath();
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "jpg", new File(folder));
          /*  byte[] bytes = file.getBytes();
            Path path= Paths.get(folder+file.getOriginalFilename()+product.getIdVozila());
            Files.write(path, bytes);*/
            // ImageModel blueImage = new ImageModel(2, "JSA-ABOUT-IMAGE-BLUE-BACKGROUND", "png", arrayPic);
            //Slika img = new Slika(idVozila, compressBytes(file.getBytes()));

        }

        //slikaService.create(voziloId,files);

    }

    @RequestMapping(method = GET, value = "/vratiSvaVozila")
    public Collection<Vozilo> vratiVozila() {


        return voziloService.findAll();
    }

    @RequestMapping(method = POST, value = "/dodajKomentar")
    public void dodajVozilo(@RequestBody Komentar komentar) throws Exception {

        komentarService.create(komentar.getIdVozila(),komentar.getKomentar());
    }

    @RequestMapping(method = GET, value = "listaVozila/vratiKomentare/{idVozila}")
    public Collection<Komentar> vratiKometareVozila(@PathVariable Long idVozila) {


        return komentarService.findByIdVozila(idVozila);
    }
}

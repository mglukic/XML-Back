package com.example.Tim25Xml.controler;


import com.example.Tim25Xml.model.*;
import com.example.Tim25Xml.model.dto.SlikaDTO;
import com.example.Tim25Xml.repository.ImageRepository;
import com.example.Tim25Xml.repository.SlikaRepository;
import com.example.Tim25Xml.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
//import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageModelService imageModelService;

    //cuvanje slike
    @PostMapping("/upload")
    public  ResponseEntity<?>  uploadImage(@RequestParam("imageFile") MultipartFile mpf) throws IOException {
        //System.out.println("File to string  - " + file.toString());
        //System.out.println("Original Image Byte Size - " + fimpfle.getBytes().length);

        //for (MultipartFile mpf : file) {
            ImageModel img = new ImageModel(mpf.getOriginalFilename(), mpf.getContentType(), compressBytes(mpf.getBytes()));
            imageRepository.save(img);
        //}
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //vracanje slike
    @GetMapping(path = { "/getImage/{idVozila}" })
    public ImageModel getImage(@PathVariable("idVozila") String idVozila) throws IOException {

        final Optional<ImageModel> retrievedImage = Optional.ofNullable(imageModelService.findByCarId(idVozila));


        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    /*
    //vraca sve slike, ali ne radi na frontu
    @GetMapping(path = { "/getImages/{idVozila}" })
    public Collection<byte[]> getImages(@PathVariable("idVozila") String idVozila) throws IOException {
        final Optional<Collection<ImageModel>> retrievedImages   = Optional.ofNullable(imageModelService.findImages(idVozila));
        Collection<ImageModel> ret = imageRepository.findAll();
        ret.clear();
        //retrievedImages.get().addAll(ret);
        ret.addAll(retrievedImages.get());

        Collection<byte[]> byteRet= new ArrayList<byte[]>();
        for(ImageModel im : ret) {
            byteRet.add(im.getPicByte());
        }

        return byteRet;
    }

     */





    // compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);}
        try {
            outputStream.close();
        } catch (IOException e) {
        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {
        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }



}

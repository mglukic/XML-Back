package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.Slika;
import com.example.Tim25Xml.model.Vozilo;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.Deflater;

//@Service
public class SlikaServiceImp {
   /* @Autowired
    private SlikaRepository slikaRepository;*/

   /* @Override
    public Collection<Slika> findAll() {
        return slikaRepository.findAll();
    }

    @Override
    public Collection<Slika> findByIdVozila(Long id) {
        return slikaRepository.findByIdVozila(id);
    }

    @Override
    public Slika findById(Long id) {
        return slikaRepository.findById(id).orElseGet(null);
    }*/
/*
    @Override
    public void create(Long idVozila, ArrayList<> files) throws Exception {

        String folder="/slike/";

        for (ImageIcon file:files) {

            byte[] bytes = file.;
            Path path= Paths.get(folder+file.getOriginalFilename()+idVozila);
            Files.write(path, bytes);
           // ImageModel blueImage = new ImageModel(2, "JSA-ABOUT-IMAGE-BLUE-BACKGROUND", "png", arrayPic);
            //Slika img = new Slika(idVozila, compressBytes(file.getBytes()));

        }

    }

        // compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }*/
}

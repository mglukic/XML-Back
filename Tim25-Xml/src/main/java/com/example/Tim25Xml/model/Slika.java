package com.example.Tim25Xml.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Slika implements Serializable {

 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IdVozila", nullable = false)*/
    private Long idVozila;

  /*  @Column(name = "PicByte", nullable = false ,length = 1000)
    private byte[] picByte;*/

    private ArrayList<File> images;


    public Slika() {
    }

    public Slika(Long idVozila, ArrayList<File> images) {
        this.idVozila = idVozila;
        this.images = images;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public ArrayList<File> getImages() {
        return images;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public void setImages(ArrayList<File> images) {
        this.images = images;
    }
    /*  public Slika(Long idVozila, byte[] picByte ) {
        this.idVozila = idVozila;
        this.picByte = picByte;
    }

    public Long getId() {
        return id;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }*/
}

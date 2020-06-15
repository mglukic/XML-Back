package com.example.Tim25Xml.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Vozilo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ZauzetoOd", nullable = false)
    private LocalDate zauzetoOd;

    @Column(name = "ZauzetoDo", nullable = false)
    private LocalDate zauzetoDo;


    @Column(name = "Mesto", nullable = false)
    private String mesto;

    @Column(name = "MarkaAutomobila", nullable = false)
    private String markaAutomobila;

    @Column(name = "ModelAutomobila", nullable = false)
    private String modelAutomobila;

    @Column(name = "Menjac", nullable = false)
    private String menjac;

    @Column(name = "Gorivo", nullable = false)
    private String gorivo;

    @Column(name = "KlasaAutomobila", nullable = false)
    private String klasaAutomobila;

    @Column(name = "Cena", nullable = false)
    private double cena;

    @Column(name = "PredjenaKilometraza", nullable = false)
    private double predjenaKilometraza;

    @Column(name = "PlaniranoZaPreci", nullable = false)
    private String planiranoZaPreci;

    @Column(name = "CWD", nullable = false)
    private boolean cwd;

    @Column(name = "BrojSedistaZaDecu", nullable = false)
    private int brojSedistaZaDecu;

    @Column(name = "BrojKomentara", nullable = false)
    private int brojKomentara;

    public Vozilo() {
    }

    public Vozilo(LocalDate zauzetoOd,LocalDate zauzetoDo,String mesto, String markaAutomobila, String modelAutomobila, String menjac, String gorivo, String klasaAutomobila, double cena, double predjenaKilometraza, String planiranoZaPreci, boolean cwd, int brojSedistaZaDecu) {
        this.zauzetoOd=zauzetoOd;
        this.zauzetoDo=zauzetoDo;
        this.mesto = mesto;
        this.markaAutomobila = markaAutomobila;
        this.modelAutomobila = modelAutomobila;
        this.menjac = menjac;
        this.gorivo = gorivo;
        this.klasaAutomobila = klasaAutomobila;
        this.cena = cena;
        this.predjenaKilometraza = predjenaKilometraza;
        this.planiranoZaPreci = planiranoZaPreci;
        this.cwd = cwd;
        this.brojSedistaZaDecu = brojSedistaZaDecu;
        this.brojKomentara=0;
    }

    public Long getId() {
        return id;
    }

    public String getMesto() {
        return mesto;
    }

    public String getMarkaAutomobila() {
        return markaAutomobila;
    }

    public String getModelAutomobila() {
        return modelAutomobila;
    }

    public String getMenjac() {
        return menjac;
    }

    public String getGorivo() {
        return gorivo;
    }

    public String getKlasaAutomobila() {
        return klasaAutomobila;
    }

    public double getCena() {
        return cena;
    }

    public double getPredjenaKilometraza() {
        return predjenaKilometraza;
    }

    public String getPlaniranoZaPreci() {
        return planiranoZaPreci;
    }

    public boolean isCwd() {
        return cwd;
    }

    public int getBrojSedistaZaDecu() {
        return brojSedistaZaDecu;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public void setMarkaAutomobila(String markaAutomobila) {
        this.markaAutomobila = markaAutomobila;
    }

    public void setModelAutomobila(String modelAutomobila) {
        this.modelAutomobila = modelAutomobila;
    }

    public void setMenjac(String menjac) {
        this.menjac = menjac;
    }

    public void setGorivo(String gorivo) {
        this.gorivo = gorivo;
    }

    public void setKlasaAutomobila(String klasaAutomobila) {
        this.klasaAutomobila = klasaAutomobila;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setPredjenaKilometraza(double predjenaKilometraza) {
        this.predjenaKilometraza = predjenaKilometraza;
    }

    public void setPlaniranoZaPreci(String planiranoZaPreci) {
        this.planiranoZaPreci = planiranoZaPreci;
    }

    public void setCwd(boolean cwd) {
        this.cwd = cwd;
    }

    public void setBrojSedistaZaDecu(int brojSedistaZaDecu) {
        this.brojSedistaZaDecu = brojSedistaZaDecu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojKomentara() {
        return brojKomentara;
    }

    public void setBrojKomentara(int brojKomentara) {
        this.brojKomentara = brojKomentara;
    }

    public LocalDate getZauzetoOd() {
        return zauzetoOd;
    }

    public void setZauzetoOd(LocalDate zauzetoOd) {
        this.zauzetoOd = zauzetoOd;
    }

    public LocalDate getZauzetoDo() {
        return zauzetoDo;
    }

    public void setZauzetoDo(LocalDate zauzetoDo) {
        this.zauzetoDo = zauzetoDo;
    }

    public void copyValues(Vozilo vozilo) {
        this.zauzetoOd=vozilo.getZauzetoOd();
        this.zauzetoDo=vozilo.getZauzetoDo();
        this.mesto = vozilo.getMesto();
        this.markaAutomobila = vozilo.getMarkaAutomobila();
        this.modelAutomobila = vozilo.getModelAutomobila();
        this.menjac = vozilo.getMenjac();
        this.gorivo=vozilo.getGorivo();
        this.klasaAutomobila=vozilo.getKlasaAutomobila();
        this.cena=vozilo.getCena();
        this.cwd=vozilo.isCwd();
        this.brojSedistaZaDecu=vozilo.getBrojSedistaZaDecu();
        this.predjenaKilometraza=vozilo.getPredjenaKilometraza();
        this.planiranoZaPreci=vozilo.getPlaniranoZaPreci();
        this.brojKomentara=vozilo.getBrojKomentara();

    }

}

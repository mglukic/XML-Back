package com.example.Tim25Xml.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "CenovnikId", nullable = false)
    private String cenovnikId;

    @Column(name = "PredjenaKilometraza", nullable = false)
    private double predjenaKilometraza;

    @Column(name = "PlaniranoZaPreci", nullable = false)
    private String planiranoZaPreci;

    @Column(name = "CWD", nullable = false)
    private boolean cwd;

    @Column(name = "BrojSedistaZaDecu", nullable = false)
    private int brojSedistaZaDecu;

    //@Column(name = "BrojKomentara", nullable = false)
    //private int brojKomentara;

    @Column(name = "IznajmljivacId", nullable = false)
    private Long iznajmljivacId;

    @Column(name = "IznajmljivacMail", nullable = false)
    private String iznajmljivacMail;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "VOZILO_ZAHTEV",
            joinColumns = @JoinColumn(name = "vozilo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "zahtev_id", referencedColumnName = "id"))
    private Set<Zahtev> zahtevi = new HashSet<Zahtev>();

    public Vozilo() {
    }

    public Vozilo(LocalDate zauzetoOd,LocalDate zauzetoDo,String mesto, String markaAutomobila, String modelAutomobila, String menjac, String gorivo, String klasaAutomobila, String cenovnik, double predjenaKilometraza, String planiranoZaPreci, boolean cwd, int brojSedistaZaDecu, Long iznajmljivacId, String iznajmljivacMail) {
        this.zauzetoOd=zauzetoOd;
        this.zauzetoDo=zauzetoDo;
        this.mesto = mesto;
        this.markaAutomobila = markaAutomobila;
        this.modelAutomobila = modelAutomobila;
        this.menjac = menjac;
        this.gorivo = gorivo;
        this.klasaAutomobila = klasaAutomobila;
        this.cenovnikId = cenovnik;
        this.predjenaKilometraza = predjenaKilometraza;
        this.planiranoZaPreci = planiranoZaPreci;
        this.cwd = cwd;
        this.brojSedistaZaDecu = brojSedistaZaDecu;
        //this.brojKomentara=0;
        this.iznajmljivacMail = iznajmljivacMail;
        this.iznajmljivacId = iznajmljivacId;
        this.zahtevi=zahtevi;
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

    //public int getBrojKomentara() {
    //    return brojKomentara;
    //}

    //public void setBrojKomentara(int brojKomentara) {
    //    this.brojKomentara = brojKomentara;
    //}

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

    public String getCenovnikId() {
        return cenovnikId;
    }

    public void setCenovnikId(String cenovnikId) {
        this.cenovnikId = cenovnikId;
    }

    public String getIznajmljivacMail() {
        return iznajmljivacMail;
    }

    public void setIznajmljivacMail(String iznajmljivacMail) {
        this.iznajmljivacMail = iznajmljivacMail;
    }

    public Long getIznajmljivacId() {
        return iznajmljivacId;
    }

    public void setIznajmljivacId(Long iznajmljivacId) {
        this.iznajmljivacId = iznajmljivacId;
    }

    public Set<Zahtev> getZahtevi() {
        return zahtevi;
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
        this.cenovnikId=vozilo.getCenovnikId();
        this.cwd=vozilo.isCwd();
        this.brojSedistaZaDecu=vozilo.getBrojSedistaZaDecu();
        this.predjenaKilometraza=vozilo.getPredjenaKilometraza();
        this.planiranoZaPreci=vozilo.getPlaniranoZaPreci();
        //this.brojKomentara=vozilo.getBrojKomentara();
        this.iznajmljivacId = vozilo.getIznajmljivacId();
        this.iznajmljivacMail = vozilo.getIznajmljivacMail();

    }

}

package com.example.Tim25Xml.model;

import javax.persistence.*;


@Entity
public class Izvestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PredjenaKilometraza", nullable = false)
    private double predjenaKilometraza;

    @Column(name = "TekstIzvestaja", nullable = false)
    private String tekstIzvestaja;

    @Column(name = "IznajmljivacMail", nullable = false)
    private String iznajmljivacMail;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;

    @Override
    public String toString() {
        return "Izvestaj{" +
                "id=" + id +
                ", predjenaKilometraza=" + predjenaKilometraza +
                ", tekstIzvestaja='" + tekstIzvestaja + '\'' +
                ", iznajmljivacMail='" + iznajmljivacMail + '\'' +
                ", idVozila=" + idVozila +
                '}';
    }

    public Izvestaj() {
    }

    public Izvestaj(double predjenaKilometraza, String tekstIzvestaja, Long idVozila) {
        this.predjenaKilometraza = predjenaKilometraza;
        this.tekstIzvestaja = tekstIzvestaja;
        this.iznajmljivacMail = "";
        this.idVozila = idVozila;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPredjenaKilometraza() {
        return predjenaKilometraza;
    }

    public void setPredjenaKilometraza(double predjenaKilometraza) {
        this.predjenaKilometraza = predjenaKilometraza;
    }

    public String getTekstIzvestaja() {
        return tekstIzvestaja;
    }

    public void setTekstIzvestaja(String tekstIzvestaja) {
        this.tekstIzvestaja = tekstIzvestaja;
    }

    public String getIznajmljivacMail() {
        return iznajmljivacMail;
    }

    public void setIznajmljivacMail(String iznajmljivacMail) {
        this.iznajmljivacMail = iznajmljivacMail;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }
}

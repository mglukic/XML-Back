package com.example.Tim25Xml.model;

import javax.persistence.*;

@Entity
public class Komentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;

    @Column(name = "Komentar", nullable = false)
    private String komentar;

    public Komentar() {
    }

    public Komentar(Long idVozila, String komentar) {
        this.idVozila = idVozila;
        this.komentar = komentar;
    }

    public Long getId() {
        return id;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}

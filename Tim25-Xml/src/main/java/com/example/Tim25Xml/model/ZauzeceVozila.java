package com.example.Tim25Xml.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ZauzeceVozila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ZauzetOd", nullable = false)
    private LocalDate zauzetOd;

    @Column(name = "ZauzetDo", nullable = false)
    private LocalDate zauzetDo;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;

    public ZauzeceVozila() {
    }

    public ZauzeceVozila(LocalDate zauzetOd, LocalDate zauzetDo, Long idVozila) {
        this.zauzetOd = zauzetOd;
        this.zauzetDo = zauzetDo;
        this.idVozila = idVozila;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getZauzetOd() {
        return zauzetOd;
    }

    public void setZauzetOd(LocalDate zauzetOd) {
        this.zauzetOd = zauzetOd;
    }

    public LocalDate getZauzetDo() {
        return zauzetDo;
    }

    public void setZauzetDo(LocalDate zauzetDo) {
        this.zauzetDo = zauzetDo;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }
}

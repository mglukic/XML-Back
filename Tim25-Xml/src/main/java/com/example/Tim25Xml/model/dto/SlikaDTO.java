package com.example.Tim25Xml.model.dto;

public class SlikaDTO {

    private String putanja;
    private Long idVozila;

    public SlikaDTO() {
    }

    public SlikaDTO(String putanja, Long id) {
        this.putanja = putanja;
        this.idVozila = id;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    public Long getId() {
        return idVozila;
    }

    public void setId(Long id) {
        this.idVozila = id;
    }
}

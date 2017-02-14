package com.surperfluousfew.auktionsystem.models;

public class Adress {
    private int id;
    private String gata;
    private String postnummer;
    private String ort;

    public Adress(int id, String gata, String postnummer, String ort) {
        this.id = id;
        this.gata = gata;
        this.postnummer = postnummer;
        this.ort = ort;
    }

    public int getId() {
        return id;
    }

    public String getGata() {
        return gata;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public String getOrt() {
        return ort;
    }
}

package com.surperfluousfew.auktionsystem.models;

public class Produkt {
    private int id;
    private Leverantor leverantor;
    private String namn;
    private String beskrivning;
    private String bildnamn;

    public Produkt(int id, Leverantor leverantor, String namn, String beskrivning, String bildnamn) {
        this.id = id;
        this.leverantor = leverantor;
        this.namn = namn;
        this.beskrivning = beskrivning;
        this.bildnamn = bildnamn;
    }

    public int getId() {
        return id;
    }

    public Leverantor getLeverantor() {
        return leverantor;
    }

    public String getNamn() {
        return namn;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public String getBildnamn() {
        return bildnamn;
    }

    @Override
    public String toString() {
        return namn;
    }
}

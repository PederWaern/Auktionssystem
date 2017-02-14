package com.surperfluousfew.auktionsystem.models;

public class Produkt {
    private int id;
    private Leverantor leverantor;
    private String namn;
    private String beskrivning;
    private Double provision;
    private String bildnamn;

    public Produkt(int id, Leverantor leverantor, String namn, String beskrivning, Double provision, String bildnamn) {
        this.id = id;
        this.leverantor = leverantor;
        this.namn = namn;
        this.beskrivning = beskrivning;
        this.provision = provision;
        this.bildnamn = bildnamn;
    }



}

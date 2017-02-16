package com.surperfluousfew.auktionsystem.models;

import java.util.ArrayList;

public class Auktion {
    private int id;
    private Produkt produkt;
    private double acceptpris;
    private double utgangspris;
    private String startDatum;
    private String slutDatum;
    private ArrayList<Bud> budArrayList = new ArrayList<>();

    public Auktion(int id, Produkt produkt, double acceptpris, double utgangspris, String startDatum, String slutDatum) {
        this.id = id;
        this.produkt = produkt;
        this.acceptpris = acceptpris;
        this.utgangspris = utgangspris;
        this.startDatum = startDatum;
        this.slutDatum = slutDatum;
    }

    public void addBud(Bud bud){
        budArrayList.add(bud);
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public double getAcceptpris() {
        return acceptpris;
    }

    public double getUtgangspris() {
        return utgangspris;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public String getSlutDatum() {
        return slutDatum;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Bud> getBudArrayList() {
        return budArrayList;
    }

}

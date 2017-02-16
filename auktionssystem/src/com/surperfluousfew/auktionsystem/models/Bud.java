package com.surperfluousfew.auktionsystem.models;

import com.sun.javafx.binding.StringFormatter;

public class Bud {

    private Kund kund;
    private Auktion auktion;
    private double belopp;
    private String tid;

    public Bud(Kund kund, Auktion auktion, double belopp, String tid) {
        this.kund = kund;
        this.auktion = auktion;
        this.belopp = belopp;
        this.tid = tid;
    }

    public Kund getKund() {
        return kund;
    }

    public Auktion getAuktion() {
        return auktion;
    }

    public double getBelopp() {
        return belopp;
    }

    public String getTid() {
        return tid;
    }

    @Override
    public String toString() {

       return String.format
                ("Namn : %s %s, Personnummer : %s \nBud  : %.02f   Tid  : %s", kund.getFornamn(),kund.getEfternamn(),kund.getPersonnummer(),getBelopp(), getTid());
        //return string;
    }
}

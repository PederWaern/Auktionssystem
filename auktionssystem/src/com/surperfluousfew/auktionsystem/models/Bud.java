package com.surperfluousfew.auktionsystem.models;

public class Bud {

    Kund kund;
    Auktion auktion;
    double belopp;
    String tid;

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
}

package com.surperfluousfew.auktionsystem.models;

public class Bud {
    private double belopp;
    private String tid;
    private Kund bidder;

    public Bud(double belopp, String tid, Kund bidder) {

        this.belopp = belopp;
        this.tid = tid;
        this.bidder = bidder;
    }

    public double getBelopp() {
        return belopp;
    }

    public void setBelopp(double belopp) {
        this.belopp = belopp;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Kund getBidder() {
        return bidder;
    }

    public void setBidder(Kund bidder) {
        this.bidder = bidder;
    }


}

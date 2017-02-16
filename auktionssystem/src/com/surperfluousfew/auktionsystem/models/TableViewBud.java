package com.surperfluousfew.auktionsystem.models;


public class TableViewBud {
    private String personnummer;
    private String fornamn;
    private String efternamn;
    private String bud;
    private String tid;

    public TableViewBud(String personnummer, String fornamn, String efternamn, String bud, String tid) {
        this.personnummer = personnummer;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.bud = bud;
        this.tid = tid;
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public String getFornamn() {
        return fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public String getBud() {
        return bud;
    }

    public String getTid() {
        return tid;
    }
}

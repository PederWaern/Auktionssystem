package com.surperfluousfew.auktionsystem.models;

public class Kund {
    private String personnummer;
    private String fornamn;
    private String efternamn;
    private String telefonnummer;
    private String epost;
    private double orderSum = 0;
    private Adress adress;

    public Kund(String personnummer, String fornamn, String efternamn, String telefonnummer, String epost, Adress adress) {
        this.personnummer = personnummer;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
        this.adress = adress;
    }

    public Kund(String personnummer, String fornamn, String efternamn, double orderSum){
        this.personnummer = personnummer;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.orderSum = orderSum;
    }
}

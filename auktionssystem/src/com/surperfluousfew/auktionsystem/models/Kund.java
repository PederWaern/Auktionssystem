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

    public String getPersonnummer() {
        return personnummer;
    }

    public String getFornamn() {
        return fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public double getOrderSum() {
        return orderSum;
    }

    public Adress getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return "Kund{" +
                "personnummer='" + personnummer + '\'' +
                ", fornamn='" + fornamn + '\'' +
                ", efternamn='" + efternamn + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", epost='" + epost + '\'' +
                ", orderSum=" + orderSum +
                ", adress=" + adress +
                '}';
    }
}

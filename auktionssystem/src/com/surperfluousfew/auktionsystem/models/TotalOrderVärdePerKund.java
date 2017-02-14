package com.surperfluousfew.auktionsystem.models;

public class TotalOrderVärdePerKund {
    private String fornamn;
    private String efternamn;
    private String personNummer;
    private double totalOrderVarde;

    public TotalOrderVärdePerKund(String fornamn, String efternamn, String personNummer, double totalOrderVarde) {
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.personNummer = personNummer;
        this.totalOrderVarde = totalOrderVarde;
    }

    public String getFornamn() {
        return fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public String getPersonNummer() {
        return personNummer;
    }

    public double getTotalOrderVarde() {
        return totalOrderVarde;
    }
}

package com.surperfluousfew.auktionsystem.models.clientSideModels;

public class TotalOrderVärdePerKund {
    private String fornamn;
    private String efternamn;
    private String personNummer;
    private String totalOrderVarde;

    public TotalOrderVärdePerKund(String fornamn, String efternamn, String personNummer, String totalOrderVarde) {
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

    public String getTotalOrderVarde() {
        return totalOrderVarde;
    }

    @Override
    public String toString() {
        return "TotalOrderVärdePerKund{" +
                "fornamn='" + fornamn + '\'' +
                ", efternamn='" + efternamn + '\'' +
                ", personNummer='" + personNummer + '\'' +
                ", totalOrderVarde=" + totalOrderVarde +
                '}';
    }
}

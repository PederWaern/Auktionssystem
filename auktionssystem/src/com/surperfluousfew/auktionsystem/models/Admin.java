package com.surperfluousfew.auktionsystem.models;

/**
 * @author Christopher Olsson on 2017-02-14.
 */
public class Admin {
    private int anstallningsnummer;
    private String personummer;
    private String fornamn;
    private String efternamn;
    private String telefonnummer;
    private String epost;
    private String losenord;
    private Adress adress;

    public Admin(int anstallningsnummer, String personummer, String fornamn, String efternamn, String telefonnummer, String epost, String losenord, Adress adress) {
        this.anstallningsnummer = anstallningsnummer;
        this.personummer = personummer;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
        this.losenord = losenord;
        this.adress = adress;
    }

    public int getAnstallningsnummer() {
        return anstallningsnummer;
    }

    public String getPersonummer() {
        return personummer;
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

    public String getLosenord() {
        return losenord;
    }

    public Adress getAdress() {
        return adress;
    }
}

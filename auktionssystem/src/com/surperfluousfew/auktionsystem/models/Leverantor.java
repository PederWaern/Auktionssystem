package com.surperfluousfew.auktionsystem.models;

public class Leverantor {


    private String organisitionsnummer;
    private String name;
    private String telefonnummer;
    private String epost;

    public Leverantor(String organisitionsnummer, String name, String telefonnummer, String epost) {
        this.organisitionsnummer = organisitionsnummer;
        this.name = name;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
    }

    public String getOrganisitionsnummer() {
        return organisitionsnummer;
    }

    public void setOrganisitionsnummer(String organisitionsnummer) {
        this.organisitionsnummer = organisitionsnummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }


}

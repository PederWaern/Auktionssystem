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

    public String getName() {
        return name;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getEpost() {
        return epost;
    }



    @Override
    public String toString() {
        return "Leverantor{" +
                "organisitionsnummer='" + organisitionsnummer + '\'' +
                ", name='" + name + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", epost='" + epost + '\'' +
                '}';
    }
}

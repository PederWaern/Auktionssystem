package com.surperfluousfew.auktionsystem.models;

public class AuktionTidsintervall {

    private String produktNamn;
    private String slutDatum;
    private double hogstaBud;
    private double beraknadProvision;
    private String provandel;

    public AuktionTidsintervall(String produktNamn, String slutDatum, double hogstaBud, double beraknadProvision, String provandel) {
        this.produktNamn = produktNamn;
        this.slutDatum = slutDatum;
        this.hogstaBud = hogstaBud;
        this.beraknadProvision = beraknadProvision;
        this.provandel = provandel;
    }

    public String getProduktNamn() {
        return produktNamn;
    }

    public String getSlutDatum() {
        return slutDatum;
    }

    public double getHogstaBud() {
        return hogstaBud;
    }

    public double getBeraknadProvision() {
        return beraknadProvision;
    }

    public String getProvandel() {
        return provandel;
    }

}

package com.surperfluousfew.auktionsystem.models.clientSideModels;

public class AuktionTidsintervall {

    private String produktNamn;
    private String slutDatum;
    private String hogstaBud;
    private String beraknadProvision;
    private String provandel;

    public AuktionTidsintervall(String produktNamn, String slutDatum, String hogstaBud, String beraknadProvision, String provandel) {
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

    public String getHogstaBud() {
        return hogstaBud;
    }

    public String getBeraknadProvision() {
        return beraknadProvision;
    }

    public String getProvandel() {
        return provandel;
    }

}

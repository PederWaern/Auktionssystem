package com.surperfluousfew.auktionsystem.models;

/**
 * Created by daniellindkarlberg on 2017-02-16.
 */
public class ProvisionPieChart {

    private String manad;
    private double provision;

    public ProvisionPieChart(String manad, String provision) {
        this.manad = manad;
        this.provision = parseInt(provision);
    }


    private double parseInt(String provision){
        String[] p = provision.split(" ");
        return Double.parseDouble(p[0]);
    }

    public String getManad() {
        return manad;
    }

    public double getProvision() {
        return provision;
    }
}

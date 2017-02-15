package com.surperfluousfew.auktionsystem.models;

/**
 * Created by daniellindkarlberg on 2017-02-15.
 */
public class ProvisionPerManad {

    private int år;
    private String manad;
    private double provision;


    public ProvisionPerManad(int år, String manad, double provision) {
        this.år = år;
        this.manad = manad;
        this.provision = provision;
    }

    public int getÅr() {
        return år;
    }

    public String getManad() {
        return manad;
    }

    public double getProvision() {
        return provision;
    }
}

package com.surperfluousfew.auktionsystem.models;

/**
 * Created by daniellindkarlberg on 2017-02-15.
 */
public class ProvisionPerManad {

    private int år;
    private String manad;
    private String provision;


    public ProvisionPerManad(int år, String manad, String provision) {
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

    public String getProvision() {
        return provision;
    }
}

package com.surperfluousfew.auktionsystem.models;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

public class Auktion {
    private Produkt produkt;
    private double acceptpris;
    private double utgangspris;
    private String startDatum;
    private String slutDatum;
    private ArrayList<Bud> budArrayList;
}

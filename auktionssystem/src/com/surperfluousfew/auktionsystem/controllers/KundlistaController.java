package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.TotalOrderVärdePerKund;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.ArrayList;

public class KundlistaController {

    TotalOrderVärdePerKund kund;
    private DatabaseLoader databaseLoader = new DatabaseLoader();
    private ObservableList<TotalOrderVärdePerKund> totalOrderVärdePerKunds;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> pnummers = new ArrayList<>();
    private ArrayList<Double> order = new ArrayList<>();


    @FXML
    ListView lvPnummer;
    @FXML
    ListView lvNamn;
    @FXML
    ListView lvOrder;




    public void initialize(){
        loadAllCustomers();
    }

    private void loadAllCustomers(){

        totalOrderVärdePerKunds  = FXCollections.observableList(databaseLoader.totalOrderVärdePerKundLista());
        for (TotalOrderVärdePerKund t :
                totalOrderVärdePerKunds) {
            names.add(t.getFornamn()+ " " + t.getEfternamn());
            pnummers.add(t.getPersonNummer());
            order.add(t.getTotalOrderVarde());
        }
        lvPnummer.setItems(FXCollections.observableList(pnummers));
        lvNamn.setItems(FXCollections.observableList(names));
        lvOrder.setItems(FXCollections.observableList(order));


    }

    private String displayMessage(TotalOrderVärdePerKund kund){
        return (kund.getPersonNummer() + "\n" + kund.getFornamn() + " " + kund.getEfternamn() + "\n"
        + kund.getTotalOrderVarde());
    }

}

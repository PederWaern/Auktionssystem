package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.TotalOrderVärdePerKund;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class KundlistaController {

    TotalOrderVärdePerKund kund;
    private DatabaseLoader databaseLoader = new DatabaseLoader();
    private ObservableList<TotalOrderVärdePerKund> totalOrderVärdePerKunds;


    @FXML
    private TableView tableView;


    public void initialize(){

        loadAllCustomers();
    }

    private void loadAllCustomers(){

        totalOrderVärdePerKunds  = FXCollections.observableList(databaseLoader.totalOrderVärdePerKundLista());

        tableView.getItems().clear();
        for (TotalOrderVärdePerKund t: totalOrderVärdePerKunds) {
            tableView.getItems().add(t);
        }



    }

    private String displayMessage(TotalOrderVärdePerKund kund){
        return (kund.getPersonNummer() + "\n" + kund.getFornamn() + " " + kund.getEfternamn() + "\n"
        + kund.getTotalOrderVarde());
    }

}

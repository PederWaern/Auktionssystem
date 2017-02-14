package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.StageHandler;
import com.surperfluousfew.auktionsystem.models.Kund;
import com.surperfluousfew.auktionsystem.models.TotalOrderVärdePerKund;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.util.ArrayList;

public class KundlistaController {

    TotalOrderVärdePerKund kund;
    private DatabaseLoader databaseLoader = new DatabaseLoader();
    private ObservableList<TotalOrderVärdePerKund> totalOrderVärdePerKunds;

    @FXML
    private Parent root;
    @FXML
    ComboBox kundLista;
    @FXML
    private Label lblOrderValue;



    public void initialize(){
        loadAllCustomers();
    }

    private void loadAllCustomers(){

        totalOrderVärdePerKunds  = FXCollections.observableList(databaseLoader.totalOrderVärdePerKundLista());

        for (TotalOrderVärdePerKund t :
                totalOrderVärdePerKunds) {
            kundLista.getItems().add(t.getFornamn() + " " + t.getEfternamn());
        }


    }

    public void kundSelected(){
        kund = (TotalOrderVärdePerKund) kundLista.getSelectionModel().getSelectedItem();
        lblOrderValue.setText(kund.getFornamn() + "\n" + kund.getEfternamn() + "\n" + kund.getPersonNummer() + "\n" + kund.getTotalOrderVarde());
    }

}

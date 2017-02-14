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
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.util.ArrayList;

public class KundlistaController {

    ArrayList<TotalOrderVärdePerKund> kunder = new ArrayList<>();
    private StageHandler stageHandler = new StageHandler();
    private Stage stage;
    private DatabaseLoader databaseLoader = new DatabaseLoader();
    private ObservableList<TotalOrderVärdePerKund> totalOrderVärdePerKunds;

    @FXML
    private Parent root;
    @FXML
    ComboBox kundLista;



    public void initialize(){

        loadAllCustomers();
    }

    private void loadAllCustomers(){

        totalOrderVärdePerKunds  = FXCollections.observableList(databaseLoader.totalOrderVärdePerKundLista());

        kundLista = new ComboBox(totalOrderVärdePerKunds);

    }

}

package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.AuktionTidsintervall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class AuktionTidsintervallController {

    private DatabaseLoader dbLoader = new DatabaseLoader();
    private ObservableList<AuktionTidsintervall> oList;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<Double> highestBids = new ArrayList<>();
    private ArrayList<String> provisionandels = new ArrayList<>();
    private ArrayList<Double> beraknadProvisions = new ArrayList<>();

    @FXML
    Button btSubmit;
    @FXML
    DatePicker dpStart;
    @FXML
    DatePicker dpSlut;
    @FXML
    TableView tableView;



    public void loadAllAuktions() {

        oList = FXCollections.observableList(dbLoader.getAuktionTidsintervall
                (dpStart.getValue().toString(), dpSlut.getValue().toString()));
            tableView.getItems().clear();
        for (AuktionTidsintervall a : oList){
            tableView.getItems().add(a);
        }
    }

}

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
    ListView lvName;
    @FXML
    ListView lvProvandel;
    @FXML
    ListView lvSlutdatum;
    @FXML
    ListView lvBerprov;
    @FXML
    ListView lvHogstaBud;

    public void initialize(){
    }

    private void loadAllAuktions() {
        oList = FXCollections.observableList(dbLoader.getAuktionTidsintervall
                (dpStart.getValue().toString(), dpSlut.getValue().toString()));
    }

    public void getAuktioner(ActionEvent actionEvent) {
        {
            clearAllLists();
            lvBerprov.getItems().clear();
            loadAllAuktions();
            for (AuktionTidsintervall a :
                    oList) {
                names.add(a.getProduktNamn());
                dates.add(a.getSlutDatum());
                highestBids.add(a.getHogstaBud());
                provisionandels.add(a.getProvandel());
                beraknadProvisions.add(a.getBeraknadProvision());
            }
            lvName.setItems(FXCollections.observableList(names));
            lvProvandel.setItems(FXCollections.observableList(provisionandels));
            lvBerprov.setItems(FXCollections.observableList(beraknadProvisions));
            lvHogstaBud.setItems(FXCollections.observableList(highestBids));
            lvSlutdatum.setItems(FXCollections.observableList(dates));
        }
    }

    private void clearAllLists(){
        lvBerprov.getItems().clear();
        lvSlutdatum.getItems().clear();
        lvHogstaBud.getItems().clear();
        lvName.getItems().clear();
        lvProvandel.getItems().clear();
    }
}

package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.clientSideModels.AuktionTidsintervall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class AuktionTidsintervallController extends StackPane {

    @FXML
    private Button btSubmit;
    @FXML
    private DatePicker dpStart;
    @FXML
    private DatePicker dpSlut;
    @FXML
    private TableView tableView;

    private DatabaseLoader dbLoader;
    private ObservableList<AuktionTidsintervall> oList;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<Double> highestBids = new ArrayList<>();
    private ArrayList<String> provisionandels = new ArrayList<>();
    private ArrayList<Double> beraknadProvisions = new ArrayList<>();

    public AuktionTidsintervallController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/auktionTidsintervall.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
    }


    public void loadAllAuktions() {

        oList = FXCollections.observableList(dbLoader.getAuktionTidsintervall
                (dpStart.getValue().toString(), dpSlut.getValue().toString()));
        tableView.getItems().clear();
        for (AuktionTidsintervall a : oList) {
            tableView.getItems().add(a);
        }
    }

}

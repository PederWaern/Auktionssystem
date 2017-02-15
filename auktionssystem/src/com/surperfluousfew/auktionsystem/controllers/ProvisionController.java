package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.ProvisionPerManad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ProvisionController {
    DatabaseLoader dbLoader = new DatabaseLoader();

    @FXML
    TableView tableView;

    public void initialize(){
        populateTable();
    }

    private void populateTable() {
        ObservableList<ProvisionPerManad> observableList = FXCollections.observableArrayList(dbLoader.getProvisionPerManad());
        tableView.getItems().addAll(observableList);
    }
}

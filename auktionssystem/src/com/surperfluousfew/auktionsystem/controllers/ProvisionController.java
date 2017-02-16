package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.ProvisionPerManad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProvisionController extends AnchorPane {

    @FXML
    private TableView tableView;

    private DatabaseLoader dbLoader;

    public ProvisionController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/provision.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
        populateTable();
    }

    private void populateTable() {
        ObservableList<ProvisionPerManad> observableList = FXCollections.observableArrayList(dbLoader.getProvisionPerManad());
        tableView.getItems().addAll(observableList);
    }
}

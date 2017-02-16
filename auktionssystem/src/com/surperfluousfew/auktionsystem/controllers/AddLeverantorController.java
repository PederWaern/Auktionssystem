package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AddLeverantorController extends GridPane {

    @FXML
    private TextField txfName, txfOnummer, txfTelnummer, txfEpost, txfProvision;

    private DatabaseLoader dbLoader;

    public AddLeverantorController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addLeverantor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
    }

    // TODO tell the user if the action was successful
    public void addLeverantor(ActionEvent actionEvent) {
        String name = txfName.getText();
        String orgnummer = txfOnummer.getText();
        String telnummer = txfTelnummer.getText();
        String epost = txfEpost.getText();
        double prov = Double.parseDouble(txfProvision.getText()) / 100;

        dbLoader.addLeverantor(name, orgnummer, telnummer, epost, prov);
    }
}

package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddLeverantorController {

    @FXML
    private TextField txfName, txfOnummer, txfTelnummer, txfEpost, txfProvision;

    private DatabaseLoader dbLoader = new DatabaseLoader();

    public void addLeverantor(ActionEvent actionEvent) {
        String name = txfName.getText();
        String orgnummer = txfOnummer.getText();
        String telnummer = txfTelnummer.getText();
        String epost = txfEpost.getText();
        double prov = Double.parseDouble(txfProvision.getText()) / 100;

        dbLoader.addLeverantor(name, orgnummer, telnummer, epost, prov);
    }
}

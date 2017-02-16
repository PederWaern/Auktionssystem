package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddLeverantorController extends AnchorPane {

    @FXML
    private TextField txfName, txfOnummer, txfTelnummer, txfEpost, txfProvision;
    @FXML
    private Text tInfo;

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

    public void addLeverantor(ActionEvent actionEvent) {
        String name = txfName.getText();
        String orgnummer = txfOnummer.getText();
        String telnummer = txfTelnummer.getText();
        String epost = txfEpost.getText();
        double prov = 1;
        if (addLeverantorInputParameterCheck()) {
            try {
                prov = Double.parseDouble(txfProvision.getText()) / 100;
            } catch (Exception e) {
                e.printStackTrace();
            }
            String message = dbLoader.addLeverantor(name, orgnummer, telnummer, epost, prov);
            tInfo.setText(message);
        } else {
            if (txfOnummer.getText().length() != 12) {
                tInfo.setText("Organisationsnummret måste vara 12 siffror långt");
            } else {
                tInfo.setText("Alla fält måste fyllas i för att lägga till en leverantör");
            }
        }
    }

    // TextField txfName, txfOnummer, txfTelnummer, txfEpost, txfProvision;
    private boolean addLeverantorInputParameterCheck() {
        if (txfName.getText().isEmpty() || txfOnummer.getText().isEmpty() || txfTelnummer.getText().isEmpty() || txfEpost.getText().isEmpty() || txfProvision.getText().isEmpty()) {
            return false;
        } else if (txfOnummer.getText().length() != 12) {
            return false;
        } else {
            return true;
        }
    }
}

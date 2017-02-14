package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AddKundController {

    @FXML
    Parent root;
    @FXML
    TextField txfFirstName;
    @FXML
    TextField txfLastName;
    @FXML
    TextField txfPnummer;
    @FXML
    TextField txfEpost;
    @FXML
    TextField txfTelnummer;
    @FXML
    TextField txfGata;
    @FXML
    TextField txfPostnummer;
    @FXML
    TextField txfOrt;
    @FXML
    Button bAddKund;

    private DatabaseLoader dbLoader = new DatabaseLoader();
    public void addKund(ActionEvent actionEvent) {

    }

}

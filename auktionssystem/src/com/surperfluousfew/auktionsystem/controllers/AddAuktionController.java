package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.StageHandler;
import com.surperfluousfew.auktionsystem.models.Auktion;
import com.surperfluousfew.auktionsystem.models.Produkt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddAuktionController {

    ArrayList<Auktion> pagaendeAuktioner = new ArrayList<>();
    ArrayList<Produkt> produkts = new ArrayList<>();

    @FXML
    Parent root;
    @FXML
    ChoiceBox cbProdukt;
    @FXML
    TextField txfUpris;
    @FXML
    TextField txfApris;
    @FXML
    DatePicker dpStart;
    @FXML
    DatePicker dpSlut;


}

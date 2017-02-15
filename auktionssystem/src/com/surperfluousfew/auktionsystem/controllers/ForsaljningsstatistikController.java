package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class ForsaljningsstatistikController {

    DatabaseLoader dbLoader = new DatabaseLoader();

    @FXML
    BorderPane root;

    public void showKunder(ActionEvent actionEvent) throws Exception {
        Parent kundlistaScene = FXMLLoader.load(getClass().getResource("/fxml/kundlista.fxml"));
        root.setCenter(kundlistaScene);
    }

    public void showAuktioner(ActionEvent actionEvent) throws Exception {
        Parent auktionScene = FXMLLoader.load(getClass().getResource("/fxml/auktionTidsinterval.fxml"));
        root.setCenter(auktionScene);
    }

    public void showProvision(ActionEvent actionEvent) throws Exception {
        Parent provisionScene = FXMLLoader.load(getClass().getResource("/fxml/provision.fxml"));
        root.setCenter(provisionScene);
    }
}

package com.surperfluousfew.auktionsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddAuktionController {

    Stage stage;

    @FXML
    Parent root;

    public void goBack(ActionEvent actionEvent) throws Exception {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(homeScreen));
    }

    private Stage getParentStage(Parent parent){
        return (Stage) parent.getScene().getWindow();
    }
}

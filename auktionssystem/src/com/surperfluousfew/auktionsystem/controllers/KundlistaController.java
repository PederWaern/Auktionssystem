package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.StageHandler;
import com.surperfluousfew.auktionsystem.models.Kund;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KundlistaController {

    ArrayList<Kund> kunder = new ArrayList<>();
    private StageHandler stageHandler = new StageHandler();
    private Stage stage;

    @FXML
    Parent root;

    private void loadAllCustomers(){

    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(homeScreen));
    }

}

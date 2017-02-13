package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.StageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    StageHandler stageHandler = new StageHandler();
    @FXML
    Parent root;
    @FXML
    TextField txfEmail;
    @FXML
    TextField txfPassword;


    public void logIn(ActionEvent actionEvent) throws Exception {
        String email = txfEmail.getText();
        String password = txfPassword.getText();
        if (true){
            Parent homeScreen = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
            Stage oldStage = stageHandler.getParentStage(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Auktionsystem");
            primaryStage.setHeight(1000);
            primaryStage.setWidth(1000);
            primaryStage.setScene(new Scene(homeScreen));
            primaryStage.setResizable(false);
            primaryStage.show();
            oldStage.close();


            //primaryStage.show();
        }
    }
}

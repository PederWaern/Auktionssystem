package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.StageHandler;
import com.surperfluousfew.auktionsystem.models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
    private DatabaseLoader dbLoader = new DatabaseLoader();
    StageHandler stageHandler = new StageHandler();
    @FXML
    Parent root;
    @FXML
    TextField txfAnstallninsnummer;
    @FXML
    TextField txfPassword;


    public void logIn(ActionEvent actionEvent) throws Exception {
        String loginAnstallningsnummer = txfAnstallninsnummer.getText();
        String loginLosenord = txfPassword.getText();
        dbLoader.loadAdmins();
        for (Admin admin : dbLoader.getAdmins()) {
            String adminAnstallningsnummer = String.valueOf(admin.getAnstallningsnummer());
            if (adminAnstallningsnummer.equals(loginAnstallningsnummer)&& admin.getLosenord().equals(loginLosenord)){
                Parent homeScreen = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
                Stage oldStage = stageHandler.getParentStage(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Auktionsystem");
                primaryStage.setScene(new Scene(homeScreen));
                primaryStage.setResizable(false);
                primaryStage.show();
                oldStage.close();
            }
        }
    }
}

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController extends VBox {

    @FXML
    private Parent root;
    @FXML
    private TextField txfAnstallninsnummer, txfPassword;
    @FXML
    private Text tInfo;

    private DatabaseLoader dbLoader;
    private StageHandler stageHandler = new StageHandler();

    public LogInController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/logIn.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
    }

    @FXML
    public void logIn(ActionEvent actionEvent) throws Exception {
        String loginAnstallningsnummer = txfAnstallninsnummer.getText();
        String loginLosenord = txfPassword.getText();
        dbLoader.loadAdmins();
        for (Admin admin : dbLoader.getAdmins()) {
            String adminAnstallningsnummer = String.valueOf(admin.getAnstallningsnummer());
            if (adminAnstallningsnummer.equals(loginAnstallningsnummer) && admin.getLosenord().equals(loginLosenord)) {
                //Parent homeScreen = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
                HomeController homeController = new HomeController(dbLoader);
                Stage oldStage = stageHandler.getParentStage(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Auktionsystem");
                primaryStage.setHeight(600);
                primaryStage.setWidth(800);
                primaryStage.setScene(new Scene(homeController));
                primaryStage.setResizable(false);
                primaryStage.show();
                oldStage.close();
            } else {
                tInfo.setText("Felaktiga inloggningsuppgifter");
            }
        }
    }
}

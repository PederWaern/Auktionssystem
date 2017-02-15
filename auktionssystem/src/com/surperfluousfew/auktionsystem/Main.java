package com.surperfluousfew.auktionsystem;

import com.surperfluousfew.auktionsystem.models.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DatabaseLoader db = new DatabaseLoader();
        db.loadAddresses();
        db.loadAdmins();
        for (Admin admin : db.getAdmins()) {
            System.out.println(admin.getFornamn() + " " + admin.getEfternamn());
        }


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/logIn.fxml"));
        primaryStage.setTitle("Auktionsystem");
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}

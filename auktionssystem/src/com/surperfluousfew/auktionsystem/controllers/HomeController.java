package com.surperfluousfew.auktionsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    private Stage stage;
    final static int SCENE_WIDTH = 1000;
    final static int SCENE_HEIGHT = 1000;

    @FXML
    Parent root;

    /*@FXML
    Button bPagaendeAuktioner;
    @FXML
    Button bAddAuktion;
    @FXML
    Button bAddLev;
    @FXML
    Button bAddKund;*/


    public void setScenePGA(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/pagaendeAuktioner.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }


    public void setSceneAddAuktion(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addAuktion.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    public void setSceneAddKund(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addKund.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    private Stage getParentStage(Parent parent){
        return (Stage) parent.getScene().getWindow();
    }

    public void setSceneBudHistorik(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/budHistorik.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    public void setSceneKundLista(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/kundlista.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    public void setSceneAddLeverantor(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addLeverantor.fxml"));
        stage = getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }
}

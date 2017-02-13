package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.StageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    final static int SCENE_WIDTH = 1000;
    final static int SCENE_HEIGHT = 1000;

    StageHandler stageHandler = new StageHandler();
    private Stage stage;
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
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }


    public void setSceneAddAuktion(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addAuktion.fxml"));
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    public void setSceneAddKund(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addKund.fxml"));
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }



    public void setSceneBudHistorik(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/budHistorik.fxml"));
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    public void setSceneKundLista(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/kundlista.fxml"));
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }

    public void setSceneAddLeverantor(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addLeverantor.fxml"));
        stage = stageHandler.getParentStage(root);
        stage.setScene(new Scene(addAuktionScene, SCENE_WIDTH, SCENE_HEIGHT));
    }
}

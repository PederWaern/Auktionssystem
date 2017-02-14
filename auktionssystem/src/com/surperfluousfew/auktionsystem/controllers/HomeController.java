package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.StageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomeController {

    final static int SCENE_WIDTH = 1000;
    final static int SCENE_HEIGHT = 1000;

    StageHandler stageHandler = new StageHandler();
    private Stage stage;
    @FXML
    BorderPane root;
    @FXML
    GridPane gpHome;

    public void setScenePGA(ActionEvent actionEvent) throws Exception {
        Parent pgaScene = FXMLLoader.load(getClass().getResource("/fxml/pagaendeAuktioner.fxml"));
        root.setCenter(pgaScene);
    }

    public void setSceneAddAuktion(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addAuktion.fxml"));
        root.setCenter(addAuktionScene);
    }

    public void setSceneAddKund(ActionEvent actionEvent) throws Exception{
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addKund.fxml"));
        root.setCenter(addAuktionScene);
    }

    public void setSceneBudHistorik(ActionEvent actionEvent) throws Exception{
        Parent budHistorikScene = FXMLLoader.load(getClass().getResource("/fxml/budHistorik.fxml"));
        root.setCenter(budHistorikScene);
    }

    public void setSceneKundLista(ActionEvent actionEvent) throws Exception {
        Parent kundListaScene = FXMLLoader.load(getClass().getResource("/fxml/kundlista.fxml"));
        root.setCenter(kundListaScene);
    }

    public void setSceneAddLeverantor(ActionEvent actionEvent) throws Exception {
        Parent addLeverantorScene = FXMLLoader.load(getClass().getResource("/fxml/addLeverantor.fxml"));
        root.setCenter(addLeverantorScene);
    }

    public void setSceneForsaljning(ActionEvent actionEvent) throws Exception{
        Parent addLeverantorScene = FXMLLoader.load(getClass().getResource("/fxml/forsaljningsstatistik.fxml"));
        root.setCenter(addLeverantorScene);
    }
}

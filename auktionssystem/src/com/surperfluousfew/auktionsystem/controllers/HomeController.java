package com.surperfluousfew.auktionsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HomeController {

    @FXML
    AnchorPane root;
    @FXML
    GridPane gpHome;

    public void setScenePGA(ActionEvent actionEvent) throws Exception {
        Parent pgaScene = FXMLLoader.load(getClass().getResource("/fxml/pagaendeAuktioner.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pgaScene);
    }

    public void setSceneAddAuktion(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addAuktion.fxml"));
        root.getChildren().clear();
        root.getChildren().add(addAuktionScene);
    }

    public void setSceneAddKund(ActionEvent actionEvent) throws Exception {
        Parent addAuktionScene = FXMLLoader.load(getClass().getResource("/fxml/addKund.fxml"));
        root.getChildren().clear();
        root.getChildren().add(addAuktionScene);
    }

    public void setSceneBudHistorik(ActionEvent actionEvent) throws Exception {
        Parent budHistorikScene = FXMLLoader.load(getClass().getResource("/fxml/budHistorik.fxml"));
        root.getChildren().clear();
        root.getChildren().add(budHistorikScene);
    }

    public void setSceneKundLista(ActionEvent actionEvent) throws Exception {
        Parent kundListaScene = FXMLLoader.load(getClass().getResource("/fxml/kundlista.fxml"));
        root.getChildren().clear();
        root.getChildren().add(kundListaScene);
    }

    public void setSceneAddLeverantor(ActionEvent actionEvent) throws Exception {
        Parent addLeverantorScene = FXMLLoader.load(getClass().getResource("/fxml/addLeverantor.fxml"));
        root.getChildren().clear();
        root.getChildren().add(addLeverantorScene);
    }

    public void setSceneForsaljning(ActionEvent actionEvent) throws Exception {
        Parent addLeverantorScene = FXMLLoader.load(getClass().getResource("/fxml/forsaljningsstatistik.fxml"));
        root.getChildren().clear();
        root.getChildren().add(addLeverantorScene);
    }
}

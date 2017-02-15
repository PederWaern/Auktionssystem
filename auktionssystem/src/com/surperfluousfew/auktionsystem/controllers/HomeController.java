package com.surperfluousfew.auktionsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class HomeController {

    @FXML
    AnchorPane root;

    public void setScenePGA(ActionEvent actionEvent) throws Exception {
        Parent pgaScene = FXMLLoader.load(getClass().getResource("/fxml/pagaendeAuktioner.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pgaScene);
    }

    public void setSceneAddAuktion(ActionEvent actionEvent) throws Exception {
        Parent auktionScene = FXMLLoader.load(getClass().getResource("/fxml/addAuktion.fxml"));
        root.getChildren().clear();
        root.getChildren().add(auktionScene);
    }

    public void setSceneAddKund(ActionEvent actionEvent) throws Exception {
        Parent auktionScene = FXMLLoader.load(getClass().getResource("/fxml/addKund.fxml"));
        root.getChildren().clear();
        root.getChildren().add(auktionScene);
    }

    public void setSceneKundLista(ActionEvent actionEvent) throws Exception {
        Parent kundListaScene = FXMLLoader.load(getClass().getResource("/fxml/kundlista.fxml"));
        root.getChildren().clear();
        root.getChildren().add(kundListaScene);
    }

    public void setSceneAddLeverantor(ActionEvent actionEvent) throws Exception {
        Parent leverantorScene = FXMLLoader.load(getClass().getResource("/fxml/addLeverantor.fxml"));
        root.getChildren().clear();
        root.getChildren().add(leverantorScene);
    }

    public void setSceneAuktioner(ActionEvent actionEvent) throws Exception{
        Parent auktionScene = FXMLLoader.load(getClass().getResource("/fxml/auktionTidsinterval.fxml"));
        root.getChildren().clear();
        root.getChildren().add(auktionScene);
    }

    public void setSceneProvision(ActionEvent actionEvent) throws Exception {
        Parent provisionScene = FXMLLoader.load(getClass().getResource("/fxml/provision.fxml"));
        root.getChildren().clear();
        root.getChildren().add(provisionScene);
    }
}

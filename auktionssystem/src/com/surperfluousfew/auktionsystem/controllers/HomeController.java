package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController extends AnchorPane {
    @FXML
    private AnchorPane root;

    private DatabaseLoader dbLoader;

    public HomeController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
    }

    public void setScenePGA(ActionEvent actionEvent) throws Exception {
        PagaendeAuktionerController pagaendeAuktionerController = new PagaendeAuktionerController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(pagaendeAuktionerController);
    }

    public void setSceneAddAuktion(ActionEvent actionEvent) throws Exception {
        AddAuktionController addAuktionController = new AddAuktionController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(addAuktionController);
    }

    public void setSceneAddKund(ActionEvent actionEvent) throws Exception {
        AddKundController addKundController = new AddKundController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(addKundController);
    }

    public void setSceneKundLista(ActionEvent actionEvent) throws Exception {
        KundlistaController kundlistaController = new KundlistaController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(kundlistaController);
    }

    public void setSceneAddLeverantor(ActionEvent actionEvent) throws Exception {
        AddLeverantorController addLeverantorController = new AddLeverantorController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(addLeverantorController);
    }

    public void setSceneAuktioner(ActionEvent actionEvent) throws Exception {
        AuktionTidsintervallController auktionTidsintervallController = new AuktionTidsintervallController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(auktionTidsintervallController);
    }

    public void setSceneProvision(ActionEvent actionEvent) throws Exception {
        ProvisionController provisionController = new ProvisionController(dbLoader);
        root.getChildren().clear();
        root.getChildren().add(provisionController);
    }
}

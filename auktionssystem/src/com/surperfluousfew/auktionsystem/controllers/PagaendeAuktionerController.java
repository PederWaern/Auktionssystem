package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.StageHandler;
import com.surperfluousfew.auktionsystem.models.Auktion;
import com.surperfluousfew.auktionsystem.models.Bud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PagaendeAuktionerController {

    Stage stage;
    StageHandler stageHandler = new StageHandler();

    @FXML
    Parent root;
    @FXML
    ChoiceBox cbAuktion;
    @FXML
    ListView listView;

    private List<Auktion> arrayAuktion;


    @FXML
    public void initialize(){
        DatabaseLoader dbLoader = new DatabaseLoader();
        dbLoader.loadLeverantor();
        dbLoader.loadAddresses();
        dbLoader.loadKund();
        dbLoader.loadProdukt();
        dbLoader.loadAuktion();
        dbLoader.loadBud();
        dbLoader.setAuktionsBud();
        this.arrayAuktion = dbLoader.getAuktioner();

        for (Auktion a : arrayAuktion){
            cbAuktion.getItems().add(a.getProdukt().getNamn());
        }
        cbAuktion.getSelectionModel().selectFirst();

        System.out.println("inne i initialize");
    }



    public void populateView() {

        ObservableList<Bud> items = FXCollections.observableArrayList ();
        String förnamn, efternamn, personnummer, belopp, tid;
        for (Auktion b : arrayAuktion) {
            if (b.getProdukt().getNamn().equals(cbAuktion.getValue().toString()))
            for (Bud bud: b.getBudArrayList()  ) {
                items.add(bud);

            }

        }

        listView.setItems(items);
    }
}

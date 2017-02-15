package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.StageHandler;
import com.surperfluousfew.auktionsystem.models.Bud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class BudHistorikController {

    Stage stage;
    StageHandler stageHandler = new StageHandler();

    @FXML
    Parent root;
    @FXML
    ChoiceBox cbAuktion;
    @FXML
    ListView listView;

    public void populateView(ActionEvent actionEvent) {
        DatabaseLoader dbLoader = new DatabaseLoader();
        dbLoader.loadAddresses();
        dbLoader.loadLeverantor();
        dbLoader.loadKund();
        dbLoader.loadBud();
        List<Bud> budList = dbLoader.getBud();


        ObservableList<String> items = FXCollections.observableArrayList ();
        String förnamn, efternamn, personnummer, belopp, tid;
        for (Bud b : budList) {
            if (b.getAuktion().getId() == Integer.parseInt(cbAuktion.getValue().toString())){
            förnamn = b.getKund().getFornamn();
            efternamn = b.getKund().getEfternamn();
            personnummer = b.getKund().getPersonnummer();
            belopp = String.valueOf(b.getBelopp());
            tid = b.getTid();
            items.add(personnummer + " "  +förnamn + " " + efternamn + "\n" + tid + "   " + belopp + ":-");}
        }

        listView.setItems(items);
    }
}

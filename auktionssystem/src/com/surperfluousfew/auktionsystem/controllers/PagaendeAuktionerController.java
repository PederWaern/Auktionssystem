package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.Auktion;
import com.surperfluousfew.auktionsystem.models.Bud;
import com.surperfluousfew.auktionsystem.models.Kund;
import com.surperfluousfew.auktionsystem.models.TableViewBud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.util.List;

public class PagaendeAuktionerController {

    @FXML
    Parent root;
    @FXML
    ChoiceBox cbAuktion;
    @FXML
    TableView tableView;

    private List<Auktion> arrayAuktion;


    @FXML
    public void initialize() {
        DatabaseLoader dbLoader = new DatabaseLoader();
        dbLoader.loadLeverantor();
        dbLoader.loadAddresses();
        dbLoader.loadKund();
        dbLoader.loadProdukt();
        dbLoader.loadAuktion();
        dbLoader.loadBud();
        dbLoader.setAuktionsBud();
        this.arrayAuktion = dbLoader.getAuktioner();

        for (Auktion a : arrayAuktion) {
            cbAuktion.getItems().add(a.getProdukt().getNamn());
        }
        cbAuktion.getSelectionModel().selectFirst();
    }


    public void populateView() {
        ObservableList<TableViewBud> items = FXCollections.observableArrayList();
        for (Auktion b : arrayAuktion) {
            if (b.getProdukt().getNamn().equals(cbAuktion.getValue().toString()))
                for (Bud bud : b.getBudArrayList()) {
                    items.add(new TableViewBud(bud.getKund().getPersonnummer(),
                            bud.getKund().getFornamn(), bud.getKund().getEfternamn()
                            , bud.getBelopp(), bud.getTid()));

                }

        }
        tableView.getItems().clear();
        tableView.getItems().addAll(items);
    }
}

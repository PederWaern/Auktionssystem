package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.Auktion;
import com.surperfluousfew.auktionsystem.models.Bud;
import com.surperfluousfew.auktionsystem.models.clientSideModels.TableViewBud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class PagaendeAuktionerController extends StackPane {

    @FXML
    Parent root;
    @FXML
    ChoiceBox cbAuktion;
    @FXML
    TableView tableView;
    private DatabaseLoader dbLoader;
    private List<Auktion> arrayAuktion;

    public PagaendeAuktionerController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/pagaendeAuktioner.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
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
                            , bud.getBelopp() + " kr", bud.getTid()));

                }

        }
        tableView.getItems().clear();
        tableView.getItems().addAll(items);
    }
}

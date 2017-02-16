package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.TotalOrderVärdePerKund;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class KundlistaController extends AnchorPane {

    @FXML
    TableColumn sortCol;
    @FXML
    private TableView tableView;

    private TotalOrderVärdePerKund kund;
    private DatabaseLoader dbLoader;
    private ObservableList<TotalOrderVärdePerKund> totalOrderVärdePerKunds;


    public KundlistaController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kundlista.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
        loadAllCustomers();
        sortCol.setSortType(TableColumn.SortType.DESCENDING);
    }

    private void loadAllCustomers() {

        totalOrderVärdePerKunds = FXCollections.observableList(dbLoader.totalOrderVärdePerKundLista());

        tableView.getItems().clear();
        for (TotalOrderVärdePerKund t : totalOrderVärdePerKunds) {
            tableView.getItems().add(t);
        }


    }

    private String displayMessage(TotalOrderVärdePerKund kund) {
        return (kund.getPersonNummer() + "\n" + kund.getFornamn() + " " + kund.getEfternamn() + "\n"
                + kund.getTotalOrderVarde());
    }

}

package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.clientSideModels.ProvisionPerManad;
import com.surperfluousfew.auktionsystem.models.clientSideModels.ProvisionPieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProvisionController extends AnchorPane {

    @FXML
    private TableView tableView;
    @FXML
    private PieChart pieChart;

    private DatabaseLoader dbLoader;
    private ObservableList<ProvisionPerManad> provisionPerManads;


    public ProvisionController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/provision.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
        populateTable();
        populatePieChart();
    }

    private void populateTable() {
        provisionPerManads = FXCollections.observableArrayList(dbLoader.loadProvisionPerManad());
        tableView.getItems().addAll(provisionPerManads);
    }

    private void populatePieChart(){

        ObservableList<ProvisionPieChart> observablePieChartList = FXCollections.observableArrayList();

        for (ProvisionPerManad provisionPerManad: provisionPerManads
             ) {
            observablePieChartList.add(new ProvisionPieChart(provisionPerManad.getManad(), provisionPerManad.getProvision()));
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (ProvisionPieChart p: observablePieChartList
             ) {
            pieChartData.add(new PieChart.Data(p.getManad(), p.getProvision()));

        }
        pieChart.setData(pieChartData);
    }

}





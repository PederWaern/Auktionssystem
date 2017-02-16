package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.Produkt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddAuktionController extends GridPane {
    @FXML
    private ChoiceBox cbProdukt;
    @FXML
    private TextField txfUpris, txfApris;
    @FXML
    private DatePicker dpStart, dpSlut;
    @FXML
    private Text tInfo;

    private DatabaseLoader dbLoader;
    private List<Produkt> produkter;
    private Pattern doublePattern = Pattern.compile("\\d+\\.*\\d+");

    public AddAuktionController(DatabaseLoader dbLoader) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addAuktion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dbLoader = dbLoader;
        loadProducts();
        showProducts();
    }

    private void loadProducts() {
        dbLoader.loadLeverantor();
        dbLoader.loadProdukt();
        produkter = dbLoader.getProdukter();
    }

    private void showProducts() {
        ObservableList<Produkt> produktlista = FXCollections.observableArrayList(produkter);
        cbProdukt.setItems(produktlista);
        cbProdukt.getSelectionModel().selectFirst();
    }

    public void add(ActionEvent actionEvent) {
        Produkt produkt = (Produkt) cbProdukt.getSelectionModel().getSelectedItem();
        String message;
        String strUtgangspris = txfUpris.getText();
        String strAcceptpris = txfApris.getText();
        Matcher utMatcher = doublePattern.matcher(strUtgangspris);
        Matcher acMatcher = doublePattern.matcher(strAcceptpris);
        if (utMatcher.matches()) {
            double utgangspris = Double.parseDouble(strUtgangspris);
            Double acceptpris = (acMatcher.matches()) ? Double.parseDouble(strAcceptpris) : null;
            String startdatum = (dpStart.getValue() != null) ? String.valueOf(dpStart.getValue()) : null;
            String slutdatum = (dpSlut.getValue() != null) ? String.valueOf(dpSlut.getValue()) : null;
            if (startdatum != null && slutdatum != null) {
                message = dbLoader.addNewAuction(produkt.getId(), utgangspris, acceptpris, startdatum, slutdatum);
            } else {
                message = "Ange ett startdatum och ett slutdatum";
            }
        } else {
            message = "Felaktigt utgångspris eller acceptpris";
        }
        tInfo.setText(message);
    }
}

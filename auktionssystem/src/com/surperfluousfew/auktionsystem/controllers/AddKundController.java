package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.Adress;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;


public class AddKundController {

    @FXML
    Parent root;
    @FXML
    TextField txfFirstName;
    @FXML
    TextField txfLastName;
    @FXML
    TextField txfPnummer;
    @FXML
    TextField txfEpost;
    @FXML
    TextField txfTelnummer;
    @FXML
    TextField txfGata;
    @FXML
    TextField txfPostnummer;
    @FXML
    TextField txfOrt;
    @FXML
    Button bAddKund;
    @FXML
    Label labelNyKundStatus;

    private DatabaseLoader dbLoader = new DatabaseLoader();

    public void addKund(ActionEvent actionEvent) {
        getNewKundDataFromTextfields();
    }

    private void getNewKundDataFromTextfields() {
        // adress input
        String inPersonnummer = txfPnummer.getText();
        String inFornamn = txfFirstName.getText();
        String inEfternamn = txfLastName.getText();
        String inTelefonnummer = txfTelnummer.getText();
        String inEpost = txfEpost.getText();
        String inGata = txfGata.getText();
        String inPostnummer = txfPostnummer.getText();
        String inOrt = txfOrt.getText();
        if (addKundInputParameterCheck()) {
            int addressId = createNewOrUseExistingAddress(inGata, inPostnummer, inOrt);
            dbLoader.addNewKundToDatabase(inPersonnummer, inFornamn, inEfternamn, inTelefonnummer, inEpost, addressId);
        }
    }

    private int createNewOrUseExistingAddress(String inGata, String inPostunmmer, String inOrt) {
        dbLoader.loadAddresses();
        List<Adress> adressList = dbLoader.getAddresses();
        for (Adress adress : adressList) {
            if (adress.getGata().equals(inGata) && adress.getPostnummer().equals(inPostunmmer) && adress.getOrt().equals(inOrt)) {
                return adress.getId();
            }
        }
        dbLoader.addNewAddressToDatabase(inGata, inPostunmmer, inOrt);
        dbLoader.loadAddresses();
        adressList = dbLoader.getAddresses();
        for (Adress adress : adressList) {
            if (adress.getGata().equals(inGata) && adress.getPostnummer().equals(inPostunmmer) && adress.getOrt().equals(inOrt)) {
                return adress.getId();
            }
        }
        return 0;
    }

    /**
     * Warning may contain tedious if-statemenets...
     */
    private boolean addKundInputParameterCheck() {
        if (txfPnummer.getText().isEmpty() || txfFirstName.getText().isEmpty() || txfLastName.getText().isEmpty() || txfTelnummer.getText().isEmpty() ||
                txfEpost.getText().isEmpty() || txfGata.getText().isEmpty() || txfPostnummer.getText().isEmpty() || txfOrt.getText().isEmpty()) {
            labelNyKundStatus.setText("Misslyckades att skapa ny kund, alla fält måste fyllas i");
            return false;
        } else if (txfPnummer.getText().length() != 10) {
            labelNyKundStatus.setText("Misslyckades att skapa ny kund, personnummer får endast innehålla 10 tecken");
            return false;
        } else if (txfPostnummer.getText().length() != 5) {
            labelNyKundStatus.setText("Misslyckades att skapa ny kund, postnummer får endast innehålla 5 tecken");
            return false;
        } else {
            labelNyKundStatus.setText("skapar kund med inmatade parametrar");
            return true;
        }
    }

}

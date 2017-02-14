package com.surperfluousfew.auktionsystem.controllers;

import com.surperfluousfew.auktionsystem.DatabaseLoader;
import com.surperfluousfew.auktionsystem.models.Adress;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
        int addressId = createNewOrUseExistingAddress(inGata, inPostnummer, inOrt);
//        System.out.printf("%s %s %s %s %s", inPersonnummer, inFornamn, inEfternamn, inTelefonnummer, inEpost);
        System.out.println(addressId);

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

}

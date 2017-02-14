package com.surperfluousfew.auktionsystem;

import com.surperfluousfew.auktionsystem.models.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseLoader {

    private Properties properties = new Properties();
    private FileInputStream in = null;
    private String url;
    private String username;
    private String password;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private List<Admin> admins = null;
    private List<Kund> kunder = null;
    private List<Adress> addresses = null;
    private List<Leverantor> leverantorer = null;
    private List<Produkt> produkter = null;
    private List<Auktion> auktioner = null;
    private List<Bud> bud = null;



    public DatabaseLoader() {
        try {
            FileInputStream in = new FileInputStream("auktionssystem/configuration/db.properties");
            properties.load(in);

            String driver = properties.getProperty("jdbc.driver");
            if (driver != null) {
                Class.forName(driver);
            }

            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void setup() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void loadLeverantor(){
        leverantorer = new ArrayList<>();
        setup();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM leverantor");
            resultSet = statement.getResultSet();
            while (resultSet.next()){

                leverantorer.add(new Leverantor
                        (resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources();
        }
    }

public void loadProdukt(){
        produkter = new ArrayList<>();
        setup();
    try {
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM produkt ");
        resultSet = statement.getResultSet();
        while (resultSet.next()){

             int id = resultSet.getInt(1);
            String namn = resultSet.getString(3);
             String beskrivning = resultSet.getString(4);
             String bildNamn = resultSet.getString(5);
             Leverantor leverantor = null;
            for (Leverantor l: leverantorer
                 ) {
                if(l.getOrganisitionsnummer().equals(resultSet.getString(2))){

                    leverantor = l;
                }
            }

            produkter.add(new Produkt(id, leverantor, namn, beskrivning, bildNamn));

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        closeResources();
    }
}

public void loadBud() {
    bud = new ArrayList<>();
    setup();

    try {
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM bud");
        resultSet = statement.getResultSet();
        while (resultSet.next()){
            Kund kund = null;
            Auktion auktion = null;
            double belopp = resultSet.getDouble(3);
            String tid = resultSet.getString(4);

            for (Kund k: kunder) {
                if (k.getPersonnummer().equals(resultSet.getString(1))) {
                    kund = k;
                }
            }

            for (Auktion a : auktioner) {
                if (a.getId() == resultSet.getInt(2)) {
                    auktion = a;
                }
            }

            bud.add(new Bud(kund, auktion, belopp, tid));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        closeResources();
    }
}

private void setAuktionsBud() {

}

public void loadAuktion(){
    auktioner = new ArrayList<>();
    setup();
    try {
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM auktion");
        resultSet = statement.getResultSet();
        while (resultSet.next()){

            int id = resultSet.getInt(1);
            Produkt produkt  = null;
            double acceptPris = resultSet.getDouble(3);
            double utgangsPris = resultSet.getDouble(4);
            String startDatum = resultSet.getString(5);
            String slutDatum = resultSet.getString(6);

            for (Produkt p: produkter
                 ) {
                if(p.getId() == resultSet.getInt(2)){

                    produkt = p;

                }
            }

            auktioner.add(new Auktion(id, produkt, acceptPris, utgangsPris, startDatum, slutDatum));

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        closeResources();
    }




}

    public void loadAddresses() {
        addresses = new ArrayList<>();
        setup();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM adress");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                addresses.add(new Adress(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void loadKund(){
        kunder = new ArrayList<>();
        setup();

        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT  * FROM kund");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {

                String personNummer = resultSet.getString(1);
                String forNamn = resultSet.getString(2);
                String efterNamn = resultSet.getString(3);
                String telefonNummer = resultSet.getString(4);
                String epost = resultSet.getString(5);
                Adress adress = null;
                for (Adress a : addresses
                        ) {
                    if (a.getId() == resultSet.getInt(6)) {
                        adress = a;
                    }
                }

                kunder.add(new Kund(personNummer, forNamn, efterNamn, telefonNummer, epost, adress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources();
        }
    }


    public void loadAdmins() {
        admins = new ArrayList<>();
        setup();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM admin");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                admins.add(new Admin(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        getAddress(resultSet.getInt(7))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private Adress getAddress(int addressId) {
        for (Adress adress : addresses) {
            if(adress.getId() == addressId) {
                return adress;
            }
        }
        return null;
    }

    public List<Admin> getAdmins() {
        return admins;
    }


    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

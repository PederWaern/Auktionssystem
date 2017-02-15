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
    private CallableStatement callableStatement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<Admin> admins = null;
    private List<Kund> kunder = null;
    private List<Adress> addresses = null;
    private List<Leverantor> leverantorer = null;
    private List<Produkt> produkter = null;
    private List<Auktion> auktioner = null;
    private List<Bud> bud = null;

    public List<Bud> getBud() {
        return bud;
    }

    public List<Kund> getKunder() {
        return kunder;
    }

    public List<Adress> getAddresses() {
        return addresses;
    }

    public List<Leverantor> getLeverantorer() {
        return leverantorer;
    }

    public List<Produkt> getProdukter() {
        return produkter;
    }

    public List<Auktion> getAuktioner() {
        return auktioner;
    }

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


    public void loadLeverantor() {
        leverantorer = new ArrayList<>();
        setup();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM leverantor");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {

                leverantorer.add(new Leverantor
                        (resultSet.getString(1),
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

    public void loadProdukt() {
        produkter = new ArrayList<>();
        setup();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM produkt ");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String namn = resultSet.getString(3);
                String beskrivning = resultSet.getString(4);
                String bildNamn = resultSet.getString(5);
                Leverantor leverantor = null;
                for (Leverantor l : leverantorer
                        ) {
                    if (l.getOrganisitionsnummer().equals(resultSet.getString(2))) {

                        leverantor = l;
                    }
                }

                produkter.add(new Produkt(id, leverantor, namn, beskrivning, bildNamn));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
            while (resultSet.next()) {
                Kund kund = null;
                Auktion auktion = null;
                double belopp = resultSet.getDouble(3);
                String tid = resultSet.getString(4);

                for (Kund k : kunder) {
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
        } finally {
            closeResources();
        }
    }

    public void setAuktionsBud() {

        for (Auktion a :
                auktioner) {
            for (Bud b : bud) {
                if (b.getAuktion().getId() == a.getId())
                    a.getBudArrayList().add(b);
            }
        }
    }

    public void loadAuktion() {
        auktioner = new ArrayList<>();
        setup();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM auktion");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                Produkt produkt = null;
                double acceptPris = resultSet.getDouble(3);
                double utgangsPris = resultSet.getDouble(4);
                String startDatum = resultSet.getString(5);
                String slutDatum = resultSet.getString(6);

                for (Produkt p : produkter
                        ) {
                    if (p.getId() == resultSet.getInt(2)) {

                        produkt = p;

                    }
                }
                auktioner.add(new Auktion(id, produkt, acceptPris, utgangsPris, startDatum, slutDatum));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public void loadKund() {
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
                Adress adress = getAddress(resultSet.getInt(6));

                kunder.add(new Kund(personNummer, forNamn, efterNamn, telefonNummer, epost, adress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }


    public void loadAdmins() {
        admins = new ArrayList<>();
        loadAddresses();
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
            if (adress.getId() == addressId) {
                return adress;
            }
        }
        return null;
    }

    public List<Admin> getAdmins() {
        return admins;
    }


    public ArrayList<TotalOrderVärdePerKund> totalOrderVärdePerKundLista () {
        setup();
        ArrayList<TotalOrderVärdePerKund> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM total_order_value_per_customer");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
               String fornamn = resultSet.getString(1);
               String efternamn = resultSet.getString(2);
               String personNummer = resultSet.getString(3);
               double totaltOrderVärde = resultSet.getDouble(4);

               list.add(new TotalOrderVärdePerKund(fornamn,efternamn,personNummer, totaltOrderVärde));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    public void addNewAddressToDatabase(String gata, String postnummer, String ort) {
        setup();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO adress (gata, postnummer, ort) VALUES (?,?,?)");
            preparedStatement.setString(1, gata);
            preparedStatement.setString(2, postnummer);
            preparedStatement.setString(3, ort);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void addNewKundToDatabase(String personnummer, String fornamn, String efternamn, String telefonnummer, String epost, int addressId) {
        setup();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO kund (personnummer, fornamn, efternamn, telefonnummer, epost, address_id) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, personnummer);
            preparedStatement.setString(2, fornamn);
            preparedStatement.setString(3, efternamn);
            preparedStatement.setString(4, telefonnummer);
            preparedStatement.setString(5, epost);
            preparedStatement.setInt(6, addressId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public String addNewAuction(int produktId, double utgangspris, Double acceptpris, String startdatum, String slutdatum) {
        setup();
        try {
            callableStatement = connection.prepareCall("{call lagg_till_auktion (?,?,?,?,?,?)}");
            callableStatement.setInt(1, produktId);
            callableStatement.setDouble(2, utgangspris);
            if (acceptpris == null) {
                callableStatement.setNull(3, Types.DOUBLE);
            } else {
                callableStatement.setDouble(3, acceptpris);
            }
            callableStatement.setDate(4, Date.valueOf(startdatum));
            callableStatement.setDate(5, Date.valueOf(slutdatum));
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.executeUpdate();
            return callableStatement.getString(6);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    public void addLeverantor(String name, String orgnummer, String telnummer, String epost, double prov){
        setup();
        try {
            callableStatement = connection.prepareCall("{call lagg_till_leverantor (?,?,?,?,?)}");
            callableStatement.setString(1, orgnummer);
            callableStatement.setString(2, name);
            callableStatement.setString(3, telnummer);
            callableStatement.setString(4, epost);
            callableStatement.setDouble(5, prov);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }


    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

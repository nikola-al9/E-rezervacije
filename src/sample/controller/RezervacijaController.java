package sample.controller;

import com.sun.javafx.scene.control.IntegerField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Baza;
import sample.model.Karta;
import sample.model.Rezervacija;


import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RezervacijaController implements Initializable{

    ObservableList<Rezervacija> data1 = FXCollections.observableArrayList();
    Baza db = new Baza();
    @FXML
    private TextField IDR;
    @FXML
    private TextField PolazisteR;
    @FXML
    private TextField OdredisteR;
    @FXML
    private IntegerField CijenaR;
//    @FXML
//    private TextField DatumR;
    @FXML
    private TableView<Rezervacija> RezervacijaR;
    @FXML
    private TableColumn<Rezervacija, String> ColumnIDR;
    @FXML
    private TableColumn<Rezervacija, Integer> ColumnOsobaR;
    @FXML
    private TableColumn<Rezervacija, Integer> ColumnKartaR;
//    @FXML
//    private TableColumn<Rezervacija, Integer> ColumnCijenaR;
//    @FXML
//    private TableColumn<Rezervacija, String> ColumnDatumR;

    ObservableList<Karta> data2 = FXCollections.observableArrayList();
    @FXML
    private TableView<Karta> KartaR;
    @FXML
    private TableColumn<Karta, String> ColumnIdR;
    @FXML
    private TableColumn<Karta, String> ColumnPolazisteR;
    @FXML
    private TableColumn<Karta, String> ColumnOdredisteR;
    @FXML
    private TableColumn<Karta, Integer> ColumnCijenaR;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void listaRezervacija () {

        Baza DB = new Baza();
        String sql = "SELECT * FROM rezervacija WHERE person=24";
        System.out.println(sql);
        ResultSet rs = DB.select(sql);
        ColumnIDR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("ID"));
        ColumnOsobaR.setCellValueFactory(new PropertyValueFactory<Rezervacija, Integer>("Osoba"));
        ColumnKartaR.setCellValueFactory(new PropertyValueFactory<Rezervacija, Integer>("Karta"));
//        ColumnCijenaR.setCellValueFactory(new PropertyValueFactory<Rezervacija, Integer>("Cijena"));
//        ColumnDatumR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Datum"));

        try {

            data1.clear();
            while (rs.next()) {
                data1.add(new Rezervacija(
                        rs.getInt("ID"),
                        rs.getInt("person"),
                        rs.getInt("ticket")));
                RezervacijaR.setItems(data1);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }

    public void listaKarata () {

        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM karta");
        ColumnIdR.setCellValueFactory(new PropertyValueFactory<Karta, String>("ID"));
        ColumnPolazisteR.setCellValueFactory(new PropertyValueFactory<Karta, String>("Polaziste"));
        ColumnOdredisteR.setCellValueFactory(new PropertyValueFactory<Karta, String>("Odrediste"));
        ColumnCijenaR.setCellValueFactory(new PropertyValueFactory<Karta, Integer>("Cijena"));
//        ColumnDatumR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Datum"));

        try {

            data2.clear();
            while (rs.next()) {
                data2.add(new Karta(
                        rs.getInt("ID"),
                        rs.getString("start_city"),
                        rs.getString("end_city"),
                        rs.getInt("price")));
                KartaR.setItems(data2);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }

    public void Rezerviraj(ActionEvent event){
        try{
            String sql = "INSERT INTO rezervacija (person, ticket) VALUES (?, ?)";

            Karta r = (Karta) KartaR.getItems().get(KartaR.getSelectionModel().getSelectedIndex());

            Integer id = r.getID();
            System.out.println(id);
            PreparedStatement ps = db.exec(sql);
            ps.setInt(1, 24);
            ps.setInt(2, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UkloniRezervaciju(ActionEvent event){
        try{
            String sql = "DELETE FROM rezervacija WHERE id=?";

            Rezervacija r = (Rezervacija) RezervacijaR.getItems().get(RezervacijaR.getSelectionModel().getSelectedIndex());

            Integer id = r.getID();
            System.out.println(id);
            PreparedStatement ps = db.exec(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void postaviPodatkeUCelijeR(){
        ;
    }
    public void DodajR(ActionEvent event){
        try{
            String sql = "INSERT INTO rezervacija (start_city, end_city, price) VALUES (?, ?, ?)";

            PreparedStatement ps = db.exec(sql);
            ps.setString(1, PolazisteR.getText());
            ps.setString(2, OdredisteR.getText());
            ps.setInt(3, CijenaR.getValue());
//            ps.setString (4, DatumR.getText());

            ps.execute();


            JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            IDR.clear();
            PolazisteR.clear();
            OdredisteR.clear();
//            DatumR.clear();
            data1.clear();
            listaRezervacija();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void UrediR(ActionEvent event){
        try{

            String sql =  "UPDATE `rezervacija` SET `Polaziste`='"+PolazisteR.getText()+"',`Odrediste`='"+OdredisteR.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno azurirano");
            data1.clear();
            listaRezervacija();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void ObrisiR(ActionEvent event){
        try{
            String sql = "DELETE FROM rezervacija WHERE ID="+IDR.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            IDR.clear();
            PolazisteR.clear();
            OdredisteR.clear();
//            DatumR.clear();
            data1.clear();
            listaRezervacija();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void IsprazniPoljaR (ActionEvent event){
        try{
            IDR.clear();
            PolazisteR.clear();
            OdredisteR.clear();
//            DatumR.clear();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Odjava(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/view/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prijava!");
            stage.setScene(new Scene(root, 370, 250));
            stage.show();
        } catch (Exception e) {

        }
    }
    private void handle(MouseEvent e) {
        Rezervacija r = (Rezervacija) RezervacijaR.getItems().get(RezervacijaR.getSelectionModel().getSelectedIndex());
        IDR.setText(String.valueOf(r.getID()));
        PolazisteR.setText(String.valueOf(r.getKarta()));
        OdredisteR.setText(String.valueOf(r.getOsoba()));
    }

}


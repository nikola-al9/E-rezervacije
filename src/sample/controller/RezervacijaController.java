package sample.controller;

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
    private TextField DatumR;
    @FXML
    private TableView<Rezervacija> RezervacijaR;
    @FXML
    private TableColumn<Rezervacija, String> ColumnIDR;
    @FXML
    private TableColumn<Rezervacija, String> ColumnPolazisteR;
    @FXML
    private TableColumn<Rezervacija, String> ColumnOdredisteR;
    @FXML
    private TableColumn<Rezervacija, String> ColumnDatumR;

    public RezervacijaController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void listaRezervacija () {

        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `rezervacija`");
        ColumnIDR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("ID"));
        ColumnPolazisteR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Polaziste"));
        ColumnOdredisteR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Odrediste"));
        ColumnDatumR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Datum"));

        try {

            data1.clear();
            while (rs.next()) {
                data1.add(new Rezervacija(
                        rs.getInt("ID"),
                        rs.getString("Polaziste"),
                        rs.getString("Odrediste"),
                        rs.getString("Datum")));
                RezervacijaR.setItems(data1);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }
    public void postaviPodatkeUCelijeR(){
        RezervacijaR.setOnMouseClicked(this::handle);
    }
    public void DodajR(ActionEvent event){
        try{
            String sql = "INSERT INTO rezervacija (Polaziste, Odrediste, Datum) VALUES (?, ?, ?)";

            PreparedStatement ps = db.exec(sql);
            ps.setString(1, PolazisteR.getText());
            ps.setString(2, OdredisteR.getText());
            ps.setString (3, DatumR.getText());
            ps.execute();


            JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            IDR.clear();
            PolazisteR.clear();
            OdredisteR.clear();
            DatumR.clear();
            data1.clear();
            listaRezervacija();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void UrediR(ActionEvent event){
        try{

            String sql =  "UPDATE `rezervacija` SET `Polaziste`='"+PolazisteR.getText()+"',`Odrediste`='"+OdredisteR.getText()+"',`Datum`='"+DatumR.getText();
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
            DatumR.clear();
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
            DatumR.clear();
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
        PolazisteR.setText(r.getPolaziste());
        OdredisteR.setText(r.getOdrediste());
        DatumR.setText(r.getDatum());
    }
}


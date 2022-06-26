package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.stage.Stage;
import sample.model.Baza;
import sample.model.Karta;
import sample.model.Osoba;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    ObservableList<Osoba> data1 = FXCollections.observableArrayList();
    Baza db = new Baza();
    @FXML
    private TextField jIDK;
    @FXML
    private TextField jImeK;
    @FXML
    private TextField jPrezimeK;
    @FXML
    private TextField jJMBGK;
    @FXML
    private TextField jEmailK;
    @FXML
    private TextField jTelefonK;
    @FXML
    private TextField jTipK;
    @FXML
    private TextField jKImeK;
    @FXML
    private TextField jLozinkaK;
    @FXML
    private TableView<Osoba> jTableK;
    @FXML
    private TableColumn<Osoba, String> ColumnIDK;
    @FXML
    private TableColumn<Osoba, String> ColumnImeK;
    @FXML
    private TableColumn<Osoba, String> ColumnPrezimeK;
    @FXML
    private TableColumn<Osoba, String> ColumnJMBGK;
    @FXML
    private TableColumn<Osoba, String> ColumnEmailK;
    @FXML
    private TableColumn<Osoba, String> ColumnTelefonK;;
    @FXML
    private TableColumn<Osoba, String> ColumnKImeK;
    @FXML
    private TableColumn<Osoba, String> ColumnLozinkaK;




    ObservableList<Karta> data2 = FXCollections.observableArrayList();
    @FXML
    private TableView<Karta> KartaR;
    @FXML
    private TableColumn<Karta, String> ColumnIdKarteR;
    @FXML
    private TableColumn<Karta, String> ColumnPolazisteKarteR;
    @FXML
    private TableColumn<Karta, String> ColumnOdredisteKarteR;
    @FXML
    private TableColumn<Karta, String> ColumnCijenaKarteR;
    @FXML
    private TextField jIdKarteK;
    @FXML
    private TextField jPolazisteKarteK;
    @FXML
    private TextField jOdredisteKarteK;
    @FXML

    private TextField jCijenaKarteK;




    public AdminMenuController (){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void listaOsoba () {

        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `osoba`");
        ColumnIDK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("ID"));
        ColumnImeK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Ime"));
        ColumnPrezimeK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Prezime"));
        ColumnJMBGK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("JMBG"));
        ColumnEmailK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Email"));
        ColumnTelefonK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Telefon"));
        ColumnKImeK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Tip_korisnika"));
        ColumnKImeK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Korisnicko_ime"));
        ColumnLozinkaK.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Lozinka"));

        try {

            data1.clear();
            while (rs.next()) {
                data1.add(new Osoba(
                        rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("jmbg"),
                        rs.getString("email"),
                        rs.getInt("phone_number"),
                        rs.getInt("type"),
                        rs.getString("username"),
                        rs.getString("password")));
                jTableK.setItems(data1);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }
    public void postaviPodatkeUCelijeO(){
        jTableK.setOnMouseClicked(e -> {
            Osoba os = (Osoba) jTableK.getItems().get(jTableK.getSelectionModel().getSelectedIndex());
//            jIDK.setText(String.valueOf(os.getID()));
            jImeK.setText(os.getIme());
            jPrezimeK.setText(os.getPrezime());
            jJMBGK.setText(String.valueOf(os.getJMBG()));
            jEmailK.setText(os.getEmail());
            jTelefonK.setText( String.valueOf(os.getTelefon()));
            jTipK.setText(String.valueOf(os.gettype()));
            jKImeK.setText(os.getKorisnicko_ime());
            jLozinkaK.setText(os.getLozinka());
        });
    }

    public void listaKarata1() {
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `karta`");
        ColumnIdKarteR.setCellValueFactory(new PropertyValueFactory<Karta, String>("ID"));
        ColumnPolazisteKarteR.setCellValueFactory(new PropertyValueFactory<Karta, String>("Polaziste"));
        ColumnOdredisteKarteR.setCellValueFactory(new PropertyValueFactory<Karta, String>("Odrediste"));
        ColumnCijenaKarteR.setCellValueFactory(new PropertyValueFactory<Karta, String>("Cijena"));

        try {

            data2.clear();
            while (rs.next()) {
                data2.add(new Karta(
                        rs.getInt("id"),
                        rs.getString("start_city"),
                        rs.getString("end_city"),
                        rs.getInt("price")));
                KartaR.setItems(data2);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }

    }


    public void postaviPodatkeUCelijeR(){
        KartaR.setOnMouseClicked(e -> {
            Karta k = (Karta) KartaR.getItems().get(KartaR.getSelectionModel().getSelectedIndex());
            jIdKarteK.setText(String.valueOf(k.getID()));
            jOdredisteKarteK.setText(k.getOdrediste());
            jPolazisteKarteK.setText(k.getPolaziste());
            jCijenaKarteK.setText(String.valueOf(k.getCijena()));
        });
    }


    public void IsprazniPoljaR (ActionEvent event){
        try{
            jIdKarteK.clear();
            jPolazisteKarteK.clear();
            jOdredisteKarteK.clear();
            jCijenaKarteK.clear();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


    public void DodajR(ActionEvent event){
        try{
            String sql = "INSERT INTO karta (start_city, end_city, price) VALUES (?, ?, ?)";

            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jPolazisteKarteK.getText());
            ps.setString(2, jOdredisteKarteK.getText());
            ps.setInt(3, Integer.parseInt(jCijenaKarteK.getText()));
//            ps.setString (4, DatumR.getText());

            ps.execute();


            JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            jIdKarteK.clear();
            jPolazisteKarteK.clear();
            jOdredisteKarteK.clear();
            jCijenaKarteK.clear();
            data2.clear();
            listaKarata1();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void UrediR(ActionEvent event){
        try{

            String sql =  "UPDATE `karta` SET `start_city`='"+jPolazisteKarteK.getText()+"',`end_city`='"+jOdredisteKarteK.getText()+"',`price`="+jCijenaKarteK.getText() +" WHERE `id`="+jIdKarteK.getText()+";";
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno azurirano");
            data2.clear();
            listaKarata1();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void ObrisiR(ActionEvent event){
        try{
            String sql = "DELETE FROM karta WHERE ID="+jIdKarteK.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            jIdKarteK.clear();
            jPolazisteKarteK.clear();
            jOdredisteKarteK.clear();
            jCijenaKarteK.clear();
            data2.clear();
            listaKarata1();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }



    public void DodajO(ActionEvent event){
        try{
            String sql = "INSERT INTO `osoba` (name, surname, jmbg, email, phone_number, type, username, password) VALUES (?, ?, ?,?,?,?,?,?)";

            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jImeK.getText());
            ps.setString(2, jPrezimeK.getText());
            ps.setString(3, jJMBGK.getText());
            ps.setString(4, jEmailK.getText());
            ps.setString(5, jTelefonK.getText());
            ps.setString(6, jTipK.getText());
            ps.setString(7, jKImeK.getText());
            ps.setString(8, jLozinkaK.getText());

            ps.execute();


            JOptionPane.showMessageDialog(null, "Uspjesno dodano");
//            jIDK.clear();
            jImeK.clear();
            jPrezimeK.clear();
            jJMBGK.clear();
            jEmailK.clear();
            jTelefonK.clear();
            jTipK.clear();
            jKImeK.clear();
            jLozinkaK.clear();
            data1.clear();
            listaOsoba();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void UrediO(ActionEvent event){
        try{

            String sql =  "UPDATE `osoba` SET `name`='"+jImeK.getText()+"',`surname`='"+jPrezimeK.getText()+"',`jmbg`='"+jJMBGK.getText()+"',`email`='"+jEmailK.getText()+"',`phone_number`='"+jTelefonK.getText()+"',`type`='"+jTipK.getText()+"',`username`='"+jKImeK.getText()+"',`password`='"+jLozinkaK.getText()+"' WHERE `jmbg`= "+jJMBGK.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno azurirano");
            data1.clear();
            listaOsoba();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void ObrisiO(ActionEvent event){
        try{
            String sql = "DELETE FROM  `osoba` WHERE jmbg="+jJMBGK.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            jImeK.clear();
            jPrezimeK.clear();
            jJMBGK.clear();
            jEmailK.clear();
            jTelefonK.clear();
            jTipK.clear();
            jKImeK.clear();
            jLozinkaK.clear();
            data1.clear();
            listaOsoba();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void IsprazniPoljaO(ActionEvent event){
        try{
//            jIDK.clear();
            jImeK.clear();
            jPrezimeK.clear();
            jJMBGK.clear();
            jEmailK.clear();
            jTelefonK.clear();
            jTipK.clear();
            jKImeK.clear();
            jLozinkaK.clear();
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

    public ObservableList<Karta> listaKarata2(Event event) {
        ObservableList<Karta> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `karta`");

        try {
            while(rs.next()) {
                lista.add(new Karta(rs.getInt("id"), rs.getString("start_city"), rs.getString("end_city"), rs.getInt("price")));
            }
        } catch (SQLException var4) {
            System.out.println("Nastala je greška prilikom iteriranja: " + var4.getMessage());
        }

        return lista;

    }
}


// Za dodavanja karata:
//package sample.controller;
//
//        import javafx.collections.FXCollections;
//        import javafx.collections.ObservableList;
//        import javafx.event.ActionEvent;
//        import javafx.fxml.FXML;
//        import javafx.fxml.FXMLLoader;
//        import javafx.fxml.Initializable;
//        import javafx.scene.Node;
//        import javafx.scene.Parent;
//        import javafx.scene.Scene;
//        import javafx.scene.control.TableColumn;
//        import javafx.scene.control.TableView;
//        import javafx.scene.control.TextField;
//        import javafx.scene.control.cell.PropertyValueFactory;
//        import javafx.scene.input.MouseEvent;
//        import javafx.stage.Stage;
//        import sample.model.Baza;
//        import sample.model.Rezervacija;
//
//
//        import javax.swing.*;
//        import java.net.URL;
//        import java.sql.PreparedStatement;
//        import java.sql.ResultSet;
//        import java.sql.SQLException;
//        import java.util.ResourceBundle;
//
//public class RezervacijaController implements Initializable{
//
//    ObservableList<Rezervacija> data1 = FXCollections.observableArrayList();
//    Baza db = new Baza();
//    @FXML
//    private TextField IDR;
//    @FXML
//    private TextField PolazisteR;
//    @FXML
//    private TextField OdredisteR;
//    @FXML
//    private TextField DatumR;
//    @FXML
//    private TableView<Rezervacija> RezervacijaR;
//    @FXML
//    private TableColumn<Rezervacija, String> ColumnIDR;
//    @FXML
//    private TableColumn<Rezervacija, String> ColumnPolazisteR;
//    @FXML
//    private TableColumn<Rezervacija, String> ColumnOdredisteR;
//    @FXML
//    private TableColumn<Rezervacija, String> ColumnDatumR;
//
//    public RezervacijaController() {
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//    public void listaRezervacija () {
//
//        Baza DB = new Baza();
//        ResultSet rs = DB.select("SELECT * FROM `rezervacija`");
//        ColumnIDR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("ID"));
//        ColumnPolazisteR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Polaziste"));
//        ColumnOdredisteR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Odrediste"));
//        ColumnDatumR.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("Datum"));
//
//        try {
//
//            data1.clear();
//            while (rs.next()) {
//                data1.add(new Rezervacija(
//                        rs.getInt("ID"),
//                        rs.getString("Polaziste"),
//                        rs.getString("Odrediste"),
//                        rs.getString("Datum")));
//                RezervacijaR.setItems(data1);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
//        }
//    }
//    public void postaviPodatkeUCelijeR(){
//        RezervacijaR.setOnMouseClicked(this::handle);
//    }
//    public void DodajR(ActionEvent event){
//        try{
//            String sql = "INSERT INTO rezervacija (Polaziste, Odrediste, Datum) VALUES (?, ?, ?)";
//
//            PreparedStatement ps = db.exec(sql);
//            ps.setString(1, PolazisteR.getText());
//            ps.setString(2, OdredisteR.getText());
//            ps.setString (3, DatumR.getText());
//            ps.execute();
//
//
//            JOptionPane.showMessageDialog(null, "Uspjesno dodano");
//            IDR.clear();
//            PolazisteR.clear();
//            OdredisteR.clear();
//            DatumR.clear();
//            data1.clear();
//            listaRezervacija();
//
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
//    public void UrediR(ActionEvent event){
//        try{
//
//            String sql =  "UPDATE `rezervacija` SET `Polaziste`='"+PolazisteR.getText()+"',`Odrediste`='"+OdredisteR.getText()+"',`Datum`='"+DatumR.getText();
//            PreparedStatement ps = db.exec(sql);
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Uspjesno azurirano");
//            data1.clear();
//            listaRezervacija();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//    }
//
//    public void ObrisiR(ActionEvent event){
//        try{
//            String sql = "DELETE FROM rezervacija WHERE ID="+IDR.getText();
//            PreparedStatement ps = db.exec(sql);
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
//            IDR.clear();
//            PolazisteR.clear();
//            OdredisteR.clear();
//            DatumR.clear();
//            data1.clear();
//            listaRezervacija();
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
//
//    public void IsprazniPoljaR (ActionEvent event){
//        try{
//            IDR.clear();
//            PolazisteR.clear();
//            OdredisteR.clear();
//            DatumR.clear();
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
//    public void Odjava(ActionEvent event) {
//        try {
//            ((Node) event.getSource()).getScene().getWindow().hide();
//            Parent root;
//            root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/view/login.fxml"));
//            Stage stage = new Stage();
//            stage.setTitle("Prijava!");
//            stage.setScene(new Scene(root, 370, 250));
//            stage.show();
//        } catch (Exception e) {
//
//        }
//    }
//    private void handle(MouseEvent e) {
//        Rezervacija r = (Rezervacija) RezervacijaR.getItems().get(RezervacijaR.getSelectionModel().getSelectedIndex());
//        IDR.setText(String.valueOf(r.getID()));
//        PolazisteR.setText(r.getPolaziste());
//        OdredisteR.setText(r.getOdrediste());
//        DatumR.setText(r.getDatum());
//    }
//}
//
//



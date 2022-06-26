package sample.controller;

import sample.model.Baza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Osoba;
import sample.model.Rezervacija;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    @FXML
    TextField jUsername;

    @FXML
    PasswordField jPassword;

    @FXML
    Label Poruka;


    public void Prijavise (ActionEvent event){
        Baza db = new Baza();
        String username = this.jUsername.getText();
        String password = this.jPassword.getText();
        String sql = "select type from osoba where username=? and password=?";
        System.out.println(username + password);
        try{
            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jUsername.getText());
            ps.setString(2, jPassword.getText());
            ResultSet rs = ps.executeQuery();
            rs.next();

            //OVO

//            if (!rs.next()){
//                Poruka.setText("Invalid username and/or password");
//            }

             if(Objects.equals(rs.getInt("type"), 1)){
                ((Node)event.getSource()).getScene().getWindow().hide();
                Parent root;
                 root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/view/AdminMenu.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Admin menu");
                stage.setScene(new Scene(root,721,675));
                stage.show();

            }
            else if((Objects.equals(rs.getInt("type"), 2))){
                ((Node)event.getSource()).getScene().getWindow().hide();

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/sample/view/Rezervacija.fxml"));
                try{
                    Loader.load();
                } catch(IOException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                RezervacijaController ime = Loader.getController();
//                RezervacijaController immm = new RezervacijaController();
//                immm.setIme(jUsername.getText());

                Stage stage = new Stage();
                stage.setTitle("Izbornik");
                Parent root = Loader.getRoot();
                stage.setScene(new Scene(root));
                stage.show();


            }

//            else if{
//                //OVO POPRAVI
//                Poruka.setText("Invalid username and/or password");
//
//            }
        }
        catch(Exception e){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null,e);
            Poruka.setText("Invalid username and/or password");


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


    }

}

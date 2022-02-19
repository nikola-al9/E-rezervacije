//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Rezervacija {
    private int ID;
    private String Polaziste;
    private String Odrediste;
    private String Datum;

    public Rezervacija(int ID, String Polaziste, String Odrediste, String Datum) {
        this.ID = ID;
        this.Polaziste = Polaziste;
        this.Odrediste = Odrediste;
        this.Datum = Datum;
    }

    public int getID() {
        return this.ID;
    }

    public String getPolaziste() {
        return this.Polaziste;
    }

    public String getOdrediste() {
        return this.Odrediste;
    }

    public String getDatum() {
        return this.Datum;
    }

    public static ObservableList<Rezervacija> listaRezervacija() {
        ObservableList<Rezervacija> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `rezervacija`");

        try {
            while(rs.next()) {
                lista.add(new Rezervacija(rs.getInt("ID"), rs.getString("Polaziste"), rs.getString("Odrediste"), rs.getString("Datum")));
            }
        } catch (SQLException var4) {
            System.out.println("Nastala je greška prilikom iteriranja: " + var4.getMessage());
        }

        return lista;
    }
}

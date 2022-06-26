//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Karta {
    private int ID;
    private String Polaziste;
    private String Odrediste;

    private Integer Cijena;
//    private String Datum;

    public Karta(int ID, String Polaziste, String Odrediste, Integer Cijena) {
        this.ID = ID;
        this.Polaziste = Polaziste;
        this.Odrediste = Odrediste;
        this.Cijena = Cijena;
//        this.Datum = Datum;
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

    public Integer getCijena() {
        return this.Cijena;
    }

//    public String getDatum() {
//        return this.Datum;
//    }

    public static ObservableList<Karta> listaKarata() {
        ObservableList<Karta> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `karta`");

        try {
            while(rs.next()) {
                lista.add(new Karta(rs.getInt("ID"), rs.getString("Polaziste"), rs.getString("Odrediste"), rs.getInt("Cijena")));
            }
        } catch (SQLException var4) {
            System.out.println("Nastala je greška prilikom iteriranja: " + var4.getMessage());
        }

        return lista;
    }
}

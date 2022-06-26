//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.javafx.scene.control.IntegerField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Rezervacija {
    private int ID;
    private int Osoba;
    private int Karta;

    public Rezervacija(int ID, int Osoba, int Karta) {
        this.ID = ID;
        this.Osoba = Osoba;
        this.Karta = Karta;
    }

    public int getID() {
        return this.ID;
    }

    public int getOsoba() {
        return this.Osoba;
    }

    public int getKarta() {
        return this.Karta;
    }

    public static ObservableList<Rezervacija> listaRezervacija() {
        ObservableList<Rezervacija> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `rezervacija`");

        try {
            while(rs.next()) {
                lista.add(new Rezervacija(rs.getInt("ID"), rs.getInt("Osoba"), rs.getInt("Karta")));
            }
        } catch (SQLException var4) {
            System.out.println("Nastala je greška prilikom iteriranja: " + var4.getMessage());
        }

        return lista;
    }
}

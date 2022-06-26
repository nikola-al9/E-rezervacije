package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Osoba {
    private int ID;
    private String Ime;
    private String Prezime;
    private int JMBG;
    private String Email;
    private int Telefon;
    private int type;
    private String Korisnicko_ime;
    private String Lozinka;

    public Osoba(int ID, String name, String surname, int jmbg, String email, int phone_number, int type, String username, String password) {
        this.ID = ID;
        this.Ime = name;
        this.Prezime = surname;
        this.JMBG = jmbg;
        this.Email = email;
        this.Telefon = phone_number;
        this.type= type;
        this.Korisnicko_ime = username;
        this.Lozinka = password;
    }

    public int getID() {
        return ID;
    }

    public String getIme() {
        return Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public int getJMBG() {
        return JMBG;
    }

    public String getEmail() {
        return Email;
    }

    public int getTelefon() {
        return Telefon;
    }
    public int gettype() {
        return type;
    }

    public String getKorisnicko_ime() {
        return Korisnicko_ime;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public static ObservableList<Osoba> listaOsoba() {
        ObservableList <Osoba> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `osoba`");

        try {
            while (rs.next()) {
                lista.add(new Osoba(rs.getInt("ID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getInt("JMBG"),  rs.getString("Email"),  rs.getInt("Telefon"),rs.getInt("type"), rs.getString("Korisnicko_ime"),  rs.getString("Lozinka")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}

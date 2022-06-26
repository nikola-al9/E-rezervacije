//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample.model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {
    private String host;
    private String korisnik;
    private String lozinka;
    private String baza;
    protected Connection konekcija;

    public Konekcija() {
        this.host = "sql11.freemysqlhosting.net:3306";
        this.korisnik = "sql11501950";
        this.lozinka = "VxdfRgAs2z";
        this.baza = "sql11501950";
        this.spoji();
    }

    public Konekcija(String host, String korisnik, String lozinka, String baza) {
        this.host = host;
        this.korisnik = korisnik;
        this.lozinka = lozinka;
        this.baza = baza;
        this.spoji();
    }

    public void spoji() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("jdbc:mysql://" + this.host + "/" + this.baza + "?user=" + this.korisnik + "&password=" + this.lozinka);
            this.konekcija = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.baza + "?user=" + this.korisnik + "&password=" + this.lozinka);
        } catch (ClassNotFoundException var2) {
            System.out.println("Sustav nije uspio pronaci klasu za konekciju na MYSQL...");
        } catch (SQLException var3) {
            System.out.println("Sustav nije se mogao spojiti na bazu podataka...");
        }

    }

    public void odspoji() {
        try {
            this.konekcija.close();
        } catch (SQLException var2) {
            System.out.println("Sustav nije uspio zatvoriti konekciju...");
        }

    }
}

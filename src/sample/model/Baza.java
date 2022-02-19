//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baza extends Konekcija {
    public static final Baza DB = new Baza();
    private Statement upit;
    private PreparedStatement execUpit;

    public Baza() {
    }

    public ResultSet select(String sql) {
        try {
            this.upit = this.konekcija.createStatement();
            return this.upit.executeQuery(sql);
        } catch (SQLException var3) {
            System.out.println("Nastala je greška prilikom izvršavanja upita " + var3.getMessage());
            return null;
        }
    }

    public PreparedStatement exec(String sql) {
        try {
            this.execUpit = this.konekcija.prepareStatement(sql, 1);
            return this.execUpit;
        } catch (SQLException var3) {
            System.out.println("Nisam uspio izvršiti upit " + var3.getMessage());
            return null;
        }
    }
}

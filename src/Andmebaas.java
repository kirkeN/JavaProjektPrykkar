import com.sun.org.apache.xpath.internal.operations.And;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kirke on 12.12.2015.
 */
public class Andmebaas {
    Connection conn = null;

    // Constructor, käivitab kohe objekti väljakutsumisel
    public Andmebaas(){
        looYhendus();
        looTabel();
    }

    //MEETOD andmebaasi yhendamiseks
    private void looYhendus (){
        try{
            Class.forName("org.sqlite.JDBC"); // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:test.db"); // loo ühendus andmebaasi failiga
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Opened database successfully");            // lihtsalt meie enda jaoks teade
    }

    public void looTabel() {
        // Käsk ise on CREATE TABLE ja sulgude vahel on kõik tulbad, mida tahan, et tabel hoiaks.
        String sql = "CREATE TABLE IF NOT EXISTS TABEL (ID INT AUTO_INCREMENT, KONTEINER TEXT, " + // jätkub järgmisel real
                "PRYGI TEXT, GUPP TEXT);";
        teostaAndmebaasiMuudatus(sql);
    }
    // Andmebaasi muudatused ei tagasta väärtusi (erinevalt
    // päringutest) ja on lihtne eraldi meetodi tuua.
    private void teostaAndmebaasiMuudatus(String sql) {
        try{
        // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void lisaPrygi (String konteiner, String prygi) {
        String sql = "INSERT INTO TABEL (KONTEINER, PRYGI) VALUES ('"+konteiner+"','"+prygi+"')";
        teostaAndmebaasiMuudatus(sql);
    }
}

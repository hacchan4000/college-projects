package buku;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class nyoba {
    static Connection koneksi;
    public static Connection getKoneksi(){
        try {
            String url = "jdbc:mysql://localhost/perpustakaan";
            String user = "root";
            String password = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = 
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
   
}

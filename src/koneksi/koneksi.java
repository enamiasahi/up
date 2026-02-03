/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author buildAccount
 */
public class koneksi {
    public static Connection connection = null;

    public static Connection getConnection(){
        if (connection != null)
            return connection;
        else {
            String dbURL = "jdbc:mysql://localhost:3306/" +     "up_rpl?user=root&password=";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(dbURL);
                System.out.println("Koneksi Sukses");
            } catch (ClassNotFoundException | SQLException e) {
                // jika gagal pesan error...
                System.out.println("Koneksi gagal : "+e);
            }
            return connection;


        }
        
    }

    public static void main(String[] args) {
        getConnection();
    }
    
}

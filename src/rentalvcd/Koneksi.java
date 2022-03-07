/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalvcd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Asus
 */
public class Koneksi {
        public static Connection con;
        public static Statement statement;
        
    public Koneksi(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver tidak ditemukan"+e.getMessage());}
        
       
        con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/rent","root","");
            statement = con.createStatement();
        }catch (SQLException e){
            System.err.println("Koneksi DB Error "+e.getMessage());
            
        }
        
        if (con!= null){
            System.out.println("Koneksi DB Berhasil");
        }
        }

    PreparedStatement prepareStatement(String editSQL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

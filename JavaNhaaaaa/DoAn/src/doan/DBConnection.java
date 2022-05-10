/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

;
import java.sql.*;

/**
 *
 * @author HP
 */
public class DBConnection {
    public Connection conn;
     public   Statement myStmt=null;
     public   ResultSet myRs=null;
     
    public Connection KetNoiCSDL() throws ClassNotFoundException{
        String user="sa";
        String pass="123456";
        try{
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ShopQuanAo1",user,pass);
            System.out.println("Thanh Cong");
            return conn;
        }catch(SQLException e)
        {
             e.printStackTrace();
        }
        return conn;
    }
}

package model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static String driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/compufix";
    private static String username = "sanda";  
    private static String password = "DashDashGo2K23!!"; 

    public static Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } 

        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();  
        }
        
        return con;
    }

    public static void closeRessources(ResultSet rs, PreparedStatement prstm , Connection c, Boolean isNewConnection) throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(prstm != null){
            prstm.close();
        }
        if( c != null){
            if( isNewConnection != null && isNewConnection.booleanValue() == true){
                c.close();
            }
            else if( isNewConnection == null){
                c.close();
            }
        }
    }
}
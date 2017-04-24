/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHandler;

/**
 *
 * @author shan
 */

import static DatabaseHandler.DBHandler.connection;
import java.sql.*;

public class DBHandler {
    static Connection connection=null;
    private final static String databaseUrl = "jdbc:mysql://localhost:3306/shoppingCart";
    private final static String databaseUser = "root";
    private final static String databasePassword = "";
    
    private DBHandler(){
    
    }
    
    public static Connection getConnection(){
        System.out.println("db called");
        if(connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    connection = DriverManager.getConnection(databaseUrl,databaseUser,databasePassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        return connection;
    }
}

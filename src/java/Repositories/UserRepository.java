/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DatabaseHandler.DBHandler;
import Entity.Role;
import Entity.User;
import static Repositories.ItemRepository.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shan
 */
public class UserRepository {
    
    static Connection connection;
    
    /**
     *
     * @param username
     * @return
     */
    public static User getUserByUsername(String username){
        System.out.println("sdsdsdsdsdsd");
        connection = DBHandler.getConnection();
        
        String query = "SELECT * FROM `users` JOIN roles ON users.role = roles.id WHERE email = '"+username+"'";
        User user = new User();
        
        try{
            Statement userCheck = connection.createStatement();
            ResultSet result = userCheck.executeQuery(query);
//            result.last();
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            System.out.println(size);
            if(size < 1){
                return null;
            }
            
//            result.first();
            System.out.println("sddsds");
//            user.setFirstName(username);
            if(result.next()){
//	    	avQty	= rs.getInt("QTY");
                user.setId(result.getInt("id"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                
                Role role = new Role();
                role.setId(result.getInt("role"));
                role.setMetacode(result.getString("metacode"));
                role.setName(result.getString("name"));
                
                user.setRole(role);
                
            }
	    	
//	    connection.close();
        
            
        }
        catch(SQLException e){
            return null;
        }
        
        return user;
    }
    
    public static boolean createUser(String firstName,String lastName,String email,String password){
        connection = DBHandler.getConnection();
        String querySave = "INSERT INTO `users` (`firstName`, `lastName`, `email`, `password`,`role`) VALUES (?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement itemSave = connection.prepareStatement(querySave);
            itemSave.setString(1, firstName);
            itemSave.setString(2, lastName);
            itemSave.setString(3, email);
            itemSave.setString(4, password );
            itemSave.setInt(5, 1);
            itemSave.execute();
            return true;
        }catch(SQLException e){
            
            e.printStackTrace();
            return false;
        }
        
//        return true;
    }
    
}

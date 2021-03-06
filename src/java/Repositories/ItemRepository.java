/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DatabaseHandler.DBHandler;
import Entity.Category;
import Entity.Item;
import Entity.Role;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemRepository {
 
    static Connection connection;
    
    public static ArrayList<Item> getItems(String name,String category, String itemCode, User user){
          connection = DBHandler.getConnection();
           ArrayList<Item> items=new ArrayList<Item>();
          String query = "SELECT * FROM `items` JOIN categories ON items.category = categories.id JOIN users ON items.user = users.id ";
          ArrayList<String> andQueries = new ArrayList<String>();
          if(name != null & name != ""){
              andQueries.add("items.name LIKE '%"+name+"%'");
          }
          if(category != null & category != ""){
              andQueries.add("items.category = "+category);
          }
          if(itemCode != null & itemCode != ""){
              andQueries.add("items.itemCode LIKE '%"+itemCode+"%'");
          }
          
          andQueries.add("items.user = "+user.getId());
          
          if(andQueries.size()>0){
              query+=" WHERE ( ";
          }
          for (int i = 0; i < andQueries.size(); i++) {
              query+= andQueries.get(i);
              if(i!= andQueries.size()-1){
                  query += " AND ";
              }
          }
          
          if(andQueries.size()>0){
              query+= " )";
          }
          System.out.println("name :"+name);
          
          query = query.replaceAll("\"", "");
          System.out.println(query);
          try{
            Statement userCheck = connection.createStatement();
            ResultSet result = userCheck.executeQuery(query);
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            if(size < 1){
                System.out.println("null returned");
                return null;
            }
            

            while (result.next()) {

                items.add(createObject(result, user));
                
            }
	    	        
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
          
        return items;
    }
    
    public static ArrayList<Category> getCategories(){
        connection = DBHandler.getConnection();
           ArrayList<Category> categories=new ArrayList<Category>();
          String query = "SELECT * FROM `categories`";
          try{
            Statement userCheck = connection.createStatement();
            ResultSet result = userCheck.executeQuery(query);
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            if(size < 1){
                return null;
            }
            

            while (result.next()) {
                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                category.setMetacode(result.getString("metacode"));
                categories.add(category);
            }
            
          }catch(SQLException e){
              return null;
          }
          return categories;
    }
    
    private static Item createObject(ResultSet result,User user) throws SQLException {
        Item item = new Item();
        item.setId(result.getInt("items.id"));
        item.setName(result.getString("name"));
        item.setPrice(result.getFloat("price"));
        item.setItemCode(result.getString("itemCode"));
        item.setQuantity(result.getInt("quantity"));
        item.setImage(result.getString("image"));
        if(user == null){
            user = new User();
            user.setId(result.getInt("users.id"));
            user.setEmail(result.getString("users.email"));
            user.setFirstName(result.getString("users.firstName"));
            user.setLastName(result.getString("users.lastName"));
        }
        item.setUser(user);
        Category newCategory = new Category();
        newCategory.setId(result.getInt("categories.id"));
        newCategory.setName(result.getString("categories.name"));
        newCategory.setMetacode(result.getString("categories.metacode"));
        item.setCategory(newCategory);
        return item;
    }
    
    public static boolean saveItem(String name, String itemCode, String price, String quantity, String category, String image,User user){
        connection = DBHandler.getConnection();
        String querySave = "INSERT INTO `items` (`name`, `itemCode`, `price`, `quantity`,`category`,`image`,`user`) VALUES (?, ?, ?, ?, ?,?,?)";
        
        try{
            PreparedStatement itemSave = connection.prepareStatement(querySave);
            itemSave.setString(1, name);
            itemSave.setString(2, itemCode);
            itemSave.setFloat(3, Float.parseFloat(price));
            itemSave.setInt(4, Integer.parseInt(quantity));
            itemSave.setInt(5, Integer.parseInt(category));
            itemSave.setString(6, image);
            itemSave.setInt(7, user.getId());
            System.out.println("sdsd"+user.getId());
            itemSave.execute();
            return true;
        }catch(SQLException e){
            
            e.printStackTrace();
            return false;
        }

    }
    
    public static ArrayList<Item> getItemsBy(String name,String category, String lPrice, String hPrice){
        connection = DBHandler.getConnection();
           ArrayList<Item> items=new ArrayList<Item>();
          String query = "SELECT * FROM `items` JOIN categories ON items.category = categories.id JOIN users ON items.user = users.id ";
          
          ArrayList<String> andQueries = new ArrayList<String>();
          if(name != null & name != ""){
              andQueries.add("items.name LIKE '%"+name+"%'");
          }
          if(category != null & category != ""){
              andQueries.add("items.category = "+category);
          }
          if(lPrice != null & lPrice != ""){
              andQueries.add("items.price >="+lPrice);
          }
          
          if(hPrice != null & hPrice != ""){
              andQueries.add("items.price <="+hPrice);
          }
          
          
          if(andQueries.size()>0){
              query+=" WHERE ( ";
          }
          for (int i = 0; i < andQueries.size(); i++) {
              query+= andQueries.get(i);
              if(i!= andQueries.size()-1){
                  query += " AND ";
              }
          }
          
          if(andQueries.size()>0){
              query+= " )";
          }
          
          query = query.replaceAll("\"", "");
          System.out.println(query);
          try{
            Statement userCheck = connection.createStatement();
            ResultSet result = userCheck.executeQuery(query);
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            if(size < 1){
                System.out.println("null returned");
                return null;
            }
            

            while (result.next()) {

                items.add(createObject(result, null));
                
            }
	    	        
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
          
        return items;
    }
    
    
    
    public static float[] getPriceLimit(String name,String category){
        connection = DBHandler.getConnection();
          String query = "SELECT *,MIN(price) as minPrice,MAX(price) as maxPrice FROM `items` JOIN categories ON items.category = categories.id JOIN users ON items.user = users.id ";
          float[] prices = new float[2];
          ArrayList<String> andQueries = new ArrayList<String>();
          if(name != null & name != ""){
              andQueries.add("items.name LIKE '%"+name+"%'");
          }
          if(category != null & category != ""){
              andQueries.add("items.category = "+category);
          }
          
          
          if(andQueries.size()>0){
              query+=" WHERE ( ";
          }
          for (int i = 0; i < andQueries.size(); i++) {
              query+= andQueries.get(i);
              if(i!= andQueries.size()-1){
                  query += " AND ";
              }
          }
          
          if(andQueries.size()>0){
              query+= " )";
          }
          
          query = query.replaceAll("\"", "");
          System.out.println(query);
          try{
            Statement userCheck = connection.createStatement();
            ResultSet result = userCheck.executeQuery(query);
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            if(size < 1){
                System.out.println("null returned");
                return null;
            }
            
            

            if (result.next()) {

                prices[0] = result.getFloat("minPrice");
                prices[1] = result.getFloat("maxPrice");
                
            }
	    	        
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
          
        return prices;
    }
    
    public static Item getItemById(String id){
        connection = DBHandler.getConnection();
           ArrayList<Item> items=new ArrayList<Item>();
          String query = "SELECT * FROM `items` JOIN categories ON items.category = categories.id JOIN users ON items.user = users.id WHERE items.id = "+id;
       
          query = query.replaceAll("\"", "");
          System.out.println(query);
          Item item = null;
          try{
            Statement userCheck = connection.createStatement();
            ResultSet result = userCheck.executeQuery(query);
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            if(size < 1){
                System.out.println("null returned");
                return null;
            }
            
            

            while (result.next()) {

                item = createObject(result, null);
                
            }
	    	        
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
          
        return item;
    }
    
    public static boolean editItem(String name, String itemCode, String price, String quantity, String category,String image,String id){
        connection = DBHandler.getConnection();
        String querySave = "UPDATE `items` SET `name` = ? , `itemCode` = ?, `price` = ?, `quantity` = ?,`category`= ?, `image`= ? WHERE `items`.`id` = "+id;
        
        try{
            PreparedStatement itemSave = connection.prepareStatement(querySave);
            itemSave.setString(1, name);
            itemSave.setString(2, itemCode);
            itemSave.setFloat(3, Float.parseFloat(price));
            itemSave.setInt(4, Integer.parseInt(quantity));
            itemSave.setInt(5, Integer.parseInt(category));
            itemSave.setString(6, image);
            itemSave.execute();
            System.out.println(itemSave);

            return true;
        }catch(SQLException e){
            
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean removeItem(String id){
        connection = DBHandler.getConnection();
        String queryDelete = "DELETE FROM `items` WHERE id = ?";
        
        try{
            PreparedStatement itemDelete = connection.prepareStatement(queryDelete);
            itemDelete.setString(1, id);
            
//            itemSave.setString(6, image);
            itemDelete.execute();
            System.out.println(itemDelete);

            return true;
        }catch(SQLException e){
            
            e.printStackTrace();
            return false;
        }
    }
}

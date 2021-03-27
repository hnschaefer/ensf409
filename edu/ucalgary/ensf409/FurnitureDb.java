/**
 @author 
 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.sql.*;
import java.io.*;

public class FurnitureDb{
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection dbConnect;
    private ResultSet results;

    public FurnitureDb(String DBURL, String USERNAME, String PASSWORD){
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void initializeConnection(){
        //connect to database
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeChairFromInventory(String id){
        try{
            String query = "DELETE FROM chair WHERE ID = ?";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);

            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeDeskFromInventory(String id){
        try{
            String query = "DELETE FROM desk WHERE ID = ?";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);

            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeFilingFromInventory(String id){
        try{
            String query = "DELETE FROM filing WHERE ID = ?";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);

            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeLampFromInventory(String id){
        try{
            String query = "DELETE FROM lamp WHERE ID = ?";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);

            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertNewChair (String idNumber, String type, String legs, String arms, String seat, String cushion, String price, String manufacturerId){
        try 
            {
                // first create the query itself as a string, it looks the same as the sql
                String query = "INSERT INTO teacher (ID, Type, Legs, Arms, Seat, Cushion, Price, ManuID) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement stmt = myConnect.prepareStatement(query); // send query to PreparedStatement

                stmt.setString(1, idNumber);
                stmt.setString(2, type);
                stmt.setString(3, legs);
                stmt.setString(4, arms);
                stmt.setString(5, seat);
                stmt.setString(5, cushion);
                stmt.setString(5, price);
                stmt.setString(5, manufacturerId);

                int rowCount = stmt.executeUpdate(); // updating the database, we are creating a transaction is the data base which is gonna update the data base
                //System.out.println("Rows affected: " + rowCount);
                
                stmt.close(); // good practice to close
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            } 
    }
    
    public int priceCheck(String table){   
        List<Int> pricesList = new ArrayList<Int>();

        try{
        Statement stmt = dbConnect.createStatement();
        String query = "SELECT Price FROM " + table;
        results = stmt.executeQuery(query);
            while(results.next()){
                prices.add(results.getInt("Price"));
            }
        stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        int[] pricesArray = pricesList.toArray();
        int lowest = pricesArray[0];
        for(int i = 0; i < pricesArray.length; i++){
            if (lowest > pricesArray[i]){
                lowest = pricesArray[i];
            }
        }
        return lowest;
    }
}
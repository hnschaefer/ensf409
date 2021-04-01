/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>

 @version 1.4
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.sql.*;
import java.io.*;
import java.util.*;

public class FurnitureDb{
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection dbConnect;
    private ResultSet results;
    private ResultSetMetaData resultsMeta;

    public FurnitureDb(String DBURL, String USERNAME, String PASSWORD){
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    // Establish database connection
    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Removal of furniture items from database
    // To be used once items have been placed in order form
    // And are no longer in inventory
    public void removeFurnitureFromInventory(String category, String id){
        try{
            String query = "DELETE FROM inventory." + category + " WHERE ID = ?";
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

    // Creation of ArrayList furnitureList that contains Furniture objects
    // with only desired category and desired type
    public ArrayList<Furniture> furnitureFinder(String category, String type){
        category = category.toLowerCase();
        ArrayList<Furniture> furnitureList = new ArrayList<Furniture>();
        ArrayList<Boolean> components = new ArrayList<Boolean>();

        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);
            resultsMeta = results.getMetaData();
            int columns = resultsMeta.getColumnCount();

            // Adding boolean values to components array list
            // Checks if "arms", "legs", etc. are available
            while(results.next()){
                for(int i = 3; i < columns - 2; i++){
                    if(resultsMeta.getColumnTypeName(i).equals("Y")){
                        components.add(true);
                    }
                    else if(resultsMeta.getColumnTypeName(i).equals("N")){
                        components.add(false);
                    }
                }

                // Adding Furniture objects to array list furnitureList
                if(results.getString("Type").equals(type)){
                    furnitureList.add(new Furniture(results.getString("ID"), results.getString("Type"), components, results.getInt("Price"), results.getString("ManuID")));
                }
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return furnitureList;
    }

    // Returns either desired quantity or quantity available
    public int componentCounter(ArrayList<Furniture> furnitureList, int desiredQuant){
        int componentCount = furnitureList.get(0).components.size();
        int maybeLeast = 0;
        int availableQuant = 0;

        // Counting how many of each component are available
        // Example: If 4 arms are available, but only 2 legs,
        // availableQuant will update to 2
        for(int i = 0; i < furnitureList.size(); i++){
            for(int j = 0; j < componentCount; j++){
                if(furnitureList.get(i).components.get(j) == true){
                    maybeLeast++;
                }
            }
            if(maybeLeast < availableQuant){
                availableQuant = maybeLeast;
            }
        }

        // If there are more furniture items available than
        // the customer desires, only return as many as they want
        if (desiredQuant < availableQuant){
            return desiredQuant;
        }

        return availableQuant;
    }

    // Creating sets of manufacturers that sell desired types of furniture
    // Note: A set does not allow duplicates
    public Set<Manufacturer> manufacturerSuggestion(String category){
        Set<Manufacturer> manufacturerList = new Set<Manufacturer>();
        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);

            while(results.next()){
                manufacturerList.add(new Manufacturer(category, results.getString("ManuID")));
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return manufacturerList;
    }
}
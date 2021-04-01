/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @version 1.5
 
 @since 1.0
*/

/* Class FurnitureDb creates a connection to the database "inventory"
*  It has the following functionalities:
*  Removal of furniture items from database
*  Creation of lists providing desired furniture types, 
*   (eg. list of all available ergonomic chairs)
*  A counter for how many items of the customer desired type can be created 
*   using various parts of used furniture
*  Creation of a list of manufacturers that can provide the desired
*   type of item
*
*  This class is to be used in conjunction with the classes UserIO,
*   FileIO, and Main, in order to successfully fulfill customers' used
*   furniture needs
*/

package edu.ucalgary.ensf409;

import java.sql.*;
import java.io.*;
import java.util.*;

public class FurnitureDb{
    // Variables for connection with database
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
    /* The following getter methods are used in the Test file to
     * perform tests on this file
     */
    public String getDburl()
    {
        return this.DBURL;
    }
    public String getUsername()
    {
        return this.USERNAME;
    }
    public String getPassword()
    {
        return this.PASSWORD;
    }

    // Removal of furniture items from database
    // To be used once items have been placed in order form
    //  and are no longer in inventory
    public void removeFurnitureFromInventory(String category, String id){
        // Connection to database, and creation of SQL query to delete items
        //  from database
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

    // Returns ArrayList "furnitureList" that contains Furniture objects
    //  with only customer's desired category and desired type
    //  (eg. list of standing desks)
    public ArrayList<Furniture> furnitureFinder(String category, String type){
        category = category.toLowerCase();
        ArrayList<Furniture> furnitureList = new ArrayList<Furniture>();
        ArrayList<Boolean> components = new ArrayList<Boolean>();

        // Connection to database, and creation of SQL query
        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);
            resultsMeta = results.getMetaData();
            int columns = resultsMeta.getColumnCount();

            // Adding boolean values to components array list
            // Checks if "arms", "legs", etc. are available
            // If available, result = true
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
                    furnitureList.add(new Furniture(results.getString("ID"), results.getString("Type"), 
                    components, results.getInt("Price"), results.getString("ManuID")));
                }
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return furnitureList;
    }

    // Counter which returns either desired quantity or quantity available of desired 
    //  furniture type and category 
    public int componentCounter(ArrayList<Furniture> furnitureList, int desiredQuant){
        int componentCount = furnitureList.get(0).components.size();
        int maybeLeast = 0;
        int availableQuant = 0;

        // Counting how many of each component are available
        //  (eg. if 4 arms are available, but only 2 legs,
        //  availableQuant will update to 2)
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
        //  the customer desires, only return quantity they asked for
        if (desiredQuant < availableQuant){
            return desiredQuant;
        }

        return availableQuant;
    }

    // Return sets of manufacturers that sell desired types of furniture
    // To be run at the beginning of the program, before any items are removed
    //  from inventory, in order for clients to have contacts of manufacturers
    //  who carry their desired furniture category, in case the quantity they
    //  desire is not available
    public Set<Manufacturer> manufacturerSuggestion(String category){
        Set<Manufacturer> manufacturerList = new HashSet<>();
        Set<String> manuID = new HashSet<>();

        // Connection to database, and creation of SQL query
        // Adds manufacturer IDs to manuID list, from items
        //  of customer's desired category
        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);

            while(results.next()){
                manuID.add(results.getString("ManuID"));
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        
        // Creation of set of manufacturers with manuIDs obtained
        //  from previous code segment
        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory.manufacturer";
            results = stmt.executeQuery(query);

            while(results.next()){
                if (manuID.contains(results.getString("ManuID"))){
                    manufacturerList.add(new Manufacturer(results.getString("ManuID"), 
                    results.getString("Name"), results.getString("Phone"), results.getString("Province")));
                }
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return manufacturerList;
    }
}
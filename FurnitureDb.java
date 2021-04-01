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
        // Start by assuming there is enough in stock
        int availableQuant = desiredQuant;

        // Counting how many of each component are available
        //  (eg. if 4 arms are available, but only 2 legs,
        //  availableQuant will update to 2)
        for(int i = 0; i < componentCount; i++){
            for(int j = 0; j < furnitureList.size(); j++){
                if(furnitureList.get(i).components.get(j) == true){
                    maybeLeast++;
                }
            }
            // If there is less than desired in stock,
            //  change available to the available quantity of component
            //  with least amount in stock
            if(maybeLeast < availableQuant){
                availableQuant = maybeLeast;
            }
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

    // *** THE FOLLOWING CODE IS AN EARLY ATTEMPT TO CALCULATE THE LOWEST PRICE ***
    // *** THIS SECTION NEEDS A LOT OF WORK!!! ***
    // *** PROCEED WITH CAUTION!!! ***


    // findIterations finds the number of possible furniture combinations based
    //  on how many items were ordered, and how many are available
    // Combination equation used (nCk = n! / (k! * (n-k)!) where n = available quantity
    //  and k = desired quantity.
    public int findIterations(ArrayList<Furniture> furnitureList, int desiredQuant){
        int availableQuant = furnitureList.size();
        int sum = 0;
    
        for(int i = desiredQuant; i <= availableQuant; i++){
            sum += fact(availableQuant)/(fact(i)*(fact(availableQuant-i)));
        }
    
        return sum;
    }

    public int fact(int num){
        if (num < 0){
            throw new IllegalArgumentException();
        }

        if (num > 1){
            return num + fact(num-1);
        }
        else{
            return 1;
        }
    }


    // Checks to see if list possibleCombo has enough of each component to
    //  fulfill the order (ie. enough legs, arms, bulbs, etc.)
    // Returns true if enough of each component, returns false otherwise
    public boolean combinationSuccess(ArrayList<Furniture> possibleCombo, int desiredQuant){
        int count = 0;
        for(int i = 0; i < possibleCombo.get(0).components.size(); i++){
            for(int j = 0; j < possibleCombo.size(); j++){
                if(possibleCombo.get(j).components.get(i) == true){
                    count++;
                }
            }
            if (count < desiredQuant){
                return false;
            }
            count = 0;
        }
        return true;
    }

    // Takes furnitureList containing furniture with correct category and type
    // Creates new arrays with all possibilities of combinations that could fulfill the order
    // Sends list to combinationSuccess to see if the combination has enough of each component
    //  to fulfill the order
    // Returns possibleCombo list if combination success is true, otherwise recursive function continues
    //  until iteration = 0.
    public ArrayList<Furniture> findCombinations(ArrayList<Furniture> furnitureList, int desiredQuant, int iteration){
        if(iteration == 0){
            return null;
        }

        ArrayList<Furniture> possibleCombo = new ArrayList<Furniture>();

        if (combinationSuccess(possibleCombo, desiredQuant) == true){
            return possibleCombo;
        }
        
        else{
            iteration--;
            findCombinations(furnitureList, desiredQuant, iteration);
        }
    }

}
/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>

 @version 1.2
 
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

    public FurnitureDb(String DBURL, String USERNAME, String PASSWORD){
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    //Establish database connection
    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Removal of furniture items from database
    //To be used once items have been placed in order form
    //And are no longer in inventory

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

    //Creation of ArrayLists of furniture with desired types

    public ArrayList<Chair> chairFinder(String category, String type, String quantity){
        category = category.toLowerCase();
        ArrayList<Chair> furnitureList = new ArrayList<Chair>();
        boolean legs = false;
        boolean arms = false;
        boolean seat = false;
        boolean cushion = false;

        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);
            while(results.next()){
                if(results.getString("Legs").equals("Y")){
                    legs = true;
                }
                if(results.getString("Arms").equals("Y")){
                    arms = true;
                }
                if(results.getString("Seat").equals("Y")){
                    seat = true;
                }
                if(results.getString("Cushion").equals("Y")){
                    cushion = true;
                }
                if(results.getString("Type").equals(type)){
                    furnitureList.add(new Chair(results.getString("ID"), results.getString("Type"), legs, arms, seat, cushion, results.getInt("Price"), results.getString("ManuID")));
                }
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return furnitureList;
    }

    public ArrayList<Desk> deskFinder(String category, String type, String quantity){
        category = category.toLowerCase();
        ArrayList<Desk> furnitureList = new ArrayList<Desk>();
        boolean legs = false;
        boolean top = false;
        boolean drawer = false;

        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);
            while(results.next()){
                if(results.getString("Legs").equals("Y")){
                    legs = true;
                }
                if(results.getString("Top").equals("Y")){
                    top = true;
                }
                if(results.getString("Drawer").equals("Y")){
                    drawer = true;
                }
                if(results.getString("Type").equals(type)){
                    furnitureList.add(new Desk(results.getString("ID"), results.getString("Type"), legs, top, drawer, results.getInt("Price"), results.getString("ManuID")));
                }
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return furnitureList;
    }

    public ArrayList<Filing> filingFinder(String category, String type, String quantity){
        category = category.toLowerCase();
        ArrayList<Filing> furnitureList = new ArrayList<Filing>();
        boolean rails = false;
        boolean drawers = false;
        boolean cabinet = false;

        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);
            while(results.next()){
                if(results.getString("Rails").equals("Y")){
                    rails = true;
                }
                if(results.getString("Top").equals("Y")){
                    drawers = true;
                }
                if(results.getString("Drawer").equals("Y")){
                    cabinet = true;
                }
                if(results.getString("Type").equals(type)){
                    furnitureList.add(new Filing(results.getString("ID"), results.getString("Type"), rails, drawers, cabinet, results.getInt("Price"), results.getString("ManuID")));
                }
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return furnitureList;
    }

    public ArrayList<Lamp> lampFinder(String category, String type, String quantity){
        category = category.toLowerCase();
        ArrayList<Lamp> furnitureList = new ArrayList<Lamp>();
        boolean base = false;
        boolean bulb = false;

        try{
            Statement stmt = dbConnect.createStatement();
            String query = "SELECT * FROM inventory." + category;
            results = stmt.executeQuery(query);
            while(results.next()){
                if(results.getString("Base").equals("Y")){
                    base = true;
                }
                if(results.getString("Bulb").equals("Y")){
                    bulb = true;
                }
                if(results.getString("Type").equals(type)){
                    furnitureList.add(new Lamp(results.getString("ID"), results.getString("Type"), base, bulb, results.getInt("Price"), results.getString("ManuID")));
                }
            }
            stmt.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return furnitureList;
    }
}
/**
 @author 
 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.sql.*;
import java.io.*;
public class FunTestFile{
    
    public void insertNewChair (String idNumber, String type, String legs, String arms, String seat, String cushion, String price, String manufacturerId){
        try {
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
}
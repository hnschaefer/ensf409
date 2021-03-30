/**
 @author 

 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.sql.*;
import java.io.*;

public class UserIO{
    private String category;
    private String type;
    private int quantity;

    public UserIO(String category, String type, int quantity){
        this.category = category;
        this.type = type;
        this.quantity = quantity;
    }

    public String getCategory(){
        return this.category;
    }

    public String getType(){
        return this.type;
    }

    public int getQuantity(){
        return this.quantity;
    }
    /**
    * @param args Handles a single command-line argument 
    */
    public static void main(String[] args){
        UserIO input = new UserIO(args[0], args[1], args[2]);
    }
}

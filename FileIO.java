package edu.ucalgary.ensf409;
import java.util.ArrayList;

/**
@author Lubaba Sheikh <a href="mailto:lubaba.sheikh@ucalgary.ca">
lubaba.sheikh@ucalgary.ca</a>
@version 1.4
@since 1.0
*/

import java.io.*;

public class FileIO{

    /**
     * Class FileIO consists of two methods called blankOrderForm() and completeOrderForm().
     * blankOrderForm() creates an order form that is blank with no price and item IDs.
     * completeOrderForm() creates a filled out order form with the category, type and quantity as well as the item IDs 
     * of the furnitures and the total price
     */
    
    public FileIO()
    {

    }


    // blankOrderForm() creates an order form that is blank with no price and item IDs.
    // no return value
    // takes in no arguments
    public void blankOrderForm()
    {
        StringBuffer form = new StringBuffer("Furniture Order Form\n" + "\nFacultyName:"+
                            "\nContact:" +"\nDate:"+"\n"+"\nOriginal Request:"+"\n"+"\nItems Ordered"+
                            "\n"+"\nTotalPrice:");
        File newfile = new File("Blank Furniture Order Form.txt"); // created a File object called newfile
        try
        {
            if (!newfile.exists()) 
            {
                newfile.createNewFile();
            } 
            PrintWriter pw = new PrintWriter(newfile);
            pw.println(form);
            pw.close();
        } 
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    // completeOrderForm() creates a filled out order form with the category, type and quantity as well as the item IDs 
    // of the furnitures and the total price
    // not return value
    // takes in arguments ArrayList<Furniture> list, int totalPrice, String type, String category, int originalQuant and int availableQuant
    public void completeOrderForm(ArrayList<Furniture> list, int totalPrice, String type, String category, int originalQuant, int availableQuant){
        String title = "Furniture Order Form";
        String fcd = "\n" + "\nFacultyName:" + "\nContact:" +"\nDate:";
        StringBuffer originalRequest = new StringBuffer("\n" + "Original Request: " + type + " " + category + ", " + originalQuant);
        StringBuffer availableRequest = new StringBuffer("\n" + "Available: " + type + " " + category + ", " + availableQuant);
        
        String itemsOrdered = "\n" +"Items Ordered";
        for (int i = 0; i < list.size(); i++)
        {
            itemsOrdered = itemsOrdered + "\n" + "ID: " + list.get(i).getId();
        }

        File newfile = new File("Furniture Order Form.txt"); // created a File object called newfile
        try
        {
            if (!newfile.exists()) 
            {
                newfile.createNewFile();
            } 
            PrintWriter pw = new PrintWriter(newfile);
            pw.print(title);
            pw.println(fcd);
            pw.println(originalRequest);
            pw.println(availableRequest);
            pw.println(itemsOrdered);
            pw.print("\n"+"Total Price: $" +totalPrice);
            pw.close();
        } 
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
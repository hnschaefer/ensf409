/**
 @author Rajpreet Gill <a 
  href="mailto:rajpreet.gill@ucalgary.ca">rajpreet.gill@ucalgary.ca</a>
 @author Heidi Schaefer <a 
  href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @author Lubaba Sheikh <a 
  href="mailto:lubaba.sheikh@ucalgary.ca">lubaba.sheikh@ucalgary.ca</a>

 @version 1.4
 @since 1.0
*/


package edu.ucalgary.ensf409;
import java.util.ArrayList;
import java.io.*;

public class FileIO{

    /** Class FileIO consists of two methods called blankOrderForm() 
    * and completeOrderForm(). It can create a blank order form, or a 
    * complete order form.
    * blankOrderForm() creates an order form that is blank with no price and 
    * item IDs.
    * completeOrderForm() creates a filled out order form with the category, 
    * type and quantity as well as the item IDs of the furnitures and the 
    * total price
    */
    public FileIO()
    {

    }

    /** Class blankOrderForm creates an order form that is blank with no price 
    * and item IDs.
    * No return value
    * Takes in no arguments
    */
    public void blankOrderForm()
    {
        StringBuffer form = new StringBuffer("Furniture Order Form\n" + 
                    "\nFacultyName:" + "\nContact:" + "\nDate:" + "\n" + 
                    "\nOriginal Request:" + "\n" + "\nItems Ordered" + 
                    "\n" + "\nTotalPrice:");

        // Create a File object called newfile
        File newfile = new File("Blank Furniture Order Form.txt");
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
            e.printStackTrace();
        }
    }

    
    /** Method completeOrderForm creates a filled out order form with the 
    * category, type and quantity as well as the item IDs 
    * of the furnitures and the total price.
    * No return value
    * Takes in arguments ArrayList<Furniture> list, int totalPrice, 
    * String type, String category, int originalQuant and int availableQuant
    */
    public void completeOrderForm(ArrayList<Furniture> list, int totalPrice, 
            String type, String category, int originalQuant, 
            int availableQuant){
        String title = "Furniture Order Form";
        String fcd = "\n" + "\nFacultyName:" + "\nContact:" + "\nDate:";
        StringBuffer originalRequest = new StringBuffer("\n" + 
                "Original Request: " + type + " " + category + ", " + 
                originalQuant);
        StringBuffer availableRequest = new StringBuffer("\n" + 
                "Available: " + type + " " + category + ", " + 
                availableQuant);
        
        String itemsOrdered = "\n" + "Items Ordered";
        for (int i = 0; i < list.size(); i++)
        {
            itemsOrdered = itemsOrdered + "\n" + "ID: " + list.get(i).getId();
        }

        // Create a File object called newfile
        File newfile = new File("Furniture Order Form.txt"); 
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
            pw.print("\n" + "Total Price: $" + totalPrice);
            pw.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
package edu.ucalgary.ensf409;
import java.util.ArrayList;

/**
@author Lubaba Sheikh <a href="mailto:lubaba.sheikh@ucalgary.ca">
lubaba.sheikh@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

import java.io.*;

public class FileIO{
    private String in;
    private String out;

    public FileIO(String in, String out){
        this.in = in;
        this.out = out;
    }
    public FileIO()
    {
        
    }

    public String getIn(){
        return this.in;
    }

    public String getOut(){
        return this.out;
    }



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

    public void completeOrderForm(ArrayList<Furniture> list, int totalPrice, String type, String category, int quantity){
        String title = "Furniture Order Form";
        String fcd = "\n" + "\nFacultyName:" + "\nContact:" +"\nDate:";
        StringBuffer originalRequest = new StringBuffer("\n" + "Original Request: " + type + " " + category + ", " + quantity);
        
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
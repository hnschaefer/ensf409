package edu.ucalgary.ensf409;

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

    public void CompleteOrderForm(String category, String type, String quantity, String[] itemIDs, int totalPrice){
        String title = "Furniture Order Form";
        String fcd = "\n" + "\nFacultyName:" + "\nContact:" +"\nDate:";
        StringBuffer originalRequest = new StringBuffer("\n" + "Original Request: " + type + " " + category + ", " + quantity);
        
        String itemsOrdered = "\n" +"Items Ordered";
        for (int i = 0; i < itemIDs.length-1; i++)
        {
            itemsOrdered += "\n" + "ID: " + itemIDs[i];
        }

        File newfile = new File("Furniture Order Form.txt"); // created a File object called newfile
        try
        {
            if (!newfile.exists()) 
            {
                newfile.createNewFile();
            } 
            PrintWriter pw = new PrintWriter(newfile);
            pw.println(title);
            pw.println(fcd);
            pw.println(originalRequest);
            pw.println(itemsOrdered);
            pw.print("\n"+totalPrice);
            pw.close();
        } 
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
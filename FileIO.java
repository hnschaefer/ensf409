package edu.ucalgary.ensf409;

/**
@author Lubaba Sheikh <a href="mailto:lubaba.sheikh@ucalgary.ca">
lubaba.sheikh@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

import java.sql.*;
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



    public void blankOrderForm(){
        StringBuffer form = new StringBuffer("Furniture Order Form\n" + "\nFacultyName:"+
                            "\nContact:" +"\nDate:"+"\n"+"\nOriginal Request:"+"\n"+"\nItems Ordered"+
                            "\n"+"\nTotalPrice:");
        File newfile = new File("Furniture Order Form.txt"); // created a File object called newfile
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
}
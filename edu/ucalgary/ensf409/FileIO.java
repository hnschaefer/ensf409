/**
 @author 

 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

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
}
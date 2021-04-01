/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 
 @version 1.0
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.sql.*;
import java.io.*;
import java.util.*;

public class Manufacturer{
    public String manuID;
    public String name;
    public String phone;
    public String province;

    public Manufacturer(String manuID, String name, String phone, String province){
        this.manuID = manuID;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }
}
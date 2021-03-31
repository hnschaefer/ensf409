/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 
 @version 1.1
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.util.*;

public class FurnitureUniversal{
    public String id;
    public String type;
    public ArrayList<Boolean> components;
    public int price;
    public int manuID;

    public FurnitureUniversal(String id, String type, ArrayList<Boolean> components, int price, String manuID){
        this.id = id;
        this.type = type;
        this.components = components;
        this.price = price;
        this.manuID = manuID;
    }
}
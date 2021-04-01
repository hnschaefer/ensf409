/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 
 @version 1.1
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.util.*;

/* Furniture is a class which creates objects of type Furniture
*  All furniture categories share similar attributes of
*   id, type, price and manuID
*  However all categories have varying components
*   (eg. lamp has bulbs but no arms, chair has arms but no bulbs)
*  The varying components are accounted for in an array list of
*   type boolean, true if piece of furniture has that component,
*   false if it does not
*/

public class Furniture{
    public String id;
    public String type;
    public ArrayList<Boolean> components;
    public int price;
    public String manuID;

    public Furniture(String id, String type, ArrayList<Boolean> components, int price, String manuID){
        this.id = id;
        this.type = type;
        this.components = components;
        this.price = price;
        this.manuID = manuID;
    }
    public String getId()
    {
        return this.id;
    }
    public String getType()
    {
        return this.type;
    }
    public ArrayList<Boolean> getComponents()
    {
        return this.components; 
    }
    public int getPrice()
    {
        return this.price;
    }
    public String manuId()
    {
        return this.manuID;
    }
}
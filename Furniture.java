/**
 @author Rajpreet Gill <a 
  href="mailto:rajpreet.gill@ucalgary.ca">rajpreet.gill@ucalgary.ca</a>
 @author Heidi Schaefer <a 
  href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @author Lubaba Sheikh <a 
  href="mailto:lubaba.sheikh@ucalgary.ca">lubaba.sheikh@ucalgary.ca</a>
 @version 1.1
 @since 1.0
*/

/* Furniture is a class which creates objects of type Furniture
*  All furniture categories share similar attributes of
*   id, type, price and manuID
*  However all categories have varying components
*   (eg. lamp has bulbs but no arms, chair has arms but no bulbs)
*  The varying components are accounted for in an array list of
*   type boolean, true if piece of furniture has that component,
*   false if it does not
*/

package edu.ucalgary.ensf409;

import java.util.*;

public class Furniture{
    private String id;
    private String type;
    private ArrayList<Boolean> components = new ArrayList<Boolean>();
    private int price;
    private String manuID;

    public Furniture(String id, String type, ArrayList<Boolean> components, 
                int price, String manuID){

        this.id = id;
        this.type = type;
        for(int i = 0; i < components.size(); i++){
            this.components.add(components.get(i));
        }
        this.price = price;
        this.manuID = manuID;
    }

    public String getId(){
        return this.id;
    }

    public String getType(){
        return this.type;
    }

    public ArrayList<Boolean> getComponents(){
        return this.components; 
    }

    public int getPrice(){
        return this.price;
    }

    public String getManuId(){
        return this.manuID;
    }
}

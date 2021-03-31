/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 
 @version 1.1
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

public class Lamp{
    public String id;
    public String type;
    public boolean base;
    public boolean bulb;
    public int price;
    public int manuID;

    public Lamp(String id, String type, boolean base, boolean bulb, int price, int manuID){
        this.id = id;
        this.type = type;
        this.base = base;
        this.bulb = bulb;
        this.price = price;
        this.manuID = manuID;
    }
}
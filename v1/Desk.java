/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 
 @version 1.1
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

public class Desk{
    public String id;
    public String type;
    public boolean legs;
    public boolean top;
    public boolean drawer;
    public int price;
    public String manuID;

    public Desk(String id, String type, boolean legs, boolean top, boolean drawer, int price, String manuID){
        this.id = id;
        this.type = type;
        this.legs = legs;
        this.top = top;
        this.drawer = drawer;
        this.price = price;
        this.manuID = manuID;
    }
}

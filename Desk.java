/**
 @author 
 
 @version
 
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
    public int manuID;

    public Chair(String id, String type, boolean legs, boolean top, boolean drawer, int price, int manuID){
        this.id = id;
        this.type = type;
        this.legs = legs;
        this.arms = top;
        this.seat = drawer;
        this.price = price;
        this.manuID = manuID;
    }
}
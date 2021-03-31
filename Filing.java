/**
 @author 
 
 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

public class Filing{
    public String id;
    public String type;
    public boolean rails;
    public boolean drawers;
    public boolean cabinet;
    public int price;
    public int manuID;

    public Chair(String id, String type, boolean rails, boolean drawers, boolean cabinet, int price, int manuID){
        this.id = id;
        this.type = type;
        this.rails = rails;
        this.drawers = drawers;
        this.cabinet = cabinet;
        this.price = price;
        this.manuID = manuID;
    }
}
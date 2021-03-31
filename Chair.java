/**
 @author 
 
 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

public class Chair{
    public String id;
    public String type;
    public boolean legs;
    public boolean arms;
    public boolean seat;
    public boolean cushion;
    public int price;
    public String manuID;

    public Chair(String id, String type, boolean legs, boolean arms, boolean seat, boolean cushion, int price, String manuID){
        this.id = id;
        this.type = type;
        this.legs = legs;
        this.arms = arms;
        this.seat = seat;
        this.cushion = cushion;
        this.price = price;
        this.manuID = manuID;
    }
}
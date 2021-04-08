/**
 @author Heidi Schaefer <a 
    href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @version 1.0
 @since 1.0
*/

package edu.ucalgary.ensf409;

public class Manufacturer{
    private String manuID;
    private String name;
    private String phone;
    private String province;

    public Manufacturer(String manuID, String name, String phone, String province){
        this.manuID = manuID;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    public String getManuID(){
        return this.manuID;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getProvince(){
        return this.province;
    }
}
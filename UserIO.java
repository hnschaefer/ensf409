/**
 @author 
 @version
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.util.Scanner;

public class UserIO{
    private String category;
    private String type;
    private int quantity;

    public UserIO()
    {
        this.category = null;
        this.type = null;
        this.quantity = 0;
    }

    public UserIO(String category, String type, int quantity){
        this.category = category;
        this.type = type;
        this.quantity = quantity;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getCategory(){
        return this.category;
    }

    public String getType(){
        return this.type;
    }

    public int getQuantity(){
        return this.quantity;
    }
    
    // call this method in main to prompt userInput
    public void userInput()
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter furniture category: ");
        setCategory(input.nextLine());
        this.category.toLowerCase();
        
        if((!this.category.equals("chair")) || !(this.category.equals("desk")) || !(this.category.equals("lamp")) || !(this.category.equals("filing")))
        {
            input.close();
            throw new IllegalArgumentException("The category entered is invalid and does not exist in the database.");
        }

        System.out.println("Enter furniture type: ");
        setType(input.nextLine());
        this.type.toLowerCase();

        if(this.category.equals("chair"))
        {
            if(!(this.type.equals("kneeling")) || !(this.type.equals("task")) || !(this.type.equals("mesh")) 
               || !(this.type.equals("executive")) || !(this.type.equals("ergonomic"))){
                    input.close();
                    throw new IllegalArgumentException("The type entered is invalid and does not exist in the table chair.");
               }
        }

        if(this.category.equals("desk"))
        {
            if(!(this.type.equals("standing")) || !(this.type.equals("adjustable")) || !(this.type.equals("traditional")))
            {
                input.close();
                throw new IllegalArgumentException("The type entered is invalid and does not exist in the table desk.");
            }
        }

        if(this.category.equals("lamp"))
        {
            if(!(this.type.equals("desk")) || !(this.type.equals("study")) || !(this.type.equals("swing arm")))
            {
                input.close();
                throw new IllegalArgumentException("The type entered is invalid and does not exist in the table lamp.");
            }
        }

        if(this.category.equals("filing"))
        {
            if(!(this.type.equals("small")) || !(this.type.equals("medium")) || !(this.type.equals("large")))
            {
                input.close();
                throw new IllegalArgumentException("The type entered is invalid and does not exist in the table filing.");
            }
        }
        
        System.out.println("Enter number of items: ");
        setQuantity(Integer.parseInt(input.nextLine()));

        input.close();
    }
}
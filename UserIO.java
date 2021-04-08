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

    public UserIO(String category, String type, String quantity){
        setCategory(category);
        setType(type);
        setQuantity(quantity);
    }

    public void setCategory(String furnitureCategory)
    {
        if(!(furnitureCategory.equals("chair")) && !(furnitureCategory.equals("desk")) && !(furnitureCategory.equals("lamp")) && !(furnitureCategory.equals("filing")))
        {
            throw new IllegalArgumentException("The category entered is invalid and does not exist in the database.");
        }
        this.category = furnitureCategory;
    }

    public void setType(String furnitureType)
    {
        if(this.category.equals("chair"))
        {
            if(!(furnitureType.equals("Kneeling")) && !(furnitureType.equals("Task")) && !(furnitureType.equals("Mesh")) 
               && !(furnitureType.equals("Executive")) && !(furnitureType.equals("Ergonomic"))){
                    throw new IllegalArgumentException("The type entered is invalid and does not exist in the table chair.");
               }
        }

        if(this.category.equals("desk"))
        {
            if(!(furnitureType.equals("Standing")) && !(furnitureType.equals("Adjustable")) && !(furnitureType.equals("Traditional")))
            {
                throw new IllegalArgumentException("The type entered is invalid and does not exist in the table desk.");
            }
        }

        if(this.category.equals("lamp"))
        {
            if(!(furnitureType.equals("Desk")) && !(furnitureType.equals("Study")) && !(furnitureType.equals("Swing Arm")))
            {
                throw new IllegalArgumentException("The type entered is invalid and does not exist in the table lamp.");
            }
        }

        if(this.category.equals("filing"))
        {
            if(!(furnitureType.equals("Small")) && !(furnitureType.equals("Medium")) && !(furnitureType.equals("Large")))
            {
                throw new IllegalArgumentException("The type entered is invalid and does not exist in the table filing.");
            }
        }
        this.type = furnitureType;
    }
    public void setQuantity(String number)
    {
        try
        {
            int a = Integer.parseInt(number);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("The number of items entered is not an integer.");
        }
        this.quantity = Integer.parseInt(number);
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
        
        if(!(this.category.equals("chair")) && !(this.category.equals("desk")) && !(this.category.equals("lamp")) && !(this.category.equals("filing")))
        {
            input.close();
            throw new IllegalArgumentException("The category entered is invalid and does not exist in the database.");
        }

        System.out.println("Enter furniture type: ");
        setType(input.nextLine());
        
        System.out.println("Enter number of items: ");
        setQuantity(input.nextLine());

        input.close();
    }
}
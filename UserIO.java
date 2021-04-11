/**
 @author Rajpreet Gill <a 
  href="mailto:rajpreet.gill@ucalgary.ca">rajpreet.gill@ucalgary.ca</a>
 @author Heidi Schaefer <a 
  href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @author Lubaba Sheikh <a 
  href="mailto:lubaba.sheikh@ucalgary.ca">lubaba.sheikh@ucalgary.ca</a>
 @version 1.6
 @since 1.0
*/

package edu.ucalgary.ensf409;

import java.util.Scanner;

public class UserIO{

    /**
     * This class prompts the user in the command line to enter the category, type of furniture 
     * and the quantity of furniture they would like to order. It contains a method called userInput()
     * that uses a scanner to read input from the terminal. 
     */


    // Variables for category, type and quantity
    private String category;
    private String type;
    private int quantity;

    // Default constructor for UserIO
    public UserIO()
    {
        this.category = null;
        this.type = null;
        this.quantity = 0;
    }

    // Constructor for UserIO that takes in String type category, type and quantity
    public UserIO(String category, String type, String quantity){
        setCategory(category);
        setType(type);
        setQuantity(quantity);
    }

    // Setter method for category, it throws IllegalArgumentException if format is incorrect 
    // and if the category does not exist
    public void setCategory(String furnitureCategory)
    {
        if(!(furnitureCategory.equals("chair")) && !(furnitureCategory.equals("desk")) && !(furnitureCategory.equals("lamp")) && !(furnitureCategory.equals("filing")))
        {
            throw new IllegalArgumentException("The category entered is invalid and does not exist in the database.");
        }
        this.category = furnitureCategory;
    }

    // Setter method for type, it throws IllegalArgumentException if format is incorrect 
    // and if the type does not exist
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

    // Setter method for quantity, it throws IllegalArgumentException if the quantity provided is not an integer.
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

    // Getter method for category
    // returns a category string
    public String getCategory(){
        return this.category;
    }

    // Getter method for type
    // returns a type string
    public String getType(){
        return this.type;
    }

    // Getter method for quantity
    // returns a ingeter quantity
    public int getQuantity(){
        return this.quantity;
    }
    
    
    
    // Method userInput(): call this method in main to prompt user input 
    // This method uses a scanner to take in input from the terminal
    // no return value and arguments
    public void userInput()
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter furniture category: ");
        setCategory(input.nextLine());

        System.out.println("Enter furniture type: ");
        setType(input.nextLine());
        
        System.out.println("Enter number of items: ");
        setQuantity(input.nextLine());

        input.close();
    }
}

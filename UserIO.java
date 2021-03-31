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
        this.category = input.nextLine();

        System.out.println("Enter furniture type: ");
        this.type = input.nextLine();

        System.out.println("Enter number of items: ");
        this.quantity = Integer.parseInt(input.nextLine());

        input.close();
    }
}

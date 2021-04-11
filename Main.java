/**
 @author Rajpreet Gill <a href="mailto:rajpreet.gill@ucalgary.ca">rajpreet.gill@ucalgary.ca</a>
 @author Heidi Schaefer <a href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @author Lubaba Sheikh <a href="mailto:lubaba.sheikh@ucalgary.ca">lubaba.sheikh@ucalgary.ca</a>
 @version 1.5
 @since 1.0
*/

package edu.ucalgary.ensf409;
import java.util.*;

public class Main {
    private static UserIO userIO;
    private static FileIO blank;
    private static FurnitureDb database;
    private static ArrayList<Furniture> furnitureList;
    private static ArrayList<Manufacturer> manufacturers;

    public static void main (String[] args){   
        // Take user input
        userIO = new UserIO();
        userIO.userInput();

        // Create variables from user input
        String category = userIO.getCategory();
        String type = userIO.getType();
        int desiredQuant = userIO.getQuantity();
        int originalQuant = desiredQuant;


        // Connection to database
        database = new FurnitureDb("jdbc:mysql://localhost/inventory", "scm", 
                    "ensf409");
        database.initializeConnection();

        // Create list of furniture from correct category and type
        furnitureList = database.furnitureFinder(category, type);
        
        // Check how many items from the order can be fulfilled based on 
        //  inventory availability
        int maxQuantity = database.componentCounter(furnitureList, desiredQuant);
        
        // If no items can be fulfilled, end program
        if (maxQuantity == 0){
            // Create blank form
            blank = new FileIO();
            blank.blankOrderForm();

            manufacturers = database.manufacturerSuggestion(category);

            System.out.println("The availability of " + type.toLowerCase() + 
                        " " + category + "s is 0.");
            System.out.println();
            System.out.println("A list of manufacturers: ");
            for (int i = 0; i < manufacturers.size(); i++)
            {
                System.out.println(manufacturers.get(i).getName());
            }
            System.out.println();
            System.out.println("We apologize for the inconvenience." + 
                        " Thank you for using our service.");

            System.exit(0);
        }

        // If some items can be fulfilled, but not all
        else if(maxQuantity < desiredQuant){
            manufacturers = database.manufacturerSuggestion(category);

            System.out.println("The availability of " + type.toLowerCase() + 
                        " " + category + "s is " + maxQuantity + ".");
            System.out.println("An order form will be completed for " + 
                        maxQuantity + " " + type.toLowerCase() + " " + 
                        category + "(s).");
            System.out.println();
            System.out.println("A list of manufacturers: ");
            for (int i = 0; i < manufacturers.size(); i++)
            {
                System.out.println(manufacturers.get(i).getName());
            }
            System.out.println();
            System.out.println("We apologize for the inconvenience." + 
                        " Thank you for using our service.");

            desiredQuant = maxQuantity;
        }

        // Finds all combinations
        ArrayList<ArrayList<Furniture>> allCombinations = 
                    database.findCombinations(furnitureList, desiredQuant, 
                    furnitureList.size());
       
        // Finds the cheapest furniture combination to fulfill order
        ArrayList<Furniture> cheapestList = database.priceCheck(allCombinations);
        int totalPrice = 0;
        for(int i = 0; i < cheapestList.size(); i++){
            totalPrice += cheapestList.get(i).getPrice();
        }

        // Print lowest price to terminal
        System.out.println("The lowest price for " + desiredQuant + " " + 
                    type.toLowerCase() + " " + category + "(s) is $" + 
                    totalPrice + ".");

        // Create filled out order form
        FileIO orderForm = new FileIO();
        orderForm.completeOrderForm(cheapestList, totalPrice, type, category, 
                    originalQuant, desiredQuant);

        // Remove furniture from database after order form is printed
        // Commented out for now to avoid removing test objects from database
        /*
        for(int i = 0; i < cheapestList.size(); i++){
            database.removeFurnitureFromInventory(category, 
                        cheapestList.get(i).id);
        }
        */
    }   
}
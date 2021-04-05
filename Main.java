package edu.ucalgary.ensf409;
import java.util.*;

public class Main {

    public static void main (String[] args)
    {
    // Testing blank order form
        FileIO x = new FileIO("hi", "bye");
        x.blankOrderForm();

    //Testing UserIO
        UserIO userIO = new UserIO();
        System.out.println("Before UserInput:");
        System.out.println(userIO.getCategory());
        System.out.println(userIO.getType());
        System.out.println(userIO.getQuantity());
        userIO.userInput();
        System.out.println();
        System.out.println("After UserInput:");
        System.out.println(userIO.getCategory());
        System.out.println(userIO.getType());
        System.out.println(userIO.getQuantity());


        // Running program

        // Create variables from user input
        String category = userIO.getCategory();
        String type = userIO.getType();
        int desiredQuant = userIO.getQuantity();

        // Connection to database
        FurnitureDb database = new FurnitureDb("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        database.initializeConnection();

        // Create list of furniture from correct category and type
        ArrayList<Furniture> furnitureList = database.furnitureFinder(category, type);

        // Check how many items from the order can be fulfilled based on inventory availability
        int maxQuantity = database.componentCounter(furnitureList, desiredQuant);

        // If no items can be fulfilled, end program
        if (maxQuantity == 0){
            Set<Manufacturer> manufacturers = database.manufacturerSuggestion(category);
            // ** send to FileIO for printing **

            System.out.println("The availability of " + type + " " + category + " is 0.");
            System.out.println("A list of manufacturers has been supplied for your convenience.");
            System.out.println("We apologize for the inconvenience, thank you for using our service.");
            System.exit(1);
        }

        else if(maxQuantity < desiredQuant){
            Set<Manufacturer> manufacturers = database.manufacturerSuggestion(category);
            // ** send to FileIO for printing **
            System.out.println("The availability of " + type + " " + category + " is " + maxQuantity);
            System.out.println("An order form will be completed for " + maxQuantity + " " + type + " " + category + "(s).");
            System.out.println("A list of manufacturers has also been supplied for your convenience.");
            System.out.println("We apologize for the inconvenience, thank you for using our service.");
            desiredQuant = maxQuantity;
        }

            // Finds the cheapest furniture combination to fulfill order
            ArrayList<Furniture> cheapestList = database.priceCheck(database.findCombinations(furnitureList, desiredQuant, furnitureList.size()));
            // ** send to FileIO for printing **

            int totalPrice = 0;
            for(int i = 0; i < cheapestList.size(); i++){
                totalPrice += cheapestList.get(i).price;
            }

            System.out.println("The lowest price for " + desiredQuant + " " + type + " " + category + "is " + totalPrice);

            // Remove furniture from database after order form is printed
            /*
            for(int i = 0; i < cheapestList.size(); i++){
                removeFurnitureFromInventory(category, cheapestList.get(i).id);
            }
            */
    }
    
}
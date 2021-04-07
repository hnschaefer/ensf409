package edu.ucalgary.ensf409;
import java.util.*;

public class Main {

    public static void main (String[] args)
    {
        UserIO userIO = new UserIO();
        userIO.userInput();
        
    // Blank form
        //FileIO d = new FileIO();
        //d.blankOrderForm();
        // Running program

        // Create variables from user input
        String category = userIO.getCategory();
        String type = userIO.getType();
        int desiredQuant = userIO.getQuantity();

        // Connection to database
        FurnitureDb database = new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409");
        database.initializeConnection();

        // Create list of furniture from correct category and type
        ArrayList<Furniture> furnitureList = database.furnitureFinder(category, type);


        // Check how many items from the order can be fulfilled based on inventory availability
        int maxQuantity = database.componentCounter(furnitureList, desiredQuant);
        if (maxQuantity >= desiredQuant)
        {
            ArrayList<ArrayList<Furniture>> allCombinations = database.findCombinations(furnitureList, desiredQuant, furnitureList.size());

            // Finds the cheapest furniture combination to fulfill order
            ArrayList<Furniture> cheapestList = database.priceCheck(allCombinations);
            System.out.println("chespestList size:" + cheapestList.size());
            int totalPrice = 0;
            for(int i = 0; i < cheapestList.size(); i++){
                totalPrice += cheapestList.get(i).getPrice();
            }
            // ** send to FileIO for printing **
            FileIO y = new FileIO();
            y.completeOrderForm(cheapestList, totalPrice, type, category, desiredQuant);
        }
        else 
        {
            System.out.println("The availability of " + type.toLowerCase() + " " + category + "s is " + maxQuantity);
            System.out.println("A list of manufacturers has been supplied for your convenience.");
            // add list of manufacturers
            System.out.println("We apologize for the inconvenience, thank you for using our service.");
            System.exit(1);
        }
        
        
    }   
}
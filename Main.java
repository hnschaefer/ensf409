package edu.ucalgary.ensf409;

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
    }
    
}

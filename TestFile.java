/**
 @author Rajpreet Gill <a 
  href="mailto:rajpreet.gill@ucalgary.ca">rajpreet.gill@ucalgary.ca</a>
 @author Heidi Schaefer <a 
  href = "mailto:heidi.schaefer@ucalgary.ca">heidi.schaefer@ucalgary.ca</a>
 @author Lubaba Sheikh <a 
  href="mailto:lubaba.sheikh@ucalgary.ca">lubaba.sheikh@ucalgary.ca</a>
 @version 1.9
 @since 1.0
*/

/*
class TestFile has 17 tests which tests various functions
of the files including FurnitureDb, FileIO, Furniture, Main, Manufacturer
as well as UserIO files.
*/

package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import jdk.jfr.Timestamp;

public class TestFile {
    /** The following method checks if the UserIO constructor
    * initializes the data members inside the Class Manufacturer.
    */
    @Test
    public void checkUserIOConstructor()
    {
        UserIO userObject= new UserIO("chair", "Mesh", "2");
        String result = userObject.getCategory();
        String expResult= "chair";
        assertEquals("The constructor failed to initialize a data member.", expResult, result);
    }

    /**The following method checks if the category setter method
    * correctly throws an error if an invalid category is entered 
    * which does not exist in the database. 
    */
    @Test (expected = IllegalArgumentException.class)
    public void testValidCategorySetter()
    {
        UserIO userObject = new UserIO();
        userObject.setCategory("game");
    }

    /**The following method checks if the category setter method
    * correctly throws an error if an invalid category is entered 
    * with capitals
    */
    @Test (expected = IllegalArgumentException.class)
    public void testCategorySetter()
    {
        UserIO userObject = new UserIO();
        userObject.setCategory("CHair");
    }

    /**The following method checks if the type setter method
    * correctly throws an error if an invalid type is entered 
    * which does not exist in the database. 
    */
    @Test (expected = IllegalArgumentException.class)
    public void testValidTypeSetter()
    {
        UserIO userObject = new UserIO();
        userObject.setCategory("mario");
    }

    /**The following method checks if the category setter method
    * correctly throws an error if an invalid type is entered 
    * with the first letter not capitalized
    */
    @Test (expected = IllegalArgumentException.class)
    public void testTypeSetter()
    {
        UserIO userObject = new UserIO();
        userObject.setCategory("mesh");
    }

    /**The following method checks if the Quantity setter method
    * correctly throws an error if an invalid Quantity is entered 
    */
    @Test (expected = IllegalArgumentException.class)
    public void testValidQuantitySetter()
    {
        UserIO userObject = new UserIO();
        userObject.setCategory("g");
    }

    /** The following method checks if the Manufacturer constructor
    * initializes the data members inside the Class Manufacturer.
    */
    @Test
    public void checkManufacturerConstructor()
    {
        Manufacturer manuObject= new Manufacturer("1234", "name", "0988901234", "province");
        String result = manuObject.getName();
        String expResult= "name";
        assertEquals("The constructor failed to initialize a data member.", expResult, result);
    }
    /**The following method checks if the Furniture constructor
    * initializes the data members inside the Class Furniture.
    */
    @Test 
    public void checkFurnitureConstructor()
    {
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(true);
        Furniture furnitureObject= new Furniture("1234", "type", list, 120,"12345");
        String result = furnitureObject.getType();
        String expResult= "type";
        assertEquals("The constructor failed to initialize a data member.", expResult, result);
    }
    /**The following method checks if the FurnitureDb constructor 
     * initializes the data members in the class properly.
     */
    @Test
    public void checkFurnitureDbConstructor()
    {
    FurnitureDb furnitureDbObject = new FurnitureDb("1234", "username", "password");
    String result = furnitureDbObject.getDburl();
    String expResult = "1234";
    assertEquals("The constructor failed to initialize a data member.", expResult, result);
    }

    /**The following test called checkDatabaseConnection
     * checks if a connection with the database has been sucessfully made
     */
    @Test
    public void checkDatabaseConnection()
    {
        FurnitureDb furnitureObject= new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409"); // need to supply the parameters 
        furnitureObject.initializeConnection();
        int expResult = 1;
        int result = 0;
        try{
            Statement myStmtOne = furnitureObject.getDbconnect().createStatement();
            ResultSet results = myStmtOne.executeQuery("SELECT Type FROM chair");
            while(results.next())
            {
                if(results.getString("Type").equals("Task"))
                {
                    result = 1;
                }
            }
        myStmtOne.close();
        results.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        //furnitureObject.close();
        assertEquals("Connection with database failed.", expResult, result);
    }

    /**The following test called checkFurnitureFinder()
     * checks if the method FurnitureFinder() successfully selects
     * the specified category and type of the furniture from the database. 
     */
    @Test 
    public void checkFurnitureFinder()
    {
        FurnitureDb furnitureObject = new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409");
        furnitureObject.initializeConnection();
        ArrayList<Furniture> furnitureList = furnitureObject.furnitureFinder("chair", "Mesh");
        Furniture furnitureList0 = null;
        for (int i = 0; i < furnitureList.size(); i++)
        {
            if (furnitureList.get(i).id.equals("C0942"))
            {
                furnitureList0 = furnitureList.get(i);
                break;
            }
        }
        
        ArrayList<Boolean> components1 = new ArrayList<Boolean>();
        components1.add(true);
        components1.add(false);
        components1.add(true);
        components1.add(true);

        Furniture testObject = new Furniture("C0942", "Mesh", components1, 175, "005");
        furnitureObject.close();
        assertEquals("The method called checkFurnitureFinder inside the class FurnitureDb does not return an arraylist.", testObject, furnitureList0);
    }

    /**The following test called checkComponentCounter()
     * checks if the method called componentCounter() successfully returns the
     * quantity of a specified furniture which was asked for. 
     */
    @Test 
    public void checkComponentCounter()
    {
        FurnitureDb furnitureObject = new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409");
        furnitureObject.initializeConnection();
        ArrayList<Furniture> furnitureList = furnitureObject.furnitureFinder("chair", "Mesh");
        int counter = furnitureObject.componentCounter(furnitureList, 3);
        System.out.println(counter);
        furnitureObject.close();
        assertEquals("The method componentCounter does not return correct value.", 1, counter);
    }

    /**The following test called testBlankOrderFormMethod()
     * checks if the method called blankOrderFrom successfully 
     * creates a new file.
     */
    @Test
    public void testBlankOrderFormMethod()
    {
            FileIO fileObject = new FileIO();
            fileObject.blankOrderForm();
            
            File file = new File("Blank Furniture Order Form.txt"); // **REMEMBER TO PUT IN THE PATH**
            assertTrue(file.exists());
    }

    /**The following test called testBlankOrderFormContents()
     * checks if the method called blankOrderForm successfully 
     * produces the correct contents in the file. 
     */
    @Test
    public void testBlankOrderFormContents() throws Exception
    {
        FileIO fileObject= new FileIO();
        fileObject.blankOrderForm();
        
        StringBuffer expectedForm = new StringBuffer("Furniture Order Form\n" + "\nFacultyName:"+
                            "\nContact:" +"\nDate:"+"\n"+"\nOriginal Request:"+"\n"+"\nItems Ordered"+
                            "\n"+"\nTotalPrice:");
        File newfile = new File("Expected Blank Furniture Order Form.txt"); // created a File object called newfile
        try
        {
            if (!newfile.exists()) 
            {
                newfile.createNewFile();
            } 
            PrintWriter pw = new PrintWriter(newfile);
            pw.println(expectedForm);
            pw.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedReader reader1 = new BufferedReader(new FileReader("Blank Furniture Order Form.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("Expected Blank Furniture Order Form.txt"));
        String l1 = reader1.readLine();
        String l2 = reader2.readLine();
        boolean areEqual = true;
        int lineNum = 1;
        while (l1 != null || l2 != null)
        {
            if(l1 == null || l2 == null)
            {
                areEqual = false;
                 
                break;
            }
            else if(! l1.equalsIgnoreCase(l2))
            {
                areEqual = false;
                 
                break;
            } 
            l1 = reader1.readLine();
             
            l2 = reader2.readLine();
             
            lineNum++;
        }
        assertTrue("The file does not match the expected outcomes",areEqual);
    }

    

    /**The following test called testCompleteOrderFormMethod()
     * checks if the method called completeOrderForm successfully 
     * creates a new file.
     */
    @Test
    public void testCompleteOrderFormMethod() throws Exception
    {
        FileIO fileObject= new FileIO();
        ArrayList<Boolean> components1 = new ArrayList<Boolean>();
            components1.add(true);
            components1.add(false);
            components1.add(true);
            components1.add(true);
        fileObject.completeOrderForm(components1, 100, "Mesh", "chair", 1, 1);
        String type = "Mesh";
        String category= "chair";
        int originalQuant= 1;
        int availableQuant= 1;
        int totalPrice= 100;
        StringBuffer expectedForm = new StringBuffer("Furniture Order Form\n" + "\nFacultyName:"+
                                                    "\nContact:" +"\nDate:"+"\n"+"Original Request: "+ 
                                                    type + " " + category + ", " + originalQuant + "\n" + 
                                                    "Available: " + type + " " + category + ", " + availableQuant + 
                                                    "\n" + "Items Ordered" + "\n" + "Total Price: $" + totalPrice);
                            
        File newfile = new File("Expected Furniture Order Form.txt"); // created a File object called newfile
        try
        {
            if (!newfile.exists()) 
            {
                newfile.createNewFile();
            } 
            PrintWriter pw = new PrintWriter(newfile);
            pw.println(expectedForm);
            pw.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedReader reader1 = new BufferedReader(new FileReader("Furniture Order Form.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("Expected Furniture Order Form.txt"));
        String l1 = reader1.readLine();
        String l2 = reader2.readLine();
        boolean areEqual = true;
        int lineNum = 1;
        while (l1 != null || l2 != null)
        {
            if(l1 == null || l2 == null)
            {
                areEqual = false;
                 
                break;
            }
            else if(! l1.equalsIgnoreCase(l2))
            {
                areEqual = false;
                 
                break;
            } 
            l1 = reader1.readLine();
             
            l2 = reader2.readLine();
             
            lineNum++;
        }
        assertTrue("The file does not match the expected outcomes",areEqual);
    }
    /**The following test called testRemoveFurnitureMethod()
     * checks if the method called RemoveFurnitureMethod() successfully 
     * removes a chair from the database.
     */
    @Test
    public void testRemoveFurnitureMethod()
    {
        {
            FurnitureDb furnitureObject = new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409");
            furnitureObject.initializeConnection();
            furnitureObject.removeFurnitureFromInventory("chair", "C9890");
            String expResult ="false";
            String result = "true";
            try{
                Statement myStmtOne = furnitureObject.getDbconnect().createStatement();
                ResultSet results = myStmtOne.executeQuery("SELECT id FROM chair WHERE id ='C9890'");
                if(!(results.next()))
                {
                    expResult = "true";
                }
            myStmtOne.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
            assertEquals("The method does not remove the category passed with ID.", expResult, result);
        }
    }
    /**The following test called testAddFurnitureToInventory()
     * checks if the method called addFurnitureToInventory successfully 
     * adds back the removed chair from the previous method.
     */
    @Test 
    public void testAddFurnitureToInventory()
    {
            FurnitureDb furnitureObject = new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409");
            furnitureObject.initializeConnection();
            ArrayList<Boolean> components1 = new ArrayList<Boolean>();
            components1.add(false);
            components1.add(true);
            components1.add(false);
            components1.add(true);
            Furniture testObject = new Furniture("C9890", "Mesh", components1, 50, "003");
            furnitureObject.addFurnitureToInventory(testObject, "chair");
            String expResult= "true";
            String result= "false";

            try{
                Statement myStmtOne = furnitureObject.getDbconnect().createStatement();
                ResultSet results = myStmtOne.executeQuery("SELECT id FROM chair WHERE id ='C9890'");
            if((results.next()))
            {
                result="true";
            }
            myStmtOne.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
            assertEquals("The method does not add to the database.", expResult, result);
    }
}
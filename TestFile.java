/**
 @author 
 @version 1.0
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.sql.*;
import java.util.*;
//import org.junit.contrib.java.lang.system.ExpectedSystemExit;

//import jdk.internal.jshell.tool.MessageHandler;
import jdk.jfr.Timestamp;

public class TestFile {
/* The following method checks if the UserIO constructor
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

/* The following method checks if the Manufacturer constructor
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
/* The following method checks if the Furniture constructor
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
/* The following method checks if the FurnitureDb constructor 
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

/* The following test called checkDatabaseConnection
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
    assertEquals("The method called insertNewChair inside the class FunTestFile does not insert a new chair into the database.", expResult, result);
}

/* The following test called checkFurnitureFinder()
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

/* The following test called checkComponentCounter()
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


// this test performs if the method called insertNewChair
// actually inserts a new chair into the database 
/*@Test 
public void testAddingNewChairToDatabase()
{
        FunTestFile object = new FunTestFile(); 
        object.insertNewChair("C1234", "cool", "Y", "Y", "Y", "Y", "120", "000");
        String type = "cool"; 
        //checking if this method actually created a new chair in the database 
        String expResult= "false";
        String result= "true";
        try{
            Statement myStmtOne= dbConnect.createStatement();
            results = myStmtOne.executeQuery("SELECT type FROM chair WHERE type= " + type);
        if((results.next()))
        {
            expResult="true";
        }
        myStmtOne.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        assertEquals("The method called insertNewChair inside the class FunTestFile does not insert a new chair into the database.", expResult, result);
}*/

/* The following test called testBlankOrderFormMethod()
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

/* The following test called testBlankOrderFormContents()
 * checks if the method called blankOrderFrom successfully 
 * produces the correct contents in the file. 
 */
/* @Test
public void testBlankOrderFormContents()
{
    FileIO fileObject= new FileIO();
    fileObject.blankOrderForm();
    File file = new File("Blank Furniture Order Form.txt");
    Sting filePath = file.getPath(); //put in filePath
    String result = readFile(filePath);
    String expResult = "Furniture Order Form\n" + "\nFacultyName:"+
                        "\nContact:" +"\nDate:"+"\n"+"\nOriginal Request:"+"\n"+"\nItems Ordered"+
                        "\n"+"\nTotalPrice:";
    assertEquals("The file contents are incorrect", expResult, result);
} */
/*@Test
public void testBlankOrderFormMethod()
{
    FileIO fileObject= new FileIO();
    fileObject.blankOrderForm();
    String result = "false";
    String file= "Blank Furniture Order Form.txt";
    if(file.exists)
    {
        result="true";
    }
    String expResult = "true";
    assertEquals("The method called blankOrderForm failed to create the file", expResult, result);
} */


/* The following test called testCompleteOrderFormMethod()
 * checks if the method called completeOrderForm successfully 
 * creates a new file.
 */
    @Test
    public void testCompleteOrderFormMethod()
    {
        FileIO fileObject= new FileIO();
        ArrayList<Boolean> components1 = new ArrayList<Boolean>();
        components1.add(true);
        components1.add(false);
        components1.add(true);
        components1.add(true);
        Furniture testObject = new Furniture("C0942", "Mesh", components1, 175, "005");
        ArrayList <Furniture> list = new ArrayList<>();
        list.add(testObject);
        fileObject.completeOrderForm(list, 150, "Mesh", "2");
        String category= "chair";
        String type= "mesh";
        int quantity= 2;
        File file = new File("Furniture Order Form.txt");
        String filePath = file.getPath(); //put in filePath
        String result = readFile(filePath);
        String expResult= "\n" + "\nFacultyName:" + "\nContact:" +"\nDate:" + 
        "\n" + "Original Request: " + type + " " + category + ", " + quantity;
        
        assertEquals("The file contents are incorrect", expResult, result);
    }
    @Test
    public void testRemoveFurnitureMethod()
    {
        FurnitureDb furnitureObject = new FurnitureDb("jdbc:mysql://localhost/inventory", "ENSF409", "ensf409");
        furnitureObject.initializeConnection();
        furnitureObject.removeFurnitureFromInventory("chair", "C9890");
        String expResult ="false";
        String result = "true";
        try{
            Statement myStmtOne = furnitureObject.getDbconnect().createStatement();
            ResultSet results = myStmtOne.executeQuery("SELECT id FROM chair WHERE id = " + "C9890");
            if(!(results.next()))
            {
                expResult = "true";
            }
        myStmtOne.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        assertEquals("The method componentCounter does not return correct value.", expResult, result);
    }
}

/**
 @author 
 @version 1.0
 
 @since 1.0
*/

package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import jdk.jfr.Timestamp;


public class FinalProjectTest()
{
    public void FinalProjectTest()
    {
    }
/* The following method checks if the UserIO constructor
* initializes the data members inside the Class Manufacturer.
*/
@Test
public void checkUserIOConstructor()
{
    UserIO userObject= new UserIO("category", "type", 2);
    String result = userObject.getCategory();
    String expResult= "category";
    assertEquals("The constructor failed to initialize a data member.", expResult, result);
}
/* The following method checks if the method called userInput
* correctly throws an error if an invalid category/type is entered 
* which does not exist in the database. 
*/
@Test(expected=IllegalArgumentException.class)
public void checkUserInputMethod()
{
    UserIO userObject = new UserIO("shelf","wooden", 2);
    String result = UserIO.userInput();
}
/* The following method checks if the Manufacturer constructor
* initializes the data members inside the Class Manufacturer.
*/
@Test
public void checkManufacturerConstructor()
{
    Manufacturer manuObject= new Manufacturer("1234", "name", "0988901234", "province")
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
    Furniture furnitureObject= new Furniture("1234", "type", list, "120","12345");
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
 FurnitureDb furnitureDbObject new FurnitureDb("1234", "username", "password");
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

}

/* The following test called checkFurnitureFinder()
 * checks if the method FurnitureFinder() successfully selects
 * the specified category and type of the furniture from the database. 
 */
@Test 
public void checkFurnitureFinder()
{

}
/* The following test called checkComponentCounter()
 * checks if the method called componentCounter() successfully returns the
 * quantity of a specified furniture which was asked for. 
 */
@Test public void checkComponentCounter()
{

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
public testBlankOrderFormMethod()
{
    FileIO fileObject= new FileIO();
    fileObject.blankOrderForm();
    File file = new File("c:/pathOfTheCreatedFile"); // **REMEMBER TO PUT IN THE PATH**
    assertTrue(file.exists());
}
/* The following test called testBlankOrderFormContents()
 * checks if the method called blankOrderFrom successfully 
 * produces the correct contents in the file. 
 */
@Test
public testBlankOrderFormContents()
{
    FileIO fileObject= new FileIO();
    fileObject.blankOrderForm();
    File file = new File("c:/pathOfTheCreatedFile");
    
    if(file.exists())
    {
        //check the contents now 

    }
    else if(!(file.exists())
    {
        Boolean status= false; 
        assertFalse("Adding a data element with an existing key returned true", status);
    }
    assertEquals("The file contents are incorrect", expResult, result);
}
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

}


}
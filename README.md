ensf409
# ENSF 409 Final Project

Final Project for ENSF 409 - Principles of Software Development - Winter 2021 at the University of Calgary

By Group 56: R. Gill, H. Schaefer, L. Sheikh

The purpose of this program is to facilitate the buying of used furniture from a database. 
The user is prompted to input the category, type and quantity of furniture they desire.
The program then calculates the cheapest option of furniture from the inventory which can be used to fulfill the order.
Once the best furniture option has been selected, an order form will be printed into a .txt file,
and the price will be printed onto the terminal.
If the order cannot be fulfilled, a list of suggested manufacturers will be printed onto the terminal.

A number of tests have also been created to check the validity of the code.
These tests can be run using JUnit 4.
When the tests are run, files "Expected Blank Furniture Order Form.txt" and "Expected Furniture Order Form.txt" will be created,
these files can be disregarded, as they are for testing purposes only.
Please note that TestFile MUST BE RUN BEFORE THE REST OF THE PROGRAM to ensure all items from the database are present for the tests.

To run the tests in Command Prompt on Windows, navigate to the Group56_FinalProject directory and use the following commands:

javac -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar edu/ucalgary/ensf409/*.java
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.TestFile

Once tests are complete, the remainder of code can be run using the following command:

java -cp .;lib/mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/Main

Follow the prompts and enjoy!

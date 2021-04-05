ensf409
# ENSF 409 Final Project

Final Project for ENSF 409 - Principles of Software Development - Winter 2021 at the University of Calgary

By R. Gill, H. Schaefer, L. Sheikh

The purpose of this program is to facilitate the buying of used furniture from a database. 
The user is prompted to input the category, type and quantity of furniture they desire.
The program then calculates the cheapest option of furniture from the inventory which can be used to fulfill the order.
Once the best furniture option has been selected, an order form will be printed into a .txt file,
and the price will be printed onto the terminal.
If the order cannot be fulfilled, the order form will contain a list of suggested manufacturers.

A number of tests have also been created to check the validity of the code.
These tests can be run using JUnit 5.

To run this code in a terminal, navigate to appropriate directory and use the following commands:

javac -cp .;lib/mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/*.java

java -cp .;lib/mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/Main

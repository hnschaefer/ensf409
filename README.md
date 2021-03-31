# ensf409
ENSF 409 Final Project


**/v1**
Distinct furniture categories
Each category has its own methods, own class
ie. Chair, Desk, Filing, Lamp

Pros:
-Consistent, dependable results

Cons:
-No support for new categories
-Lots of code repetition

**/v2**
A more universal approach
Methods work for any category of furniture
Class Furniture universal takes into account similarities and differences between categories
ie. All categories have id, type, price and manuID
    but varying components with boolean values (arms, bulbs, cushions, etc.)
    
Pros:
-Can easily update database to have new categories
-Less repetition, improved modularity

Cons:
-May not yet be reliable
-Database and user input will have to be more carefully monitored

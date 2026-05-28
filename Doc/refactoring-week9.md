## Week 9

1. Fixed getters in the Material class

getName(), getRecyclingCategory(), and getRecyclingInstruction() were returning empty values just for us to be able to run the code without compiling errors.   
We fixed this by creating a method returning actual values in the RecyclingGuidance class.  
We added new tests to see if the correct values are getting returned as well.

2. Made Product constructor public

The Product constructor had no access modifier, which meant the test could not run. To fix it we made it publicly accessible. 

3. Implemented \`RecyclingGuidance.generateGuidance()\`

The method was returning a string "String". There was no logic so it could not be tested properly.   
We implemented a switch statement that iterates through a product's materials and gives each RecyclingCategory a concrete guidance string.

4. Created new tests

Added a few normal-case and edge-case tests for Product, Material and Recycling Guidance classes.

5  CI  
We created a Java CI that activates for every push and pull request. Compiles and runs our tests in github.
# Design Reflection

## Architectural Decisions

One of the most important decisions in this project was to use a layered architecture with separated Domain, Application, and Presentation layers. The main reason for choosing this approach was to separate responsibilities completely and avoid mixing the user interface code together with the actual business logic.

The Presentation layer only handles user interaction through the ConsoleUI class, meaning it just collects input from the user and displays the output. It does not perform any calculations or store data, which makes the interface a lot easier to change in the future because the business logic is not tied to the console at all.

The Application layer contains the services that coordinate the system. ProductService and MaterialService are responsible for tasks such as creating objects, validating data, loading and saving files, and connecting the user interface with the domain objects. This layer also contains StrategyService, which is responsible for selecting and creating the correct environmental impact calculation strategy based on the user's choice. Because of these responsibilities, the Application layer acts as a bridge between the Presentation and Domain layers and keeps business operations separate from user interface logic.

The Domain layer contains the core concepts of the system, such as Product, Material, and the impact calculation strategies. Keeping these classes completely independent from the user interface makes them much easier to understand, test, and reuse in different parts of the project.

## Use of the Strategy Pattern

The most important design pattern used in the project is the Strategy pattern. The system needs to support different ways of calculating environmental impact, and instead of placing all these calculations inside one large method with multiple if or switch statements, each calculation method was implemented as its own strategy class.

The ImpactCalculationStrategy interface defines a shared method that all calculation strategies must follow, while SimpleImpactStrategy and WeightedImpactStrategy provide different ways of performing the calculation. This means the Product class does not need to know how the calculation is performed. It only receives a strategy object and uses it to calculate the environmental impact. 

This approach follows the Open/Closed Principle because new calculation methods can be added without changing the existing calculation code. For example, if a new strategy is needed in the future, we can just create a new strategy class that implements the interface.

The Strategy pattern also improves testing quite a bit because each calculation method can be tested separately from the rest of the system.


## Evaluation of Design Principles

The Single Responsibility Principle was followed by giving each class a clear purpose. For example, MaterialService manages materials, ProductService manages products, and ConsoleUI handles the user interface interaction.

The Open/Closed Principle is supported by the Strategy pattern, meaning new impact calculations can be added with minimal changes to the existing code.

The Dependency Inversion Principle is partially followed because the calculation logic depends on the ImpactCalculationStrategy abstraction instead of specific implementations, which reduces coupling between classes and makes the system more flexible.

## Technical Debt and Possible Improvements

One source of technical debt is the file persistence logic. Both ProductService and MaterialService contain very similar code for saving and loading data, which results in unnecessary code duplication. A better solution for this would be to move file handling into a separate repository or storage class.

Another issue is that some methods call loadProductsFromFile() multiple times. While this works fine for a small project, it may become inefficient as the amount of data grows. A future version could load data just once during startup and update it only when it is absolutely necessary.

The user interface currently contains validation logic and menu handling inside large methods. As more features are added, these methods may become difficult to maintain, so splitting them into smaller methods would improve readability.

## Conclusion

Overall, the project demonstrates how layered architecture and the Strategy pattern can be used to create a flexible and maintainable design. The separation of responsibilities made development easier and reduced coupling between different parts of the system. While there are definitely areas that could be improved, especially regarding file persistence and code duplication, the current design provides a good start for future development and extension.


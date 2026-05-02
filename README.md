# Recycling Management System
This project is a console application for managing products and their materials. 
The goal is to make it easier to understand the environmental impact of different products and how they should be recycled.

Users can create products, assign materials to them, and view information about each product. 
The system will also calculate environmental impact and provide recycling guidance based on the materials used.

The project focuses on clean object-oriented design, clear structure, and separation between different parts of the system.

## Going to use 
- Java
- Console application
- Git and GitHub

## Functional Requirements

- Create and manage products
- Assign one or more materials to a product
- View a list of all products
- View detailed information about a product
- Define and manage materials
- Calculate environmental impact for a product
- Provide recycling instructions based on materials

## Non-Functional Requirements

- The system must follow object-oriented design lesons 
- The application must be easy to maintain and extend
- The system must be testable
- The application must run in a console 
- Code should be clean and readable

## Branch Strategy

- main: Stable version of the project ( with a protected branch)
- develop: Main development branch where features are combined
- feature/*: Used for developing new features then merging to develop 

All changes are made in feature branches and merged into develop.
When stable, develop is merged into main.

## Architectural Decisions

The system is designed using a layered architecture to ensure clear separation of concerns, maintainability, and testability.

### Layered Architecture

The application is divided into three main layers:

#### Domain Layer
Contains the core business logic and domain model. This includes entities such as Product, Material, RecyclingCategory, and RecyclingGuidance. It also includes the ImpactCalculationStrategy interface and its implementations. The domain layer is independent of any UI or application services and represents the pure business rules of the system.

#### Application Layer
Contains services that coordinate domain objects and implement use cases. ProductService and MaterialService belong here. This layer handles operations such as creating products, retrieving data, and calculating impact by delegating to domain strategies. It depends on the domain layer but contains no UI logic.

#### Presentation Layer
Contains the console-based user interface (ConsoleUI). It is responsible only for input and output operations and does not contain business logic. It communicates with the application layer to execute user actions.

### Design Principles Applied

The architecture follows SOLID principles:

* SRP (Single Responsibility Principle): Each class has one clear responsibility. Domain entities represent data and behavior related to the business concept only. Services handle orchestration. UI handles interaction only.
* OCP (Open/Closed Principle): Impact calculation is implemented using the Strategy pattern. New calculation methods can be added without modifying existing code.
* DIP (Dependency Inversion Principle): High-level services depend on abstractions (ImpactCalculationStrategy) rather than concrete implementations, enabling flexibility and testability.

## Design Pattern: Strategy

### What problem did it solve?
Environmental impact calculation had to support multiple different algorithms
(simple strategy and weighted by lifespan strategy). Without a pattern, this logic would live
inside ProductService as an if/switch block — adding a new algorithm would
mean editing existing code, which violates OCP.

### Why was Strategy the right choice?
The Strategy pattern allows each calculation algorithm to be its own class.
ProductService depends only on the ImpactCalculationStrategy interface, not
on any concrete implementation — it doesn't know or care which strategy it uses.

### What improved?
- Adding a new calculation method requires only creating a new class
- ProductService and Product never need to change
- Each strategy can be tested in isolation

### What would happen without it?
ProductService would grow an if/switch block every time a new calculation rule
was needed. The UI would need to know about concrete calculation classes.
Testing would be harder because all logic would be tangled in one method.

## Team
- Abdulfatah Ijbara – Developer, owner of github repo
- Raluca Teodora Tabacaru - Developer 
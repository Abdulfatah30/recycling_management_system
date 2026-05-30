package Presentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Application.MaterialService;
import Application.ProductService;
import Application.StrategyService;
import Domain.ImpactCalculationStrategy;
import Domain.Material;
import Domain.Product;
import Domain.RecyclingCategory;



/**
 * The presentation layer.
 * 
 * ConsoleUI is responsible for showing the menus in the terminal,
 * handling different inputs, and displaying the corresponding output/messages to the user.
 */
public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);


    /** Service used to manage products. */
    private final ProductService productService;

    /** Service used to manage materials. */
    private final MaterialService materialService;
    

    /**
     * Creates a new ConsoleUI object.
     * 
     * The constructor gets injected from Main with materialService
     * and productService so it does not violate DIP and OCP
     * 
     * @param materialService service for material operations
     * @param productService service for product operations
     */
    public ConsoleUI(MaterialService materialService, ProductService productService){
        this.productService = productService;
        this.materialService = materialService;
    }


    /**
     * Runs the main menu loop for the console application.
     * Reads user input, processes menu selections through switch cases,
     * and triggers the corresponding methods until the user exits.
     * 
     * If the user inserts an invalid choice it will display an error message and reset the loop.
     */
    public void start(){
        boolean running = true;
        
        displayMainMenu();
        while(running){
            System.out.print("\n⮚ Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    controllCreateProduct();
                    break;
                case "2":
                    controllCreateMaterials();
                    break;
                case "3":
                    controllListProducts();
                    break;
                case "4":
                    controllListMaterials();
                    break;
                case "5":
                    controllProductImpact();
                    break;
                case "6":
                    controllRecyclingMenu();
                    break;
                case "7":
                    displayMainMenu();
                    break;
                case "8":
                    running = false;
                    System.out.println("𝙴𝚡𝚒𝚝𝚒𝚗𝚐 𝚝𝚑𝚎 𝚙𝚛𝚘𝚐𝚛𝚊𝚖...");
                    break;
                default:
                    System.out.println("𝙸𝚗𝚟𝚊𝚕𝚒𝚍 𝚒𝚗𝚙𝚞𝚝, 𝚙𝚕𝚎𝚊𝚜𝚎 𝚝𝚛𝚢 𝚊𝚐𝚊𝚒𝚗!");
            }
        }
    }


    /**
     * Displays the main menu of the application in the terminal.
     * The user can see what choices are available.
     */
    public void displayMainMenu(){
        System.out.println("\n╭──── ⋅ ⋅ ⋅ ⋅ ⋅ ⋅  ──── ✩ ────  ⋅ ⋅ ⋅ ⋅ ⋅ ⋅ ────╮");
        System.out.println("       ❀ 𝕊𝕌𝕊𝕋𝔸𝕀ℕ𝔸𝔹𝕃𝔼 ℙℝ𝕆𝔻𝕌ℂ𝕋 𝕄𝔸ℕ𝔸𝔾𝔼𝕄𝔼ℕ𝕋 ❀");
        System.out.println("╰──── ⋅ ⋅ ⋅ ⋅ ⋅ ⋅  ──── ✩ ────  ⋅ ⋅ ⋅ ⋅ ⋅ ⋅ ────╯");
        System.out.println("");
        System.out.println("⓵ Create New Product");
        System.out.println("⓶ Create New Material");
        System.out.println("⓷ List All Products");
        System.out.println("⓸ List All Materials");
        System.out.println("⓹ Calculate Environmental Impact");
        System.out.println("⓺ Recycling Guidance Menu");
        System.out.println("⓻ Show menu");
        System.out.println("⓼ ⏻ Exit Application");
        System.out.println("");
        System.out.println("✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
    }



    /**
     * Creates a new product using information entered by the user.
     * 
     * The user must enter:
     * - product name
     * - category
     * - lifespan
     * - one or multiple materials
     * 
     * If the material does not already exist the user can choose to create it.
     * It activates the createMaterial method so the user does not have to exit the app
     * or the menu choice.
     */
    public void controllCreateProduct() {
        List<String> productMaterials = new ArrayList<>();
    String productname = validateString("Give a product name: ","Please enter a product name!");
    /**
     * This if statment prevents duplicate products because product names must be unique.
     */
    if(productService.getProductDetails(productname) != null){
        System.out.println("Product already exist!!");
        return;
    }

    String productcategory = validateString("Give a category name: ","Please enter a category name!");

    int productlifespan = validateInt("Give a lifespan value: ","Please enter an integer!");

    boolean moreMaterial = true;
    do {
        String productmaterial = validateString("What material does the product have: ","Can't put an empty Material!");
        if(materialService.findByMaterialName(productmaterial) != null){
            productMaterials.add(productmaterial);
        } else{
            System.out.println("Matrial not found.");
            System.out.print("Want to continue or create that Material? Y/N/C: ");
            String answer = validateString("Want to continue Y/N or create that Material and continue? type (C): ", "Please enter a valid choice").trim().toUpperCase();

            if(answer.contains("N")){
                return;
            }


            /**
             * Allows the user to create a new material without
             * needing to exit the product menu choice.
             */
            if(answer.contains("C")){
                productMaterials.add(controllCreateMaterials().getName().trim());
                }
            }
        while (true) {
            System.out.print("Does it have more materials? Y/N: ");
            String answer = scanner.nextLine().trim().toUpperCase();
            switch (answer) {
                case "Y":
                    moreMaterial = true;
                    break;
                case "N":
                    moreMaterial = false;
                    break;
                default:
                    System.out.println("Invalid answer, please type Y or N");
                    continue;
            }
            break;
        }
    }
     while (moreMaterial);
        Product product = productService.createProduct(productname, productcategory, productlifespan, productMaterials);
        System.out.println("Successfully created Product '" + product.getName().trim() + "' created with " + product.getMaterials().size() + " material(s).");
    }



        /**
     * Creates a new material based on user inputs. 
     * 
     * The user must enter:
     * - material name
     * - environmental impact value
     * - recycling category
     * 
     * @return returns the created material if the creation succeeded or returns null.
     */
    private Material controllCreateMaterials() {
        RecyclingCategory materialRecyclingCategory;

        String materialName = validateString("Give material name: ", "Material name cannot be empty!").trim();
        if(materialService.findByMaterialName(materialName) != null){
            System.out.println("Material already exists!");
            return null;
        }
        
        double impactValue = validateDouble("Enter the impact value: ","Please enter a valid number!");

        while (true) {
        recyclingCategoryMenu();
        
        int answer = validateInt("What recycling category is the material?: ", "please enter a valid integer");
        
        
        switch (answer) {

            case 1:
                materialRecyclingCategory = RecyclingCategory.PLASTIC;
                break;
                
            case 2:
                materialRecyclingCategory = RecyclingCategory.METAL;
                break;
                
            case 3:
                materialRecyclingCategory = RecyclingCategory.GLASS;
                break;
                    
            case 4:
                materialRecyclingCategory = RecyclingCategory.PAPER;
                break;

            case 5:
                materialRecyclingCategory = RecyclingCategory.ORGANIC;
                break;
                
            case 6:
                materialRecyclingCategory = RecyclingCategory.ELECTRONIC;
                break;
                
            case 7:
                materialRecyclingCategory = RecyclingCategory.HAZARDOUS;
                break;

            case 8:
                materialRecyclingCategory = RecyclingCategory.WOOD;
                break;
            case 9:
                materialRecyclingCategory = RecyclingCategory.NON_RECYCLABLE;
                break;
                
            default:
                System.out.println("Invalid category");
                continue;
            }
            
            break; 
        }
            try {
            Material material = materialService.createMaterial(materialName.trim(),impactValue,materialRecyclingCategory);
            
            System.out.println("Material created successfully");
            return material;
        } catch (Exception e) {
                    System.out.println(e);
                }
    return null;
    }



    /**
     * Displays all products from the list.
     * If the list is empty it will print an error message.
     * 
     * Each product shows:
     * - product name
     * - category
     * - lifespan
     * - materials
     */
    public void controllListProducts(){
          List<Product> products = productService.listProducts();
          if (products.isEmpty()) {
              System.out.println("No products registered yet.");
          return;
          }
        System.out.println("\n✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆Products⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
          for (Product p : products) {
          System.out.printf("  %-15s | Category: %-15s | Lifespan: %-3d yr(s)  | Materials: ",
           p.getName(),
            p.getCategory(),
             p.getEstimatedLifespanYears());

          for (Material m : p.getMaterials()) {
            System.out.print("(" + m.getName() + ") ");
          }
          System.out.println();
          }
    }
    
    
    
    /**
     * Displays all materials from the list.
     * If the list is empty it will print an error message.
     * 
     * Each material shows:
     * - material name
     * - environmental impact value
     * - recycling category
     */
    public void controllListMaterials(){
        List<Material> materials = materialService.listMaterials();
          if (materials.isEmpty()) {
              System.out.println("No material registered yet.");
              return;
          }
          System.out.println("\n✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆Materials⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
          for (Material m : materials) {
          System.out.printf("  %-25s | ImpactValue: %-14f | RecyclingCategory: %-10s\n",
           m.getName(),
           m.getImpactValue(),
             m.getRecyclingCategory());
          }
      }


      
      /**
       * A menu displaying diffrent types of recyling categories to choose from.
       */
      public void recyclingCategoryMenu(){
          System.out.println("""
          1.PLASTIC
          2.METAL
          3.GLASS
          4.PAPER
          5.ORGANIC
          6.ELECTRONIC
          7.HAZARDOUS
          8.WOOD
          9.NON_RECYCLABLE
          """);
      }
      
      
      
    /**
     * Calculates and displays the enviromental impact of a product.
     * The product must already exist on the list.
     * 
     * The user selects which calculation strategy they want to use for that specific product. 
     */
    public void controllProductImpact() {
    String name = validateString("Enter product name: ", "Product can't be empty!!");

    System.out.println("Choose a calculation strategy:");
    System.out.println("  1. Simple (sum of material impacts)");
    System.out.println("  2. Weighted (adjusted for product lifespan)");
    int choice = validateInt("Your choice: ", "Please enter a valid integer!!");
    while (true) {
        
        if(choice != 1 && choice != 2){
            System.err.println("Please enter a valid integer!!");
            choice = validateInt("Your choice: ", "Please enter a valid integer!!");
            continue;
        }
        break;
    }

    Product product = productService.getProductDetails(name);
    if (product == null) {
        System.out.println("Product not found.");
        return;
    }
    
    ImpactCalculationStrategy strategy = StrategyService.create(choice, product.getEstimatedLifespanYears());
    double impact = productService.calculateImpact(name, strategy);
    
    System.out.printf("Environmental impact for %s: %.2f%n", name, impact);
    }



    /**
    * The user needs to enter a products name already existing on the list.
    * This will display a recycling guidance telling the user how the product can be recycled. 
    */
    public void controllRecyclingMenu() {
    System.out.println("\n♻  ─── Recycling Guidance ───  ♻");
    String name = validateString("Enter product name to get recycling guidance (b to get back): ", "Product can't be empty!!").trim();
    if(name.equalsIgnoreCase("b")){
        displayMainMenu();
        return;
    }

    Product product = productService.getProductDetails(name);
    if (product == null) {
        System.out.println("Product not found.\nPlease create it by typing '1' ");
        return;
    }


    String advice = productService.getRecyclingGuidance(product);

    System.out.println("\n┌"+"─".repeat(45)+"┐");
    System.out.printf ("│  Product  : %-32s│%n", product.getName().trim());
    System.out.printf ("│  Category : %-32s│%n", product.getCategory());
    System.out.printf ("│  Materials: %-32d│%n", product.getMaterials().size());
    System.out.println("├"+"─".repeat(45)+"┤");
    System.out.printf ("│  Guidance : %-32s│%n", advice);
    System.out.println("└"+"─".repeat(45)+"┘");
    }
    
        /**
         * Validates a string input from the user.
         *
         * Keeps asking until the user enters a non-empty value.
         *
         * @param message prompt message shown to the user
         * @param errorMessage message shown when input is empty
         * @return a valid non-empty string input
         */
        public String validateString(String message, String errorMessage) {
            while (true) {
                System.out.print(message);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.err.println(errorMessage);
                    continue;
                }

                return input;
            }
        }



        /**
         * Validates an integer input from the user.
         *
         * Keeps asking until the user enters a valid integer value.
         *
         * @param message prompt message shown to the user
         * @param errorMessage message shown when input is not a valid integer
         * @return a valid integer value
         */
        public int validateInt(String message, String errorMessage) {
            while (true) {
                System.out.print(message);
                String input = scanner.nextLine().trim();

                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.err.println(errorMessage);
                }
            }
        }


        /**
         * Validates a double (decimal number) input from the user.
         *
         * Keeps asking until the user enters a valid decimal number.
         *
         * @param message prompt message shown to the user
         * @param errorMessage message shown when input is not a valid number
         * @return a valid double value
         */
        public double validateDouble(String message, String errorMessage) {
            while (true) {
                System.out.print(message);
                String input = scanner.nextLine().trim();

                try {
                    return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.err.println(errorMessage);
                }
            }
        }



}

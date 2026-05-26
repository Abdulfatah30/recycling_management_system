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

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private final ProductService productService;
    private final MaterialService materialService;
    
    public ConsoleUI(MaterialService materialService, ProductService productService){
        this.productService = productService;
        this.materialService = materialService;
    }
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
                    controllListProducts();
                    break;
                case "3":
                    controllListMaterials();
                    break;
                case "4":
                    controllProductImpact();
                    break;
                case "5":
                    controllRecyclingMenu();
                    break;
                case "6":
                    displayMainMenu();
                    break;
                case "7":
                    running = false;
                    System.out.println("𝙴𝚡𝚒𝚝𝚒𝚗𝚐 𝚝𝚑𝚎 𝚙𝚛𝚘𝚐𝚛𝚊𝚖...");
                    break;
                default:
                    System.out.println("𝙸𝚗𝚟𝚊𝚕𝚒𝚍 𝚒𝚗𝚙𝚞𝚝, 𝚙𝚕𝚎𝚊𝚜𝚎 𝚝𝚛𝚢 𝚊𝚐𝚊𝚒𝚗!");
            }
        }
    }
    public void displayMainMenu(){
        System.out.println("\n╭──── ⋅ ⋅ ⋅ ⋅ ⋅ ⋅  ──── ✩ ────  ⋅ ⋅ ⋅ ⋅ ⋅ ⋅ ────╮");
        System.out.println("       ❀ 𝕊𝕌𝕊𝕋𝔸𝕀ℕ𝔸𝔹𝕃𝔼 ℙℝ𝕆𝔻𝕌ℂ𝕋 𝕄𝔸ℕ𝔸𝔾𝔼𝕄𝔼ℕ𝕋 ❀");
        System.out.println("╰──── ⋅ ⋅ ⋅ ⋅ ⋅ ⋅  ──── ✩ ────  ⋅ ⋅ ⋅ ⋅ ⋅ ⋅ ────╯");
        System.out.println("");
        System.out.println("⓵ Create New Product");
        System.out.println("⓶ List All Products");
        System.out.println("⓷ List All Materials");
        System.out.println("⓸ Calculate Environmental Impact");
        System.out.println("⓹ Recycling Guidance Menu");
        System.out.println("⓺ Show menu");
        System.out.println("⓻ ⏻ Exit Application");
        System.out.println("");
        System.out.println("✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧⋄⋆⋅⋆⋄✧");
    }

    public void controllCreateProduct() {
    List<String> productMaterials = new ArrayList<>();

    System.out.print("Give a product name: ");
    String productname = scanner.nextLine();

    if(productService.findByProductName(productname) != null){
        System.out.println("Product already exist!!");
        return;
    }

    System.out.print("Give a category name: ");
    String productcategory = scanner.nextLine();

    System.out.print("Give a lifespan value: ");
    int productlifespan = Integer.parseInt(scanner.nextLine());

    boolean moreMaterial = true;
    do {
        System.out.print("What material does the product have: ");
        String productmaterial = scanner.nextLine();
        if(materialService.findByMaterialName(productmaterial) != null){
            productMaterials.add(productmaterial);
        } else{
            System.out.println("Matrial not found.");
            System.out.print("Want to continue or create that Material? Y/N/C: ");
            String answer = scanner.nextLine().trim().toUpperCase();

            if(answer.contains("N")){
                return;
            }
            if(answer.contains("C")){
                productMaterials.add(creatingMatrialIfNeeded().getName().trim());
                }
            }
        while (true) {
            System.out.println("Does it have more materials? Y/N: ");
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
    System.out.println("successfully created Product '" + product.getName().trim() + "' created with " + product.getMaterials().size() + " material(s).");
    }

    
    public void controllProductImpact() {
    System.out.print("Enter product name: ");
    String name = scanner.nextLine();

    System.out.println("Choose a calculation strategy:");
    System.out.println("  1. Simple (sum of material impacts)");
    System.out.println("  2. Weighted (adjusted for product lifespan)");
    System.out.print("Your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    
    Product product = productService.getProductDetails(name);
    if (product == null) {
        System.out.println("Product not found.");
        return;
    }
    
    ImpactCalculationStrategy strategy = StrategyService.create(choice, product.getEstimatedLifespanYears());
    double impact = productService.calculateImpact(name, strategy);
    
    System.out.printf("Environmental impact for %s: %.2f%n", name, impact);
    }

    public void controllRecyclingMenu(){

    }
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

    private Material creatingMatrialIfNeeded() {
        String materialName;
        double impactValue;
        RecyclingCategory materialRecyclingCategory;
        
        System.out.print("Give material name: ");
        materialName = scanner.nextLine().trim();
        
        
        while (true) {
            System.out.print("Enter the impact value: ");
            
            try {
                impactValue = scanner.nextDouble();
                scanner.nextLine();
                break;
                
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
            }
        }
        while (true) {
        recyclingCategoryMenu();
        
        System.out.print("What recycling category is the material?: ");
        int answer = scanner.nextInt();
        scanner.nextLine();
        
        
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

    public void controllListMaterials(){
          List<Material> materials = materialService.listMaterials();
          if (materials.isEmpty()) {
              System.out.println("No material registered yet.");
          return;
          }
          for (Material m : materials) {
          System.out.printf("  %10s | ImpactValue: %.3f | RecyclingCategory: %s\n", m.getName(), m.getImpactValue(), m.getRecyclingCategory());
          }
      }
      
    public void controllListProducts(){
          List<Product> products = productService.listProducts();
          if (products.isEmpty()) {
              System.out.println("No products registered yet.");
          return;
          }
          for (Product p : products) {
          System.out.printf("  %-20s | Category: %-15s | Lifespan: %d yr(s) | Materials: %d%n", p.getName(), p.getCategory(), p.getEstimatedLifespanYears(), p.getMaterials().size());
          }
    }
    public void getProductDetails(Product product){
  
      }
    }



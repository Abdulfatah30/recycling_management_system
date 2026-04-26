package Presentation;
import java.util.Scanner;
import Application.MaterialService;
import Application.ProductService;
import Domain.Product;
import Domain.Strategy_1;
import Domain.Strategy_2;

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
    public void controllCreateProduct(){

    }
    public void controllListProducts(){

    }
    public void controllProductImpact(){

    }
    public void controllRecyclingMenu(){

    }
    public void controllListMaterials(){
        
    }
    public void getProductDetails(Product product){

    }
}

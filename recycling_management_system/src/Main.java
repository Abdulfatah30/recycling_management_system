import Application.MaterialService;
import Application.ProductService;
import Presentation.ConsoleUI;
import Domain.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        ProductService productService = new ProductService();
        MaterialService materialService = new MaterialService();
        ConsoleUI ui = new ConsoleUI(materialService,  productService);
        ui.start();
    }
}

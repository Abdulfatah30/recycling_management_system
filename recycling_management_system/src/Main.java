import Application.MaterialService;
import Application.ProductService;
import Presentation.ConsoleUI;
import Domain.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        MaterialService materialService = new MaterialService();
        ProductService productService = new ProductService(materialService);
        ConsoleUI ui = new ConsoleUI(materialService,  productService);
        ui.start();
    }
}

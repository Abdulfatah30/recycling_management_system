package Application;
import Domain.*;
import java.util.List;
import java.util.ArrayList;


public class ProductService {
   private List<Product> products = new ArrayList<>();
   private MaterialService materialservice = new MaterialService();

   public Product createProduct (String name, String category, int lifespan, List<String> materialNames){
      return null;
   }
   public List<Product> listProducts(){
      return null;
   }
   public Product getProductDetails(String name){

      return null;
   }
   public double calculateImpact (String productName, ImpactCalculationStrategy strategy){
      Product product = getProductDetails(productName);
      if (product == null){
         throw new IllegalArgumentException("Product not found: " + productName);
      }
      return product.calculateImpact(strategy);
   }
   public RecyclingGuidance getRecyclingGuidance (String productname){
      return null;
   }
}

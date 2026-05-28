package Application;
import Domain.*;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ProductService {
   private List<Product> products = new ArrayList<>();
   private MaterialService materialservice;
   private RecyclingGuidance guidance;
   private static final String PRODUCT_FILE = "out/saves/products.dat";

   public ProductService(MaterialService materialservice, RecyclingGuidance guidance) {
      this.materialservice = materialservice;
      this.guidance = guidance;
      loadProductsFromFile();
   }

   public Product createProduct (String name, String category, int lifespan, List<String> materialNames){
      loadProductsFromFile();
      List<Material> productMaterials = new ArrayList<>();

      for (String materialname : materialNames) {
         Material material = materialservice.findByMaterialName(materialname.trim());

         if(material == null){
            throw new IllegalArgumentException("material not found: " + materialname);

         }
         productMaterials.add(material);
      }

       for (Product existingProduct : products) {

        if (existingProduct.getName().equalsIgnoreCase(name)) {
            throw new IllegalArgumentException("Product already exists: " + name);
        }
    }

      Product product = new Product(name, category, lifespan, productMaterials);

    products.add(product);

    saveProductsToFile();

    return product;
   }

   public void saveProductsToFile() {

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE)))
    {out.writeObject(products);

    } catch (IOException e) {
      throw new RuntimeException("Failed to save products", e);
    }
   }

   private void loadProductsFromFile() {
    File file = new File(PRODUCT_FILE);
      file.getParentFile().mkdirs();

    products = new ArrayList<>();

    if (!file.exists()) return;

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
        products = (List<Product>) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException("Failed to load products", e);
    }
   }

   public List<Product> listProducts(){
      return products;
   }

   public Product getProductDetails(String name){
      loadProductsFromFile();
    for (Product product : products) {
        if (product.getName().equalsIgnoreCase(name)) {
            return product;
        }
    }

    return null;

   }

   public double calculateImpact (String productName, ImpactCalculationStrategy strategy){
      
      Product product = getProductDetails(productName);
      if (product == null){
         throw new IllegalArgumentException("Product not found: " + productName);
      }
      return product.calculateImpact(strategy);
   }

   public String getRecyclingGuidance (Product product){
      return product.getRecyclingGuidance(product, guidance);
   }

   public Product findByProductName(String name){
      loadProductsFromFile();
            for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }

    return null;
    }
}

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



/**
 * Application layer
 * 
 * Service class responsible for managing products in the system.
 * 
 * This class handles:
 * - product creation
 * - product searching
 * - environmental impact value
 * - loading and saving porduct data
 */
public class ProductService {
   private List<Product> products = new ArrayList<>();

   /**
    * Service responsible for managing product materials.
   */
   private MaterialService materialservice;

   private static final String PRODUCT_FILE = "out/saves/products.dat";



   /**'
    * Constructor
    * 
    * Creates a new ProductService object and loads saved products.
    * 
    * @param materialservice service used to access existing materials
    */
   public ProductService(MaterialService materialservice) {
      this.materialservice = materialservice;
      this.guidance = guidance;
      loadProductsFromFile();
   }


   /**
    * Creates and stores a new product in the system.
    * 
    * All material names must already exist in the material system adn product names must be unique.
    * Saves the product directly after creating it.
    * 
    * @param name unique name of the product
    * @param category category assigned to the product
    * @param lifespan estimated lifespan of the product in years
    * @param materialNames list of material names connected to the product
    * 
    * @return the created Product object
    * 
    * @throws IllegalArgumentException if a material does not exist or if a product with the same name already exists.
    */
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



   /**
    * Saves all products to a file using object serialization.
    * The contents of the file gts overwritten while saving.
    * 
    * @throws RuntimeException if the products cannot be saved.
    */
   public void saveProductsToFile() {

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))){
         out.writeObject(products);

      } catch (IOException e) {
         throw new RuntimeException("Failed to save products", e);
      }
   }


   /**
    * Loads products from the storage file.
    * 
    * If the file does not exist, an empty product list is used instead.
    * 
    * @throws RuntimeException if the product data cannot be loaded.
    */
   private void loadProductsFromFile() {
      File file = new File(PRODUCT_FILE);
      file.getParentFile().mkdirs();

      products = new ArrayList<>();

      if (!file.exists()) {
         return;
      }

      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
         products = (List<Product>) in.readObject();
      } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException("Failed to load products", e);
      }
   }
   

   /**
    * Returns all registered products from the list.
    * 
    * @return a list containing all stored products.
    */
   public List<Product> listProducts(){
      return products;
   }


   /**
    * Searches for a product by its name.
    * When searching it does not care about uppercase and lowercase differences.
    * 
    * @param name the name of the product searching for
    * 
    * @return the matching product object if found, otherwise returning null
    */
   public Product getProductDetails(String name){
      loadProductsFromFile();
    for (Product product : products) {
        if (product.getName().equalsIgnoreCase(name)) {
            return product;
        }
    }

    return null;

   }



   /**
    * Calculates the environmental impact of a product using the specified calculation strategy.
    * 
    * @param productName name of the product to calculate impact for
    * @param strategy the strategy used to calculate environmental impact
    * 
    * @return the calculated environmental impact value
    * 
    * @throws IllegalArgumentException if the product does not exist
    */
   public double calculateImpact (String productName, ImpactCalculationStrategy strategy){
      
      Product product = getProductDetails(productName);
      if (product == null){
         throw new IllegalArgumentException("Product not found: " + productName);
      }
      return product.calculateImpact(strategy);
   }

     

   /**
    * Returns recycling guidance information for a product.
    * 
    * @param productname name of the product
    * 
    * @return recycling guidance for the product
    */
   public String getRecyclingGuidance (Product product){
      return product.getRecyclingGuidance(product, guidance);
}

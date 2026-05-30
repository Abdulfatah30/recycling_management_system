package Domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Domain layer
 * 
 * Represents a product in the system.
 * 
 * A product contains:
 * - a product name
 * - a category
 * - an estimated lifespan
 * - a list of materials
 * 
 * This class is serializable so products can be saved to files.
*/
public class Product implements Serializable{
    private String name;
    private String category;
    private int lifespan;
    private List<Material> materials = new ArrayList<>();



    /**
     * Creates a product with its name, category, lifespan, and materials.
     *
     * @param name unique name of the product
     * @param category category assigned to the product
     * @param lifespan estimated lifespan of the product in years
     * @param materials list of materials included in the product
    */
    public Product(String name, String category, int lifespan, List<Material> materials){
        this.name = name;
        this.category = category;
        this.materials = materials;
        this.lifespan = lifespan;
    }



    /**
     * Returns the name of the product
     * 
     * @return the product name, as a String.
    */
    public String getName() {
        return name;
    }



    /**
     * Returns the category assigned to the product.
     * 
     * @return the product category as a String .
    */
    public String getCategory() {
        return category;
    }



    /**
     * Returns the estimated lifespan of the product.
     * 
     * @return the estimated lifespan of the product in years.
    */
    public int getEstimatedLifespanYears() {
        return lifespan;
    }



    /**
     * Returns all materials associated with the product.
     * 
     * @return the list of materials used in the product.
    */
    public List<Material> getMaterials() {
        return materials;
    }



    /**
     * Adds a material to the product.
     * 
     * @param material material object to be added to the product
    */
    public void addMaterial(Material material) {
        materials.add(material);
    }




    /**
     * Calculates the environmental impact of the product.
     * 
     * @param strategy strategy used to calculate the environmental impact.
     * 
     * @return the result of the calcuation. 
    */
    public double calculateImpact(ImpactCalculationStrategy strategy) {
        return strategy.calculateImpact(this.materials);
    }


    
    /**
     * Generates recycling guidance for the product.
     *
     * @param product the product for which guidance should be generated.
     * @param guidance guidance strategy used to generate recycling instructions.
     *
     * @return recycling guidance for the product, from the RecyclingGuidance class.
     */ 
    public String getRecyclingGuidance(Product product, RecyclingGuidance guidance) {
        return guidance.generateGuidance(product);
    }
}

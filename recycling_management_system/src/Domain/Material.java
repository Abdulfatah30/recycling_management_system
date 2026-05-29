package Domain;
import java.io.Serializable;


/**
 * Represents a material used in products.
 * 
 * A material contains:
 * - a name
 * - an environmental impact value
 * - a recycling category
 * 
 * This class is serializable so material objects can be saved to files.
*/
public class Material implements Serializable {
    private String name;
    private double impactValue;
    private RecyclingCategory recyclingCategory;


    /**
     * Constructor
     * 
     * Creates a new Material object.
     * 
     * @param name name of the material.
     * @param impactValue environmental impact value of the material.
     * @param category recycling category assigned to the material.
     */
    public Material(String name, double impactValue, RecyclingCategory category){
        this.name = name;
        this.impactValue = impactValue;
        this.recyclingCategory = category;
    }


    /**
     * Returns the name of the material.
     * 
     * @return material name
     */
    public String getName(){
        return name;
    }


    /**
     * Returns the environmental impact value of the material.
     * 
     * @return the impact value used in environmental calculations.
     */
    public double getImpactValue(){
        return impactValue;
    }

    /**
     * Returns the recycling category of the material.
     * 
     * @return the recycling category assigned to the material
     */
    public RecyclingCategory getRecyclingCategory(){
        return recyclingCategory;
    }
}

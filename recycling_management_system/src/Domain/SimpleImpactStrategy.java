package Domain;
import java.util.List;


/**
 * Domain
 * 
 * Calculates environmental impact by summing the impact values
 * of all materials in a product.
*/
public class SimpleImpactStrategy implements ImpactCalculationStrategy {


    /**
     * Calculates the total environmental impact of a collection of materials.
     * The calculation is done by adding together the impact value of each material.
     *
     * @param materials materials included in the calculation
     *
     * @return the sum of all material impact values
    */
    @Override
    public double calculateImpact(List<Material> materials){
        double total = 0;
        for (Material m : materials) {
           total += m.getImpactValue(); 
        }
        return total;
    }
}

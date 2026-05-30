package Domain;
import java.util.List;


/**
 * Domain Layer
 * 
 * Strategy interface used for calculating environmental impact values.
 * Different implementations can provide different calculation methods for the same product materials.
*/
public interface ImpactCalculationStrategy {

    /**
     * Calculates the environmental impact for a list of materials.
     * 
     * @param materials list of materials included in the calculation.
    */
    double calculateImpact(List<Material> materials);
}

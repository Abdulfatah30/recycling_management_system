package Domain;
import java.util.List;


/**
 * Domain
 * 
 * Calculates environmental impact using the product lifespan.
 * 
 * The total impact value of all materials is divided by the
 * product lifespan, giving a weighted impact value per year.
*/
public class WeightedImpactStrategy implements ImpactCalculationStrategy {
    private final int lifespan;


     /**
     * Creates a weighted impact calculation strategy.
     * 
     * @param lifespan estimated lifespan of the product in years
     */
    public WeightedImpactStrategy(int lifespan){
        this.lifespan = lifespan;
    }



    /**
     * Calculates the weighted environmental impact from a list of meterials.
     * The calculation first sums all material impact values and then divides the result by the product lifespan.
     *
     * @param materials materials included in the calculation
     *
     * @return the weighted environmental impact value
    */
    @Override
    public double calculateImpact(List<Material> Materials){
        double total = 0.0;
        for (Material m : Materials) {
            total += m.getImpactValue();
        }

        /**
         * Use a minimum value of 1 to avoid division by zero
        */
        return total/Math.max(1, lifespan);
    }
}

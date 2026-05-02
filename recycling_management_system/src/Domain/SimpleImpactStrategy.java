package Domain;
import java.util.List;

public class SimpleImpactStrategy implements ImpactCalculationStrategy {

    @Override
    public double calculateImpact(List<Material> materials){
        double total = 0;
        for (Material m : materials) {
           total += m.getImpactValue(); 
        }
        return total;
    }
}

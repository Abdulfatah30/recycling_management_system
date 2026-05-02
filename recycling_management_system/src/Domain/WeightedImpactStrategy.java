package Domain;
import java.util.List;

public class WeightedImpactStrategy implements ImpactCalculationStrategy {
    private final int lifespan;

    public WeightedImpactStrategy(int lifespan){
        this.lifespan = lifespan;
    }

    @Override
    public double calculateImpact(List<Material> Materials){
        double total = 0.0;
        for (Material m : Materials) {
            total += m.getImpactValue();
        }
        return total/Math.max(1, lifespan);
    }
}

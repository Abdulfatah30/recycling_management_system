package Domain;
import java.util.List;

public interface ImpactCalculationStrategy {
    double calculateImpact(List<Material> materials);
}

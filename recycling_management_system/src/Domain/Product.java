package Domain;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String category;
    private int lifespan;
    private List<Material> materials = new ArrayList<>();

public String getName() {
    return "";
}

public String getCategory() {
    return "";
}

public int getEstimatedLifespanYears() {
    return 0;
}

public List<Material> getMaterials() {
    return new ArrayList<>();
}

public void addMaterial(Material material) {
    
}

public double calculateImpact(ImpactCalculationStrategy strategy) {
    return 0.0;
}

public RecyclingGuidance getRecyclingGuidance() {
    return null;
}
}

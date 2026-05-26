package Domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable{
    private String name;
    private String category;
    private int lifespan;
    private List<Material> materials = new ArrayList<>();
public Product(String name, String category, int lifespan, List<Material> materials){
    this.name = name;
    this.category = category;
    this.materials = materials;
    this.lifespan = lifespan;
}
public String getName() {
    return name;
}

public String getCategory() {
    return category;
}

public int getEstimatedLifespanYears() {
    return lifespan;
}

public List<Material> getMaterials() {
    return materials;
}

public void addMaterial(Material material) {
    materials.add(material);
}

public double calculateImpact(ImpactCalculationStrategy strategy) {
    return strategy.calculateImpact(this.materials);
}

public RecyclingGuidance getRecyclingGuidance() {
    return null;
}
}

package Application;
import java.util.ArrayList;
import java.util.List;
import Domain.Material;
import Domain.RecyclingCategory;

public class MaterialService {
    private List<Material> materials = new ArrayList <>();

    public List<Material> listMaterials() {
        return materials;
    }

    public Material findByMaterialName(String name){
            for (Material material : materials) {
            if (material.getName().equalsIgnoreCase(name)) {
                return material;
            }
        }

    return null;
    }
    public Material createMaterial(String name, double impactValue, RecyclingCategory category){
        return null;
    }
}

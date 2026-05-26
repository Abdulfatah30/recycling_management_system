package Application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import Domain.Material;
import Domain.RecyclingCategory;

public class MaterialService {

    public MaterialService() {

    loadMaterialsFromFile();

    }

    private List<Material> materials = new ArrayList <>();
    private static final String FILE_NAME = "out/saves/materials.dat";

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

    public Material createMaterial(String name, double impactValue, RecyclingCategory category) {

    for (Material m : materials) {
        if (m.getName().equalsIgnoreCase(name)) {
            throw new IllegalArgumentException("Material already exists: " + name);
        }
    }

    Material material = new Material(name, impactValue, category);

    materials.add(material);

    saveMaterialsToFile();

    return material;
    }

    public void saveMaterialsToFile() {

    try (ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

        out.writeObject(materials);

    } catch (IOException e) {
        throw new RuntimeException("Failed to save Mateials", e);
    }
    }

    public void loadMaterialsFromFile() {
        File file = new File(FILE_NAME);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in =new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            materials = (List<Material>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load materials", e);
        }
    }
}

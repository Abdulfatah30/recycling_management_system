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
import Domain.Product;
import Domain.RecyclingCategory;



/** 
 * Application layer
 * 
 * Service class responsible for managing materials in the system.
 *
 * This class handles: 
 * - material creation 
 * - material searching 
 * - material storage 
 * - loading and saving materials from files
*/
public class MaterialService {

    /**
     * File path used for storing serialized material data.
    */
    private static final String FILE_NAME = "out/saves/materials.dat";

    private List<Material> materials = new ArrayList <>();
   

    /**
     * Constructor.
     * 
     * Creates a new MaterialService object and loads saved materials
     * from the storage file.
    */
    public MaterialService() {
        loadMaterialsFromFile();
    }



    /**
     * Returns all registered materials in the system.
     * @return a list containing all stored materials
     */
     public List<Material> listMaterials() {
        return materials;
    }



    /**
     * Searches for a material by its name.
     * The search ignores uppercase and lowercase differences.
     * 
     * @param name the name of the material to search for.
     * @return the matching material object if found otherwise returning null
     */
    public Material findByMaterialName(String name){
            for (Material material : materials) {
            if (material.getName().equalsIgnoreCase(name)) {
                return material;
            }
        }
    return null;
    }



    /**
     * Creates and stores a new material to the list.
     * Material names must be unique.
     * The material list is automatically saved after creation.
     * 
     * @param name the name of the material.
     * @param impactValue the environmental impact value of the material.
     * @param category the recycling category of the material.
     * 
     * @return returns the created material
     * 
     * @throws IllegalArgumentException if a material with the same name already exists will throw an exception
     */
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



    /**
     * Saves all materials to a file using object serialization.
     * The file is overwritten each time the method is called.
     * 
     * @throws RuntimeException if the materials cannot be saved
     */
    public void saveMaterialsToFile() {

        try (ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(materials);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save Mateials", e);
        }
    }



    /**
     * Loads saved materials from the storage file.
     * If the file does not exist, the method returns without loading data.
     * Missing directories are automatically created if needed.
     * 
     * @throws RuntimeException if the material data cannot be loaded
     */
    public void loadMaterialsFromFile() {
        File file = new File(FILE_NAME);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in =new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object raw = in.readObject();
            if (raw instanceof List<?> list) {
                for (Object item : list) {
                    if (!(item instanceof Material)) {
                        throw new RuntimeException("Unexpected type in products file");
                    }
                }
                @SuppressWarnings("unchecked")
                List<Material> loaded = (List<Material>) raw;
                materials = loaded;
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load materials", e);
        }
    }
}

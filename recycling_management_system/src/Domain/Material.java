package Domain;
import java.io.Serializable;

public class Material implements Serializable {
    private static final long serialVersionUID = 1L; // fixed ID so save files stay valid after code changes
    private String name;
    private double impactValue;
    private RecyclingCategory recyclingCategory;

public Material(String name, double impactValue, RecyclingCategory category){
    this.name = name;
    this.impactValue = impactValue;
    this.recyclingCategory = category;

}
public String getName(){
    return name;
}
public double getImpactValue(){
    return impactValue;
}
public RecyclingCategory getRecyclingCategory(){
    return recyclingCategory;
}
}

package Domain;

public class Material {
    private String name;
    private double impactValue;
    private RecyclingCategory recyclingCategory;
    private String recyclingInstruction;

public Material(String name, double impactValue, RecyclingCategory category, String instructions){
    this.name = name;
    this.impactValue = impactValue;
    this.recyclingCategory = category;
    this.recyclingInstruction = instructions;
}
public String getName(){
    return "";
}
public double getImpactValue(){
    return impactValue;
}
public RecyclingCategory getRecyclingCategory(){
    return RecyclingCategory.NON_RECYCLABLE;
}
public String getRecyclingInstruction(){
    return "";
}
}

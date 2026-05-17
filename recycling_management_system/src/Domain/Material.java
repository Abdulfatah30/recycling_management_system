package Domain;
public class Material {
    private String name;
    private double impactValue;
    private RecyclingCategory recyclingCategory;
    private String instruction;

public String getName(){
    return " ";
}
public double getImpactValue(){
    return 0.00;
}
public RecyclingCategory getRecyclingCategory(){
    return RecyclingCategory.NON_RECYCLABLE;
}
public String getRecyclingInstruction(){
    return "";
}
}

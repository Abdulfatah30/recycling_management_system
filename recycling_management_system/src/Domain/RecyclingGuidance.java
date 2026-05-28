package Domain;
import java.util.List;
public class RecyclingGuidance {
    
    public String generateGuidance(Product product) {
       List<Material> materials = product.getMaterials();
       if (materials.isEmpty()){
        return "No materials! Recycling guidance can not be provided.";
       }
       if (materials.size() == 1){
        Material material = materials.get(0);
        return guidanceFor(material.getRecyclingCategory());
       }

       Material dominantMaterial = materials.get(0);
       boolean equalImpact = true;

       for(Material material : materials){
        if ( material.getImpactValue() > dominantMaterial.getImpactValue()){
        dominantMaterial = material;
        }
        if (material.getImpactValue() != materials.get(0).getImpactValue()){
            equalImpact = false;
        }
       }
       if (equalImpact){
        return "Mixed-material product. Take to general waste!";
        
       }

       return guidanceFor(dominantMaterial.getRecyclingCategory());
    }

    private String guidanceFor(RecyclingCategory category) {
        switch (category) {
            case PLASTIC:        return "Recycle in plastic bin.";
            case METAL:          return "Take to metal recycling.";
            case GLASS:          return "Place in glass recycling.";
            case PAPER:          return "Place in paper recycling.";
            case ORGANIC:        return "Compost organics.";
            case ELECTRONIC:     return "Take to e-waste facility.";
            case HAZARDOUS:      return "Take to hazardous waste facility.";
            case WOOD:           return "Take to wood recycling.";
            case NON_RECYCLABLE: return "Dispose in general waste.";
            default:             return "Dispose responsibly.";
        }
    }
}



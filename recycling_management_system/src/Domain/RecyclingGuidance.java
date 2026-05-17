package Domain;
import java.util.List;
public class RecyclingGuidance {
    
    public String generateGuidance(Product product) {
        List<Material> materials = product.getMaterials();

        if (materials.isEmpty()) {
            return "No materials — no recycling guidance available.";
        }

        StringBuilder guidance = new StringBuilder();
        for (Material m : materials) {
            guidance.append(guidanceFor(m.getRecyclingCategory()));
            guidance.append(" ");
        }
        return guidance.toString().trim();
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



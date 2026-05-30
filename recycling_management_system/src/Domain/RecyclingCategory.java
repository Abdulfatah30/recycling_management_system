package Domain;



/**
 * Domain layer
 * 
 * Represents different recycling categories in the system.
 * 
 * A recycling category is used to:
 * - classify materials
 * - support recycling guidance generation
 * - organize materials in the system
*/
public enum RecyclingCategory {
    PLASTIC,
    METAL,
    GLASS,
    PAPER,
    ORGANIC,
    ELECTRONIC,
    HAZARDOUS,
    NON_RECYCLABLE,
    WOOD
}

package Test;
import Domain.*;
import org.junit.jupiter.api.Test;

import Application.ProductService;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class test {

    // Tests on Strategies 
    @Test
    void shouldCalculateTotalImpactForSimpleStrategy() {

        // Arrange
        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC);
        Material metal = new Material("metal", 4.5, RecyclingCategory.METAL);

        List<Material> materials = List.of(plastic, metal);

        SimpleImpactStrategy strategy = new SimpleImpactStrategy();

        // Act
        double result = strategy.calculateImpact(materials);

        // Assert
        assertEquals(7.0, result);
    }
    @Test
    void shouldReturnZeroForEmptyListForSimpleStrategy() {

        SimpleImpactStrategy strategy = new SimpleImpactStrategy();

        double result = strategy.calculateImpact(List.of());

        assertEquals(0, result);
    }
    @Test
    void shouldHandleSingleMaterialSimpleStrategy() {

        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC);

        SimpleImpactStrategy strategy = new SimpleImpactStrategy();

        double result = strategy.calculateImpact(List.of(plastic));

        assertEquals(2.5 , result);
    }

    @Test
    void shouldCalculateImpactCorrectlyForWeightedStrategy() {

        // Arrange
        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC);
        Material metal = new Material("metal", 4.5, RecyclingCategory.METAL);

        List<Material> materials = List.of(plastic, metal);

        WeightedImpactStrategy strategy = new WeightedImpactStrategy(2);

        // Act
        double result = strategy.calculateImpact(materials);

        // (2.5 + 4.5) = 7.0 → 7.0 / 2 = 3.5

        // Assert
        assertEquals(3.5, result);
    }

    @Test
    void shouldReturnZeroForEmptyListForWeightedStrategy() {

        WeightedImpactStrategy strategy = new WeightedImpactStrategy(5);

        double result = strategy.calculateImpact(List.of());

        assertEquals(0.0, result);
    }

    @Test
    void shouldHandleSingleMaterialForWeightedStrategy() {

        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC);

        WeightedImpactStrategy strategy = new WeightedImpactStrategy(4);

        double result = strategy.calculateImpact(List.of(plastic));

        assertEquals(2.5 / 4, result);
    }

    @Test
    void shouldAvoidDivisionByZeroWhenLifespanIsZeroInWeightedStrategy() {

        Material metal = new Material("metal", 4.0, RecyclingCategory.METAL);

        WeightedImpactStrategy strategy = new WeightedImpactStrategy(0);

        double result = strategy.calculateImpact(List.of(metal));

        assertEquals(4.0, result);
    }

    // Test on product class
    @Test
    void shouldReturnNamePassedToProductConstructor() {
        Product product = new Product("Laptop", "Electronics", 5, new ArrayList<>());

        String result = product.getName();

        assertEquals("Laptop", result);
    }

    @Test
    void shouldReturnCategoryPassedToProductConstructor() {
        Product product = new Product("Laptop", "Electronics", 5, new ArrayList<>());

        String result = product.getCategory();

        assertEquals("Electronics", result);
    }

    @Test
    void shouldReturnLifespanPassedToProductConstructor() {
        Product product = new Product("Laptop", "Electronics", 5, new ArrayList<>());

        int result = product.getEstimatedLifespanYears();

        assertEquals(5, result);
    }

    // test on Matrial classes 
    @Test
    void shouldReturnMaterialsPassedToProductConstructor() {
        Material metal = new Material("Steel Frame", 4.0, RecyclingCategory.METAL);
        List<Material> materials = new ArrayList<>(List.of(metal));
        Product product = new Product("Bicycle", "Transport", 10, materials);

        List<Material> result = product.getMaterials();

        assertEquals(1, result.size());
        assertEquals("Steel Frame", result.get(0).getName());
    }

    //RecyclingGuidance class tests
    @Test
    void shouldReturnPlasticGuidanceForSinglePlasticMaterial() {
        Material plastic = new Material("PET Bottle", 2.0, RecyclingCategory.PLASTIC);
        Product product = new Product("Bottle", "Container", 1, new ArrayList<>(List.of(plastic)));
        RecyclingGuidance guidance = new RecyclingGuidance();

        String result = guidance.generateGuidance(product);

        assertTrue(result.contains("plastic bin"));
    }
    @Test
    void shouldReturnMetalGuidanceForSingleMetalMaterial() {
        Material metal = new Material("Steel", 4.0, RecyclingCategory.METAL);
        Product product = new Product("Can", "Container", 2, new ArrayList<>(List.of(metal)));
        RecyclingGuidance guidance = new RecyclingGuidance();

        String result = guidance.generateGuidance(product);

        assertTrue(result.contains("metal recycling"));
    }

    @Test
    void shouldReturnGeneralWasteGuidanceForNonRecyclableMaterial() {
        Material foam = new Material("Foam", 3.0, RecyclingCategory.NON_RECYCLABLE);
        Product product = new Product("Cushion", "Furniture", 5, new ArrayList<>(List.of(foam)));
        RecyclingGuidance guidance = new RecyclingGuidance();

        String result = guidance.generateGuidance(product);

        assertTrue(result.contains("general waste"));
    }
    @Test
    void shouldReturnFallbackMessageWhenProductHasNoMaterials() {
    Product product = new Product("Empty Product", "Test", 1, new ArrayList<>());
    RecyclingGuidance guidance = new RecyclingGuidance();

    String result = guidance.generateGuidance(product);

    assertTrue(result.contains("No materials"));
    }

}
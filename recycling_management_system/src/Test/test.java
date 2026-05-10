package Test;
import Domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class test {

    @Test
    void shouldCalculateTotalImpactForSimpleStrategy() {

        // Arrange
        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC, "Nothing");
        Material metal = new Material("metal", 4.5, RecyclingCategory.METAL, "Nothing");

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

        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC, "Nothing");

        SimpleImpactStrategy strategy = new SimpleImpactStrategy();

        double result = strategy.calculateImpact(List.of(plastic));

        assertEquals(2.5 , result);
    }




    @Test
    void shouldCalculateImpactCorrectlyForWeightedStrategy() {

        // Arrange
        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC, "Nothing");
        Material metal = new Material("metal", 4.5, RecyclingCategory.METAL, "Nothing");

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

        Material plastic = new Material("plastic", 2.5, RecyclingCategory.PLASTIC, "Nothing");

        WeightedImpactStrategy strategy = new WeightedImpactStrategy(4);

        double result = strategy.calculateImpact(List.of(plastic));

        assertEquals(2.5 / 4, result);
    }

    @Test
    void shouldAvoidDivisionByZeroWhenLifespanIsZeroInWeightedStrategy() {

        Material metal = new Material("metal", 4.0, RecyclingCategory.METAL, "Nothing");

        WeightedImpactStrategy strategy = new WeightedImpactStrategy(0);

        double result = strategy.calculateImpact(List.of(metal));

        assertEquals(4.0, result);
    }
}
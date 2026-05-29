package Application;

import Domain.ImpactCalculationStrategy;
import Domain.SimpleImpactStrategy;
import Domain.WeightedImpactStrategy;


/**
 * Application layer
 * 
 * Service class responsible for creating environmental impact calculation strategies.
 * Different strategies are created depending on what the user selected from the menu options.
 */
public class StrategyService {

    /**
     * Creates an impact calculation strategy based on the selected option.
     * 
     * Case 1 creates a simple impact strategy.
     * Case 2 creates a weighted impact strategy that uses the product lifespan in the calculation.
     * 
     * @param choice menu option selected by the user.
     * @param lifespanYearsestimated lifespan of the product in years.
     * 
     * @return object matching the selected option.
     * 
     * @throws IllegalArgumentException if the selected option is unknown.
     */
    public static ImpactCalculationStrategy create(int choice, int lifespanYears) {
        switch (choice) {
            case 1: return new SimpleImpactStrategy();
            case 2: return new WeightedImpactStrategy(lifespanYears);
            default: throw new IllegalArgumentException("Unknown strategy choice: " + choice);
        }
    }
}
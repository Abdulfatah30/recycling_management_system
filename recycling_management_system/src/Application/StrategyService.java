package Application;

import Domain.ImpactCalculationStrategy;
import Domain.SimpleImpactStrategy;
import Domain.WeightedImpactStrategy;

public class StrategyService {
    public static ImpactCalculationStrategy create(int choice, int lifespanYears) {
        switch (choice) {
            case 1: return new SimpleImpactStrategy();
            case 2: return new WeightedImpactStrategy(lifespanYears);
            default: throw new IllegalArgumentException("Unknown strategy choice: " + choice);
        }
    }
}
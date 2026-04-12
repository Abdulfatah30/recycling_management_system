package Application;

import Domain.ImpactCalculationStrategy;
import Domain.Product;

public class ProductApplicationService {
 private ImpactCalculationStrategy strategy;

 ProductApplicationService(ImpactCalculationStrategy strategy){
    this.strategy = strategy; 
 }
 public double caluclateimpact(Product product){
    return 0.00;
 }
 public void createProduct(){

 }
 public void listProduct(){

 }
}

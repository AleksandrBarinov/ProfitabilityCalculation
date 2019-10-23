package service;

import bean.Product;

public interface DemandService {
    void searchProduct(Product product);
    void checkBalance(Product product);
    void demandProduct(Product product);
}

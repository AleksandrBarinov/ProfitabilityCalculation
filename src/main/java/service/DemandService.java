package service;

import bean.Product;

public interface DemandService {
    Product searchProduct(String name);
    Integer checkBalance(String name);
    boolean demandProduct(String reqBody);
    void updateBalance(String name, int quantity);
}

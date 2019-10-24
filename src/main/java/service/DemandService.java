package service;

import bean.Product;

public interface DemandService {
    Product searchProduct(String name);
    int checkBalance(String name);
    boolean demandProduct(String reqBody);
    void updateBalance(String name);
}

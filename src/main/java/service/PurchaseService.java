package service;

import bean.Product;

public interface PurchaseService {
    Product searchProduct(String name);
    boolean purchaseProduct(String reqBody);
    void updateBalance(String name);
}

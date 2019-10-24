package service;

import bean.Product;

public interface PurchaseService {
    Product searchProduct(String name);
    void purchaseProduct(String reqBody);
}

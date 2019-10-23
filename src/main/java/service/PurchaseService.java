package service;

import bean.Product;

public interface PurchaseService {
    void searchProduct(Product product);
    void purchaseProduct(Product product);
}

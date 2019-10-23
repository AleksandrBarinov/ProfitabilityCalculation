package service;

import bean.Product;
import bean.Purchase;

import java.text.ParseException;

public interface PurchaseService {
    Product searchProduct(String name);
    void purchaseProduct(Purchase purchase) throws ParseException;
}

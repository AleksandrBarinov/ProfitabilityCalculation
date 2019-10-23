package dao;

import bean.Product;

import java.util.Date;

public interface ProductDAO {
    void addNewProduct(Product product);
    Product searchProductByName(String name);
    void purchaseProduct(Product product, int qty, double price, Date date);
    int checkBalance(String name);
    void demandProduct(Product product, int qty, double price, Date date);
    String generateReport (Product product, String date);
}

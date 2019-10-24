package dao;

import bean.Product;

import java.util.Date;

public interface ProductDAO {
    void addNewProduct(Product product);
    Product searchProductByName(String name);
    Integer checkBalance(String name);
    void updateBalance(String name, int quantity);
    void purchaseProduct(Product product, int qty, double price, Date date);
    void demandProduct(Product product, int qty, double price, Date date);
    String generateReport (String name, String date);
}

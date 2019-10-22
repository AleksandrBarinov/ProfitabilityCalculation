package dao;

import bean.Product;

import java.util.Date;

public interface ProductDAO {
    void addNewProduct(Product product);
    Product searchProductByName(String name);
    void purchaseProduct(Product product, int qty, double price, Date date);//purchase
    void sellProduct(Product product, int qty, double price, Date date);//demand
    String generateReport (Product product, Date date);
}

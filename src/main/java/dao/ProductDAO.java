package dao;

import bean.Product;
import dao.hibernate.models.DemandEntity;
import dao.hibernate.models.PurchaseEntity;

import java.util.Date;
import java.util.List;

public interface ProductDAO {
    void addNewProduct(Product product);
    Product searchProductByName(String name);
    Integer checkBalance(String name);
    void updateBalance(String name, int quantity);
    void purchaseProduct(Product product, int qty, double price, Date date);
    void demandProduct(Product product, int qty, double price, Date date);
    List<PurchaseEntity> getPurchases(String name, Date date);
    List<DemandEntity> demands(String name, Date date);
}

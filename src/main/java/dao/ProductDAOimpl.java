package dao;

import bean.Product;
import dao.hibernate.models.Demand;
import dao.hibernate.models.ProductEntity;
import dao.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class ProductDAOimpl implements ProductDAO {

    private static final ProductDAOimpl instance = new ProductDAOimpl();

    public static ProductDAOimpl getInstance() {
        return instance;
    }

    public void addNewProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());

        session.save(productEntity);
        session.getTransaction().commit();
        session.close();
    }

    public Product searchProductByName(String name) {
        return null;
    }

    public void purchaseProduct(Product product, int qty, double price, Date date) {

    }

    public int checkBalance(String name) {

        return 0;
    }

    public void demandProduct(Product product, int qty, double price, Date date) {

    }

    public String generateReport(Product product, String date) {
        return null;
    }
}

package dao;

import bean.Product;
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
        productEntity.setDescription("desc");
        productEntity.setName("name");
        productEntity.setPrice(500);

        session.save(productEntity);
        session.getTransaction().commit();
        HibernateUtil.closeSession();
    }

    public Product searchProductByName(String name) {
        return null;
    }

    public void purchaseProduct(Product product, int qty, double price, Date date) {

    }

    public void sellProduct(Product product, int qty, double price, Date date) {

    }

    public String generateReport(Product product, Date date) {
        return null;
    }
}

package dao;

import bean.Product;
import dao.hibernate.models.DemandEntity;
import dao.hibernate.models.ProductEntity;
import dao.hibernate.models.PurchaseEntity;
import dao.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Date;

public class ProductDAOimpl implements ProductDAO {

    private static final ProductDAOimpl instance = new ProductDAOimpl();

    public static ProductDAOimpl getInstance() {
        return instance;
    }

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static Session session;

    public void addNewProduct(Product product) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());

        session.save(entity);
        session.getTransaction().commit();

        session.close();
    }

    public Product searchProductByName(String name) {

        ProductEntity entity = searchProductEntityByName(name);

        if (entity != null) {
            return new Product(
                    entity.getName(),
                    entity.getDescription()
            );
        } else return null;
    }

    private ProductEntity searchProductEntityByName(String name) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        ProductEntity entity;
        try {
            Query query = session.createQuery("FROM ProductEntity p where p.name = :name");
            query.setParameter("name", name);
            entity = (ProductEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        session.close();
        return entity;
    }

    public void purchaseProduct(Product product, int qty, double price, Date date) {
        ProductEntity productEntity = searchProductEntityByName(product.getName());

        session = sessionFactory.openSession();
        session.beginTransaction();

        PurchaseEntity entity = new PurchaseEntity();
        entity.setProduct(productEntity);
        entity.setQuantity(qty);
        entity.setPrice(price);
        entity.setDate(date);

        session.save(entity);
        session.getTransaction().commit();

        session.close();
    }

    public int checkBalance(String name) {
        return 0;
    }

    public void demandProduct(Product product, int qty, double price, Date date) {
    }

    public String generateReport(String name, String date) {
        return null;
    }
}

package dao;

import bean.Product;
import dao.hibernate.models.DemandEntity;
import dao.hibernate.models.ProductBalance;
import dao.hibernate.models.ProductEntity;
import dao.hibernate.models.PurchaseEntity;
import dao.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public Integer checkBalance(String name) {
        Integer balance;

        session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("FROM ProductBalance p where p.product.name = :name");
            query.setParameter("name", name);
            ProductBalance productBalance = (ProductBalance) query.getSingleResult();
            balance = productBalance.getQuantity();
        } catch (NoResultException e) {
            return null;
        }
        session.getTransaction().commit();

        session.close();
        return balance;
    }

    public void updateBalance(String name, int quantity) {
        ProductEntity entity = searchProductEntityByName(name);
        session = sessionFactory.openSession();
        session.beginTransaction();
        ProductBalance productBalance;
        try {
            Query query = session.createQuery("FROM ProductBalance p where p.product.name = :name");
            query.setParameter("name", name);
            productBalance = (ProductBalance) query.getSingleResult();

            productBalance.setQuantity(
                    productBalance.getQuantity() + quantity
            );
            session.save(productBalance);
            session.getTransaction().commit();

        } catch (NoResultException e){
            productBalance = new ProductBalance();
            productBalance.setProduct(entity);
            productBalance.setQuantity(quantity);
            session.save(productBalance);
            session.getTransaction().commit();
        }
        session.close();
    }

    public void demandProduct(Product product, int qty, double price, Date date) {
        ProductEntity productEntity = searchProductEntityByName(product.getName());

        session = sessionFactory.openSession();
        session.beginTransaction();

        DemandEntity entity = new DemandEntity();
        entity.setProduct(productEntity);
        entity.setQuantity(qty);
        entity.setPrice(price);
        entity.setDate(date);

        session.save(entity);
        session.getTransaction().commit();

        session.close();
    }

    public String generateReport(String name, String date) {
        Date desiredDate = null;
        try {
            desiredDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException ignored){}

        double purchaseSum = 0;
        double demandSum = 0;
        double profitability;

        session = sessionFactory.openSession();
        session.beginTransaction();

        List<PurchaseEntity> purchases = session.createQuery("from PurchaseEntity").getResultList();
        List<DemandEntity> demands = session.createQuery("from DemandEntity").getResultList();

        for (PurchaseEntity purchase: purchases){
            if (purchase.getDate().compareTo(desiredDate) == 0){
                purchaseSum = purchaseSum + purchase.getPrice();
            }
        }
        for (DemandEntity demand: demands){
            if (demand.getDate().compareTo(desiredDate) == 0){
                demandSum = demandSum + demand.getPrice();
            }
        }
        profitability = demandSum - purchaseSum;

        session.getTransaction().commit();
        session.close();
        return "profitability of "+name+" is "+profitability+"";
    }
}

import models.Product;
import models.Purchase;
import org.hibernate.Session;
import util.HibernateUtil;

public class Test {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product = new Product();
        product.setDescription("desc");
        product.setName("name");
        product.setPrice(500);

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setQty(2);

        session.save(product);
        session.save(purchase);

        session.getTransaction().commit();

        HibernateUtil.closeSession();
    }
}

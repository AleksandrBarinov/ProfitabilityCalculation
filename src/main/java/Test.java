import dao.hibernate.models.ProductEntity;
import dao.hibernate.models.Purchase;
import org.hibernate.Session;
import dao.hibernate.util.HibernateUtil;

public class Test {
    public static void main(String[] args) {

//        Session session = HibernateUtil.getSessionFactory().openSession();
////        session.beginTransaction();
////
////        ProductEntity productEntity = new ProductEntity();
////        productEntity.setDescription("desc");
////        productEntity.setName("name");
////        productEntity.setPrice(500);
////
////        Purchase purchase = new Purchase();
////        purchase.setProduct(productEntity);
////        purchase.setQty(2);
////
////        session.save(productEntity);
////        session.save(purchase);
////
////        session.getTransaction().commit();
////
////        HibernateUtil.closeSession();
    }
}

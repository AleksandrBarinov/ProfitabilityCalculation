import bean.Product;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import dao.hibernate.models.PurchaseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.NewProductService;
import service.impl.NewProductServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Tests {

    @Test
    public void searchProduct() {
        ProductDAO productDAOimpl = new ProductDAOimpl();
        Product product = productDAOimpl.searchProductByName("product");
        System.out.println(product.getName() + " was found");

        Assert.assertEquals(product.getName(), "product");
    }

    @Test
    public void productExists() {
        NewProductService newProductService = new NewProductServiceImpl();
        boolean result = newProductService.productExists("product");
        System.out.println("result is " + result);

        Assert.assertTrue(result);
    }

    @Test
    public void updateBalance() {
        ProductDAO productDAOimpl = new ProductDAOimpl();
        int was = productDAOimpl.checkBalance("product");
        productDAOimpl.updateBalance("product", 1);
        int now = productDAOimpl.checkBalance("product");

        Assert.assertEquals((now - was), 1);
    }

    @Test
    public void getPurchases() {
        Date desiredDate = null;
        try {
            desiredDate = new SimpleDateFormat("dd.MM.yyyy").parse("21.02.2019");
        } catch (ParseException ignored) {
        }

        ProductDAO productDAOimpl = new ProductDAOimpl();
        List<PurchaseEntity> purchaseEntities = productDAOimpl.getPurchases("product2", desiredDate);

        for (PurchaseEntity purchaseEntity : purchaseEntities) {
            Assert.assertTrue(purchaseEntity.getDate().equals(desiredDate));

        }
    }
}
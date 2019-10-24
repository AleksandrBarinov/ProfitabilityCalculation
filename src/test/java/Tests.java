import bean.Product;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.NewProductService;
import service.impl.NewProductServiceImpl;

public class Tests {

    @Test
    public void searchProduct(){
        ProductDAO productDAOimpl = new ProductDAOimpl();
        Product product = productDAOimpl.searchProductByName("product");
        System.out.println(product.getName() + " was found");

        Assert.assertEquals(product.getName(),"product");
    }

    @Test
    public void productExists(){
        NewProductService newProductService = new NewProductServiceImpl();
        boolean result = newProductService.productExists("product");
        System.out.println("result is " + result);

        Assert.assertTrue(result);
    }

    @Test
    public void updateBalance(){
        ProductDAO productDAOimpl = new ProductDAOimpl();
        int was = productDAOimpl.checkBalance("product");
        productDAOimpl.updateBalance("product",1);
        int now = productDAOimpl.checkBalance("product");

        Assert.assertEquals((now - was), 1);
    }

    @Test
    public void generateReport(){
        ProductDAO productDAOimpl = new ProductDAOimpl();
        System.out.println(productDAOimpl.generateReport("product","22.02.2019"));
    }
}

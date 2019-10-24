import bean.Product;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import org.testng.annotations.Test;
import service.NewProductService;
import service.impl.NewProductServiceImpl;

public class Tests {

    @Test
    public void searchProduct(){
        ProductDAO productDAOimpl = new ProductDAOimpl();
        Product product = productDAOimpl.searchProductByName("product");
        System.out.println(product.getName() + " was found");
    }

    @Test
    public void productExists(){
        NewProductService newProductService = new NewProductServiceImpl();
        boolean result = newProductService.productExists("prod32uct");
        System.out.println("result is " + result);
    }

    @Test
    public void updateBalance(){
        ProductDAO productDAOimpl = new ProductDAOimpl();
        productDAOimpl.updateBalance("product",100);
    }
}

import bean.Product;
import service.NewProductService;
import service.impl.NewProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newproduct")
public class NewProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        NewProductService newProductService = NewProductServiceImpl.getInstance();
        newProductService.addNewPruduct(
                new Product("name from request", "desc from request", 500)
                //from request
        );
    }
}

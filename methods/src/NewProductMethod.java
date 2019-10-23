import bean.Product;
import com.google.gson.JsonObject;
import service.NewProductService;
import service.impl.NewProductServiceImpl;
import util.GsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/newproduct")
public class NewProductMethod extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringReqBody = req.getReader().lines().collect(Collectors.joining());

        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                stringReqBody
        );

        NewProductService newProductService = NewProductServiceImpl.getInstance();
        newProductService.addNewProduct(
                new Product(
                        jsonObject.get("name").toString(),
                        jsonObject.get("description").toString()
                )
        );
    }
}

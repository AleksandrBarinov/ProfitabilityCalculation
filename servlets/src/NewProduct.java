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
public class NewProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringRespBody = req.getReader().lines().collect(Collectors.joining());

        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                stringRespBody
        );

        NewProductService newProductService = NewProductServiceImpl.getInstance();
        newProductService.addNewPruduct(
                new Product(
                        jsonObject.get("name").toString(),
                        jsonObject.get("description").toString(),
                        jsonObject.get("price").getAsInt())
        );
    }
}

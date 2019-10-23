import com.google.gson.JsonObject;
import bean.Purchase;
import service.PurchaseService;
import service.impl.PurchaseServiceImpl;
import util.GsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Collectors;

@WebServlet("/purchase")
public class PurchaseMethod extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringReqBody = req.getReader().lines().collect(Collectors.joining());

        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                stringReqBody
        );

        PurchaseService purchaseService = PurchaseServiceImpl.getInstance();
        try {
            purchaseService.purchaseProduct(
                    new Purchase(
                        jsonObject.get("name").toString(),
                        jsonObject.get("quantity").getAsInt(),
                        jsonObject.get("price").getAsDouble(),
                        jsonObject.get("date").toString()
                    )
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

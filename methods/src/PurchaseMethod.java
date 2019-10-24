import service.PurchaseService;
import service.impl.PurchaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/purchase")
public class PurchaseMethod extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining());

        PurchaseService purchaseService = PurchaseServiceImpl.getInstance();
        purchaseService.purchaseProduct(reqBody);
    }
}

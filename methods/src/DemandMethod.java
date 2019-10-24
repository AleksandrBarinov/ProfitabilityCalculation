import service.DemandService;
import service.impl.DemandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/demand")
public class DemandMethod extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining());

        DemandService demandService = DemandServiceImpl.getInstance();
        boolean result = demandService.demandProduct(reqBody);

        if (!result) {
            resp.sendError(400);
        }
    }
}

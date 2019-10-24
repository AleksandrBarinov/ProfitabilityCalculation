import bean.Demand;
import com.google.gson.JsonObject;
import service.DemandService;
import service.impl.DemandServiceImpl;
import util.GsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Collectors;

@WebServlet("/demand")
public class DemandMethod extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringReqBody = req.getReader().lines().collect(Collectors.joining());

        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                stringReqBody
        );

        DemandService demandService = DemandServiceImpl.getInstance();
        try {
            demandService.demandProduct(
                    new Demand(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("quantity").getAsInt(),
                        jsonObject.get("price").getAsDouble(),
                        jsonObject.get("date").getAsString()
                    )
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

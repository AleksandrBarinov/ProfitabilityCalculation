package service.impl;

import bean.Demand;
import bean.Product;
import com.google.gson.JsonObject;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.DemandService;
import util.GsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemandServiceImpl implements DemandService {

    private static final DemandServiceImpl instance = new DemandServiceImpl();
    public static DemandServiceImpl getInstance() {
        return instance;
    }
    private ProductDAO productDAO = ProductDAOimpl.getInstance();

    public Product searchProduct(String name) {
        Product product = productDAO.searchProductByName(name);
        if (product != null) {
            return product;
        } else return null;
    }

    public int checkBalance(String name) {
        return productDAO.checkBalance(name);
    }

    public void demandProduct(String reqBody) {
        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                reqBody
        );

        Demand demand = new Demand(
                jsonObject.get("name").getAsString(),
                jsonObject.get("quantity").getAsInt(),
                jsonObject.get("price").getAsDouble(),
                jsonObject.get("date").getAsString()
        );

        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(demand.getDate());
        } catch (ParseException e){}

        Product product = searchProduct(demand.getName());

        if (
                product != null || date != null ||
                checkBalance(demand.getName()) >= demand.getQuantity()
        ) {
            productDAO.demandProduct(
                    product,
                    demand.getQuantity(),
                    demand.getPrice(),
                    date
            );
        }
    }
}

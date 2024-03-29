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

    public Integer checkBalance(String name) {
        return productDAO.checkBalance(name);
    }

    public boolean demandProduct(String reqBody) {
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

        Integer balance = checkBalance(demand.getName());

        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(demand.getDate());
        } catch (ParseException ignored){}

        Product product = searchProduct(demand.getName());

        if (
                product != null &&
                demand.getQuantity() > 0 &&
                demand.getPrice() > 0 &&
                date != null &&
                balance >= demand.getQuantity()
        ){
            productDAO.demandProduct(
                    product,
                    demand.getQuantity(),
                    demand.getPrice(),
                    date
            );
            updateBalance(
                    product.getName(),
                    -(demand.getQuantity())
            );
            return true;

        } else return false;
    }

    public void updateBalance(String name, int quantity) {
        productDAO.updateBalance(name, quantity);
    }
}

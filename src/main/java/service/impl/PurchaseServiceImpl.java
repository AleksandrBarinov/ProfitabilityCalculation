package service.impl;

import bean.Product;
import bean.Purchase;
import com.google.gson.JsonObject;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.PurchaseService;
import util.GsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseServiceImpl implements PurchaseService {

    private static final PurchaseServiceImpl instance = new PurchaseServiceImpl();

    public static PurchaseServiceImpl getInstance() {
        return instance;
    }

    private ProductDAO productDAO = ProductDAOimpl.getInstance();

    public Product searchProduct(String name) {
        Product product = productDAO.searchProductByName(name);
        if (product != null) {
            return product;
        } else return null;
    }

    public boolean purchaseProduct(String reqBody) {
        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                reqBody
        );
        Purchase purchase = new Purchase(
                jsonObject.get("name").getAsString(),
                jsonObject.get("quantity").getAsInt(),
                jsonObject.get("price").getAsDouble(),
                jsonObject.get("date").getAsString()
        );

        Product product = searchProduct(purchase.getName());
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(purchase.getDate());
        } catch (ParseException ignored){}

        if (
                product != null &&
                purchase.getQuantity() > 0 &&
                purchase.getPrice() > 0 &&
                date != null
        ){
            productDAO.purchaseProduct(
                    product,
                    purchase.getQuantity(),
                    purchase.getPrice(),
                    date
            );
            updateBalance(
                    product.getName(),
                    purchase.getQuantity()
            );
            return true;

        } else return false;
    }

    public void updateBalance(String name, int quantity) {
        productDAO.updateBalance(name,quantity);
    }
}

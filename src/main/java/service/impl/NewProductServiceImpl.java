package service.impl;

import bean.Product;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.NewProductService;
import util.GsonUtil;

public class NewProductServiceImpl implements NewProductService {

    private static final NewProductServiceImpl instance = new NewProductServiceImpl();

    public static NewProductServiceImpl getInstance() {
        return instance;
    }

    private ProductDAO productDAO = ProductDAOimpl.getInstance();

    public boolean productExists(String name) {
        Product product = productDAO.searchProductByName(name);
        return product != null;
    }

    public boolean addNewProduct(String reqBody) {
        Product product;

        GsonUtil gsonUtil = GsonUtil.getInstance();
        try {
            JsonObject jsonObject = gsonUtil.getJsonObject(reqBody);
            product = new Product(
                    jsonObject.get("name").getAsString(),
                    jsonObject.get("description").getAsString()
            );
        } catch (JsonSyntaxException e) {
            return false;
        }

        if (!productExists(product.getName()) && product.getName().length() >= 2){
            productDAO.addNewProduct(product);
            return true;
        } else return false;
    }
}

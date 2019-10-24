package service.impl;

import bean.Product;
import com.google.gson.JsonObject;
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

    public Product searchProduct(String name) {
        Product product = productDAO.searchProductByName(name);
        if (product != null) {
            return product;
        } else return null;
    }

    public boolean addNewProduct(String reqBody) {
        GsonUtil gsonUtil = GsonUtil.getInstance();
        JsonObject jsonObject = gsonUtil.getJsonObject(
                reqBody
        );

        Product product = new Product(
                jsonObject.get("name").getAsString(),
                jsonObject.get("description").getAsString()
        );

        if(
                product.getName() != null ||
                product.getDescription() != null ||
                productDAO.searchProductByName(product.getName()) == null
        ){
            productDAO.addNewProduct(product);
            return true;
        } else return false;
    }
}

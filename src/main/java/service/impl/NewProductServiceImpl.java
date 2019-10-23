package service.impl;

import bean.Product;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.NewProductService;

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

    public boolean addNewProduct(Product product) {
        if(
                product.getName() != null ||
                product.getDescription() != null ||
                searchProduct(product.getName()) == null
        ){
            productDAO.addNewProduct(product);
            return true;
        } else return false;
    }
}

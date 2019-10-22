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

    public void addNewPruduct(Product product) {
        //
        if(1==1){//all is ok
            productDAO.addNewProduct(product);
        }
    }
}

package service.impl;

import bean.Product;
import bean.Purchase;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.PurchaseService;

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

    public void purchaseProduct(Purchase purchase) throws ParseException {
        Product product = searchProduct(purchase.getName());
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(purchase.getDate());

        if (product != null) {
            productDAO.demandProduct(
                    product,
                    purchase.getQuantity(),
                    purchase.getPrice(),
                    date
            );
        }
    }
}

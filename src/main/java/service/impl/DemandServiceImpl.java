package service.impl;

import bean.Demand;
import bean.Product;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.DemandService;

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

    public void demandProduct(Demand demand) throws ParseException {
        Product product = searchProduct(demand.getName());
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(demand.getDate());

        if (product != null || checkBalance(demand.getName()) >= demand.getQuantity()) {
            productDAO.demandProduct(
                    product,
                    demand.getQuantity(),
                    demand.getPrice(),
                    date
            );
        }
    }
}

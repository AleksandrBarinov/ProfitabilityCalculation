package service.impl;

import bean.Product;
import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.SalesReportService;

public class SalesReportServiceImpl implements SalesReportService {

    private static final SalesReportServiceImpl instance = new SalesReportServiceImpl();

    public static SalesReportServiceImpl getInstance() {
        return instance;
    }

    private ProductDAO productDAO = ProductDAOimpl.getInstance();

    public String generateReport(Product product, String date) {
        return productDAO.generateReport(product,date);
    }
}

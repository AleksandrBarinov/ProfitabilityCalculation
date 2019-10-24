package service.impl;

import dao.ProductDAO;
import dao.ProductDAOimpl;
import service.SalesReportService;

public class SalesReportServiceImpl implements SalesReportService {

    private static final SalesReportServiceImpl instance = new SalesReportServiceImpl();

    public static SalesReportServiceImpl getInstance() {
        return instance;
    }

    private ProductDAO productDAO = ProductDAOimpl.getInstance();

    public String generateReport(String name, String date) {
        return productDAO.generateReport(name,date);
    }
}

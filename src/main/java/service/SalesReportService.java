package service;

import bean.Product;

public interface SalesReportService {
    String generateReport(Product product, String date);
}

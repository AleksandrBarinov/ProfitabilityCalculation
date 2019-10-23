package service;

import bean.Product;

import java.util.Date;

public interface SalesReportService {
    String generateReport(Product product, Date date);
}

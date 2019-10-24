package service.impl;

import dao.ProductDAO;
import dao.ProductDAOimpl;
import dao.hibernate.models.DemandEntity;
import dao.hibernate.models.PurchaseEntity;
import service.SalesReportService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SalesReportServiceImpl implements SalesReportService {

    private static final SalesReportServiceImpl instance = new SalesReportServiceImpl();

    public static SalesReportServiceImpl getInstance() {
        return instance;
    }

    private ProductDAO productDAO = ProductDAOimpl.getInstance();

    public String generateReport(String name, String date) {

        Date desiredDate = null;
        try {
            desiredDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException ignored){}

        double purchaseSum = 0;
        double demandSum = 0;
        double profitability;

        List<PurchaseEntity> purchases = productDAO.getPurchases(name, desiredDate);
        List<DemandEntity> demands = productDAO.demands(name, desiredDate);

        for (PurchaseEntity purchase: purchases){
            if (purchase.getDate().compareTo(desiredDate) == 0){
                purchaseSum = purchaseSum + purchase.getPrice() * purchase.getQuantity();
            }
        }
        for (DemandEntity demand: demands){
            if (demand.getDate().compareTo(desiredDate) == 0){
                demandSum = demandSum + demand.getPrice() * demand.getQuantity();
            }
        }

        profitability = demandSum - purchaseSum;

        return "profitability of "+name+" is "+profitability+"";
    }
}

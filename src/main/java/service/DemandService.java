package service;

import bean.Demand;
import bean.Product;

import java.text.ParseException;

public interface DemandService {
    Product searchProduct(String name);
    int checkBalance(String name);
    void demandProduct(Demand demand) throws ParseException;
}

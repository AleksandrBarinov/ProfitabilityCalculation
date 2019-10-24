package service;

import bean.Product;

public interface NewProductService {
    Product searchProduct(String name);
    boolean addNewProduct(String reqBody);
}

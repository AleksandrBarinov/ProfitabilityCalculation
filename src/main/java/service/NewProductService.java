package service;

public interface NewProductService {
    boolean productExists(String name);
    boolean addNewProduct(String reqBody);
}

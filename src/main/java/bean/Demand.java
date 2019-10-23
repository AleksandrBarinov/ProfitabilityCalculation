package bean;

import java.util.Date;

public class Demand {

    private String name;
    private int quantity;
    private double price;
    private Date date;

    public Demand (
            String name,
            int quantity,
            double price,
            Date date
    ) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

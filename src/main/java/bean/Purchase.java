package bean;

public class Purchase {

    private String name;
    private int quantity;
    private double price;
    private String date;

    public Purchase (
            String name,
            int quantity,
            double price,
            String date
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package net.klostergaard.basketservice.model;

public class BasketEntry {
    private Product product;
    private int quantity;

    public BasketEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return quantity;
    }

    public void setAmount(int quantity) {
        this.quantity = quantity;
    }
}

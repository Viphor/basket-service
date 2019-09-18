package net.klostergaard.basketservice.model;

import java.util.List;

public class Basket {
    private final long id;
    private final List<BasketEntry> products;

    public Basket(long id, List<BasketEntry> products) {
        this.id = id;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public List<BasketEntry> getProducts() {
        return products;
    }
}

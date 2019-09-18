package net.klostergaard.basketservice.model;

import java.math.BigDecimal;

public class Product {
    private final long id;
    private final String title;
    private final BigDecimal price;
    // Can also contain other meta data

    public Product(long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

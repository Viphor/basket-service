package net.klostergaard.basketservice.service;

import net.klostergaard.basketservice.model.BasketEntry;
import net.klostergaard.basketservice.model.Product;
import net.klostergaard.basketservice.persistance.BasketItem;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    // TODO Remove when no longer needed by the mock ProductService#getProductById
    private RandomDataGenerator random = new RandomDataGenerator();

    /**
     * Converts a list of BasketItems to a list of BasketEntries, which contains
     * both a product and its quantity
     * @param basketItems
     * @return
     */
    public List<BasketEntry> getProductsAndQuantity(List<BasketItem> basketItems) {
        List<BasketEntry> list = new ArrayList<>(basketItems.size());

        for (var basketItem : basketItems) {
            list.add(new BasketEntry(getProductById(basketItem.getProductId()), basketItem.getAmount()));
        }

        return list;
    }

    /**
     * Currently just a mock methods, that should be replaced by something that
     * fetches product information from a product catalogue (preferably another micro-service)
     * @param id of the product
     * @return a Product
     */
    public Product getProductById(final long id) {
        // TODO: Replace by lookup in product service
        return new Product(id, "Testing title", BigDecimal.valueOf(random.nextLong(50, 600)));
    }
}

package net.klostergaard.basketservice.service;

import net.klostergaard.basketservice.exceptions.BasketNotExistException;
import net.klostergaard.basketservice.model.Basket;
import net.klostergaard.basketservice.persistance.BasketItem;
import net.klostergaard.basketservice.repository.BasketItemRepository;
import net.klostergaard.basketservice.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private BasketRepository basketRepository;
    private BasketItemRepository basketItemRepository;
    private ProductService productService;

    @Autowired
    public BasketService(
            BasketRepository basketRepository,
            BasketItemRepository basketItemRepository,
            ProductService productService
    ) {
        this.basketRepository = basketRepository;
        this.basketItemRepository = basketItemRepository;
        this.productService = productService;
    }

    /**
     * Retrieves a basket by id.
     * @param id of the basket
     * @return a Basket or null
     */
    public Basket basketById(final long id) {
        var basketMapping = basketRepository.findById(id);
        if (basketMapping.isEmpty()) return null;

        return new Basket(
                basketMapping.get().getId(),
                productService.getProductsAndQuantity(basketMapping.get().getBasketItems())
        );
    }

    /**
     * Adds 1 to the quantity of a product on a basket.
     * Adds the product to the basket if it does not already exist
     * @param id of the basket
     * @param productId
     */
    public void addProductToBasket(final long id, final long productId)
            throws BasketNotExistException, IllegalArgumentException
    {
        addProductToBasket(id, productId, 1);
    }

    /**
     * Adds a quantity to the existing quantity of a product on a basket
     * Adds the product to the basket if it does not already exist
     * @param id of the basket
     * @param productId
     * @param quantity to add
     */
    public void addProductToBasket(final long id, final long productId, final int quantity)
            throws BasketNotExistException, IllegalArgumentException
    {
        if (quantity < 0) {
            throw new IllegalArgumentException("You cannot add a negative quantity");
        }

        var basketMappingOpt = basketRepository.findById(id);
        if (basketMappingOpt.isEmpty()) {
            throw new BasketNotExistException("Could not add product to non initialized basket");
        }
        var basketMapping = basketMappingOpt.get();

        int index = basketMapping.getBasketItems().indexOf(new BasketItem(productId));
        BasketItem item;
        if (index >= 0) {
            item = basketMapping.getBasketItems().get(index);
            item.addAmount(quantity);
        } else {
            item = new BasketItem(basketMapping, quantity, productId);
        }
        basketItemRepository.save(item);
    }

    /**
     * Removes one quantity of the product with productId
     * Removes the product from the basket if the quantity reaches 0
     * @param id of the basket
     * @param productId
     */
    public void removeProductFromBasket(final long id, final long productId)
            throws BasketNotExistException, IllegalArgumentException
    {
        removeProductFromBasket(id, productId, 1);
    }

    /**
     * Removes a quantity of a given product from a basket.
     * Removes the product from the basket if the quantity reaches 0
     * @param id of the basket
     * @param productId
     * @param quantity to remove
     */
    public void removeProductFromBasket(final long id, final long productId, final int quantity)
            throws BasketNotExistException, IllegalArgumentException
    {
        if (quantity < 0) {
            throw new IllegalArgumentException("You cannot remove a negative quantity");
        }

        var basketMappingOpt = basketRepository.findById(id);
        if (basketMappingOpt.isEmpty()) {
            throw new BasketNotExistException("Could not remove product from non initialized basket");
        }
        var basketMapping = basketMappingOpt.get();

        int index = basketMapping.getBasketItems().indexOf(new BasketItem(productId));
        if (index >= 0) {
            var item = basketMapping.getBasketItems().get(index);
            if (item.getAmount() <= quantity) {
                basketMapping.getBasketItems().remove(index);
                basketItemRepository.delete(item);
            } else {
                item.addAmount(-quantity);
                basketItemRepository.save(item);
            }
        }
    }

}

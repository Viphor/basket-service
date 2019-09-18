package net.klostergaard.basketservice.controller;

import net.klostergaard.basketservice.exceptions.BasketNotExistException;
import net.klostergaard.basketservice.model.Basket;
import net.klostergaard.basketservice.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Basket list(@PathVariable("id") long id) {
        return basketService.basketById(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String add(
            @PathVariable("id") long id,
            @RequestParam("productId") long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) {
        try {
            basketService.addProductToBasket(id, productId, quantity);
            return "Success";
        } catch (IllegalArgumentException | BasketNotExistException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(
            @PathVariable("id") long id,
            @RequestParam("productId") long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) {
        try {
            basketService.removeProductFromBasket(id, productId, quantity);
            return "Success";
        } catch (IllegalArgumentException | BasketNotExistException e) {
            return e.getMessage();
        }
    }
}

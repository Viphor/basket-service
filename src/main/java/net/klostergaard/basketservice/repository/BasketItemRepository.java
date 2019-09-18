package net.klostergaard.basketservice.repository;

import net.klostergaard.basketservice.persistance.BasketItem;
import org.springframework.data.repository.CrudRepository;

public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {
}

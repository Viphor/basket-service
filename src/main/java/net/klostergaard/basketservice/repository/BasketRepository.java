package net.klostergaard.basketservice.repository;

import net.klostergaard.basketservice.persistance.BasketMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<BasketMapping, Long> {
}

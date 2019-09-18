package net.klostergaard.basketservice.persistance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class BasketMapping extends BaseEntity {

    @OneToMany(mappedBy = "basketMapping")
    private List<BasketItem> basketItems = new ArrayList<>();
    // Might also contain user and other meta data


    public BasketMapping() {
    }

    public BasketMapping(long id, List<BasketItem> basketItems) {
        this.setId(id);
        this.basketItems = basketItems;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}

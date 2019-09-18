package net.klostergaard.basketservice.persistance;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "BasketItems")
public class BasketItem extends BaseEntity {

    @ManyToOne
    private BasketMapping basketMapping;
    private int quantity;
    private long productId;

    public BasketItem() {
    }

    public BasketItem(long productId) {
        this.productId = productId;
    }

    public BasketItem(BasketMapping basketMapping, int quantity, long productId) {
        this.basketMapping = basketMapping;
        this.quantity = quantity;
        this.productId = productId;
    }

    public BasketMapping getBasketMapping() {
        return basketMapping;
    }

    public void setBasketMapping(BasketMapping basketMapping) {
        this.basketMapping = basketMapping;
    }

    public int getAmount() {
        return quantity;
    }

    public void setAmount(int quantity) {
        this.quantity = quantity;
    }

    public void addAmount(int quantity) {
        setAmount(getAmount() + quantity);
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketItem)) return false;
        BasketItem that = (BasketItem) o;

        // There should only exist one BasketItem per productId in a basket
        return productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }
}

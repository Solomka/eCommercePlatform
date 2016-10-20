package ukma.eCommerce.core.paymentModule.model.domain.vo;


import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * <p>
 * value object that represents order item
 * </p>
 *
 * @author Solomka
 */
public final class OrderItem {

    @Valid
    @NotNull
    private final ProductID product;
    @Valid
    @NotNull
    private final SellerID seller;
    @NotNull
    @Valid
    private final Money money;
    @Min(1)
    private final int quantity;

    public OrderItem(ProductID product, SellerID seller, Money money, int quantity) {
        this.product = Objects.requireNonNull(product);
        this.quantity = quantity;
        this.money = Objects.requireNonNull(money);
        this.seller = Objects.requireNonNull(seller);
    }

    public ProductID getProduct() {
        return product;
    }

    public Money getMoney() {
        return money;
    }

    public int getQuantity() {
        return quantity;
    }

    public SellerID getSeller() {
        return seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (quantity != orderItem.quantity) return false;
        if (!product.equals(orderItem.product)) return false;
        if (!seller.equals(orderItem.seller)) return false;
        return money.equals(orderItem.money);

    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + seller.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", seller=" + seller +
                ", money=" + money +
                ", quantity=" + quantity +
                '}';
    }
}

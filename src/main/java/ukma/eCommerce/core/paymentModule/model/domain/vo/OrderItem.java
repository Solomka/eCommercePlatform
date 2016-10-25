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
    private final Price price;
    @Min(1)
    private final int quantity;

    public OrderItem(ProductID product, SellerID seller, Price money, int quantity) {
        this.product = Objects.requireNonNull(product);
        this.quantity = Objects.requireNonNull(quantity);
        this.price = Objects.requireNonNull(money);
        this.seller = Objects.requireNonNull(seller);
    }

    public ProductID getProduct() {
        return product;
    }

    public Price getPrice() {
        return price;
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
        return price.equals(orderItem.price);

    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + seller.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", seller=" + seller +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

package ukma.eCommerce.core.paymentModule.model.domain.vo;


import ukma.eCommerce.util.IBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * value object that represents order item
 * </p>
 *
 * @author Solomka
 */
public final class OrderItemVO {

    @Valid
    @NotNull
    private final OrderVO order;
    @Valid
    @NotNull
    private final ProductVO product;
    /**
     * total quantity
     */
    @Min(1)
    private final int quantity;
    @NotNull
    @Min(0)
    private final BigDecimal sumTotal;

    /**
     * builder that creates immutable instance of {@linkplain OrderItemVO}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<OrderItemVO> {

        private OrderVO order;
        private ProductVO product;
        private int quantity;
        private BigDecimal sumTotal;

        public Builder() {
        }

        public Builder(@NotNull OrderItemVO orderItem) {

            if (orderItem == null)
                throw new NullPointerException("orderItem must not bu null");

            setOrder(orderItem.getOrder()).setProduct(orderItem.getProduct()).setQuantity(orderItem.getQuantity())
                    .setSumTotal(orderItem.getSumTotal());
        }

        public OrderVO getOrder() {
            return order;
        }

        public Builder setOrder(OrderVO order) {
            this.order = order;
            return this;
        }

        public ProductVO getProduct() {
            return product;
        }

        public Builder setProduct(ProductVO product) {
            this.product = product;
            return this;
        }

        public int getQuantity() {
            return quantity;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public BigDecimal getSumTotal() {
            return sumTotal;
        }

        public Builder setSumTotal(BigDecimal sumTotal) {
            this.sumTotal = sumTotal;
            return this;
        }

        @Override
        public OrderItemVO build() {
            return new OrderItemVO(this);
        }

    }

    private OrderItemVO(@NotNull Builder builder) {

        if (builder == null)
            throw new NullPointerException("builder must not be null");

        this.order = Objects.requireNonNull(builder.getOrder(), "order must not be null");
        this.product = Objects.requireNonNull(builder.getProduct(), "product must not be null");
        this.quantity = builder.getQuantity();
        this.sumTotal = Objects.requireNonNull(builder.getSumTotal(), "sumTotal must not be null");
    }

    public OrderVO getOrder() {
        return order;
    }

    public ProductVO getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((sumTotal == null) ? 0 : sumTotal.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItemVO other = (OrderItemVO) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (quantity != other.quantity)
            return false;
        if (sumTotal == null) {
            if (other.sumTotal != null)
                return false;
        } else if (!sumTotal.equals(other.sumTotal))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderItemVO [order=" + order + ", product=" + product + ", quantity=" + quantity + ", sumTotal="
                + sumTotal + "]";
    }

}

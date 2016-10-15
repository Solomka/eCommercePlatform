package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * value object that represents invoice item
 * </p>
 * 
 * @author Solomka
 *
 */

public final class InvoiceItemVO implements IValidateable {

	private final OrderVO order;
	private final ProductVO product;
	private final int quantity;
	private final BigDecimal sumTotal;

	public InvoiceItemVO(OrderVO order, ProductVO product, int quantity, BigDecimal sumTotal) {

		this.order = Objects.requireNonNull(order, "order == null");
		this.product = Objects.requireNonNull(product, "product == null");
		this.quantity = Objects.requireNonNull(quantity, "quantity == null");
		this.sumTotal = Objects.requireNonNull(sumTotal, "sumTotal == null");
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
	public String toString() {
		return "InvoiceItemVO [order=" + order + ", product=" + product + ", quantity=" + quantity + ", sumTotal="
				+ sumTotal + "]";
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
		InvoiceItemVO other = (InvoiceItemVO) obj;
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

}

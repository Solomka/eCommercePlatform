package ukma.eCommerce.core.paymentModule.model.domain.vo;

import ukma.eCommerce.util.IBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

import ukma.eCommerce.util.IBuilder;

/**
 * <p>
 * value object that represents invoice item
 * </p>
 *
 * @author Solomka
 */

public final class InvoiceItemVO {

    @Valid
    @NotNull
    private final OrderVO order;
    @Valid
    @NotNull
    private final ProductVO product;
    @Min(1)
    private final int quantity;
    @NotNull
    @Min(0)
    private final BigDecimal sumTotal;

	public static class Builder implements IBuilder<InvoiceItemVO> {

		private OrderVO order;
		private ProductVO product;
		private int quantity;
		private BigDecimal sumTotal;

		public Builder(final OrderVO order, final ProductVO product, final int quantity, final BigDecimal sumTotal) {

			this.order = Objects.requireNonNull(order, "order must not be null");
			this.product = Objects.requireNonNull(product, "product must not be null");
			this.quantity = Objects.requireNonNull(quantity, "quantity must not be null");
			this.sumTotal = Objects.requireNonNull(sumTotal, "sumTotal must not be null");
		}

		public Builder(final InvoiceItemVO invoiceItem) {

			Objects.requireNonNull(invoiceItem, "invoiceItem must not bu null");

			setOrder(invoiceItem.getOrder()).setProduct(invoiceItem.getProduct()).setQuantity(invoiceItem.getQuantity())
					.setSumTotal(invoiceItem.getSumTotal());

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
		public InvoiceItemVO build() {
			return new InvoiceItemVO(this);
		}

	}

	private InvoiceItemVO(Builder builder) {

		Objects.requireNonNull(builder, "builder must not be null");

		this.order = builder.getOrder();
		this.product = builder.getProduct();
		this.quantity = builder.getQuantity();
		this.sumTotal = builder.getSumTotal();
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



}

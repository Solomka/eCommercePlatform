package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.util.IBuilder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * <p>
 * value object that represents order's invoice
 * </p>
 * 
 * @author Solomka
 *
 */

public final class InvoiceVO {

	private final OrderVO order;
	private final Collection<InvoiceItemVO> invoiceItems;
	private final InvoiceStatus status;

	private final int quantityTotal;
	private final Currency currency;
	private final BigDecimal sumTotal;

	private final DateTime creationDate;
	private final DateTime paymentDate;

	/**
	 * builder that creates immutable instance of {@linkplain InvoiceVO}
	 * 
	 * @author Solomka
	 *
	 */

	public static class Builder implements IBuilder<InvoiceVO> {

		private OrderVO order;
		private Collection<InvoiceItemVO> invoiceItems;
		private InvoiceStatus status;
		private DateTime creationDate;
		private DateTime paymentDate;
		private Currency currency;

		public Builder() {
		}

		public Builder(final InvoiceVO invoice) {

			if (invoice == null) {
				throw new NullPointerException("invoice must not be null");
			}

			setOrder(invoice.getOrder()).setInvoiceItems(invoice.getInvoiceItems())
					.setStatus(invoice.getStatus()).setCreationDate(invoice.getCreationDate())
					.setPaymentDate(invoice.getCreationDate()).setCurrency(invoice.getCurrency());
		}

		public OrderVO getOrder() {
			return order;
		}

		public Builder setOrder(OrderVO order) {
			this.order = order;
			return this;
		}

		public Currency getCurrency() {
			return currency;
		}

		public Builder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public Collection<InvoiceItemVO> getInvoiceItems() {
			return invoiceItems;
		}

		/**
		 * unmodifiable collection of invoice items
		 * 
		 * @param invoiceItems
		 * @return
		 */

		public Builder setInvoiceItems(Collection<InvoiceItemVO> invoiceItems) {
			this.invoiceItems = invoiceItems == null ? null : Collections.unmodifiableCollection(invoiceItems);
			return this;
		}

		public InvoiceStatus getStatus() {
			return status;
		}

		public Builder setStatus(InvoiceStatus status) {
			this.status = status;
			return this;
		}

		public DateTime getCreationDate() {
			return creationDate;
		}

		public Builder setCreationDate(DateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public DateTime getPaymentDate() {
			return paymentDate;
		}

		public Builder setPaymentDate(DateTime paymentDate) {
			this.paymentDate = paymentDate;
			return this;
		}

		@Override
		public InvoiceVO build() {
			return new InvoiceVO(this);
		}

	}

	private InvoiceVO(Builder builder) {

		if (builder == null) {
			throw new NullPointerException("builder must not be null");
		}

		final Collection<InvoiceItemVO> invoiceItems = Objects.requireNonNull(builder.getInvoiceItems(),
				"invoiceItems must no be null");

		this.order = Objects.requireNonNull(builder.getOrder(), "order must not be null");
		this.status = Objects.requireNonNull(builder.getStatus(), "status must not be null");
		this.invoiceItems = Collections.unmodifiableCollection(invoiceItems);
		this.creationDate = Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null");
		this.currency = Objects.requireNonNull(builder.getCurrency(), "currency == null");

		this.paymentDate = builder.getPaymentDate();

		this.sumTotal = calculateSumTotal(invoiceItems);
		this.quantityTotal = calculateQuantityTotal(invoiceItems);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InvoiceVO invoiceVO = (InvoiceVO) o;

		if (quantityTotal != invoiceVO.quantityTotal) return false;
		if (!order.equals(invoiceVO.order)) return false;
		if (!invoiceItems.equals(invoiceVO.invoiceItems)) return false;
		if (currency != invoiceVO.currency) return false;
		if (!sumTotal.equals(invoiceVO.sumTotal)) return false;
		if (status != invoiceVO.status) return false;
		if (!creationDate.equals(invoiceVO.creationDate)) return false;
		return paymentDate != null ? paymentDate.equals(invoiceVO.paymentDate) : invoiceVO.paymentDate == null;

	}

	@Override
	public int hashCode() {
		int result = order.hashCode();
		result = 31 * result + invoiceItems.hashCode();
		result = 31 * result + quantityTotal;
		result = 31 * result + currency.hashCode();
		result = 31 * result + sumTotal.hashCode();
		result = 31 * result + status.hashCode();
		result = 31 * result + creationDate.hashCode();
		result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "InvoiceVO{" +
				"order=" + order +
				", invoiceItems=" + invoiceItems +
				", quantityTotal=" + quantityTotal +
				", currency=" + currency +
				", sumTotal=" + sumTotal +
				", status=" + status +
				", creationDate=" + creationDate +
				", paymentDate=" + paymentDate +
				'}';
	}

	public OrderVO getOrder() {
		return order;
	}

	public Collection<InvoiceItemVO> getInvoiceItems() {
		return invoiceItems;
	}

	public int getQuantityTotal() {
		return quantityTotal;
	}

	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public DateTime getPaymentDate() {
		return paymentDate;
	}

	public Currency getCurrency() {
		return currency;
	}

	/**
	 * calculate qauntityTotal field for InvoiceVO
	 *
	 * @param invoiceItems
	 * @return
	 */

	private int calculateQuantityTotal(Collection<InvoiceItemVO> invoiceItems) {
		int quantityTotal = 0;

		for (final InvoiceItemVO item : invoiceItems) {
			quantityTotal += item.getQuantity();
		}
		return quantityTotal;
	}

	/**
	 * calculate sumTotal field for InvoiceVO
	 *
	 * @param invoiceItems
	 * @return
	 */

	private BigDecimal calculateSumTotal(Collection<InvoiceItemVO> invoiceItems) {

		BigDecimal sum = BigDecimal.ZERO;

		for (final InvoiceItemVO item : invoiceItems) {
			sum = sum.add(item.getSumTotal());
		}
		return sum;
	}

}

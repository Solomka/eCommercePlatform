package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.util.IBuilder;

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
	private final DateTime creationDate;
	private final Currency currency;

	private final int quantityTotal;
	private final BigDecimal sumTotal;

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
		private Currency currency;

		public Builder() {
		}

		public Builder(final InvoiceVO invoice) {

			Objects.requireNonNull(invoice, "invoice must not be null");

			setOrder(invoice.getOrder()).setInvoiceItems(invoice.getInvoiceItems()).setStatus(invoice.getStatus())
					.setCreationDate(invoice.getCreationDate()).setCurrency(invoice.getCurrency());
		}

		public OrderVO getOrder() {
			return order;
		}

		public Builder setOrder(OrderVO order) {
			this.order = order;
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

		public Currency getCurrency() {
			return currency;
		}

		public Builder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		@Override
		public InvoiceVO build() {
			return new InvoiceVO(this);
		}

	}

	private InvoiceVO(Builder builder) {

		Objects.requireNonNull(builder, "builder must not be null");

		final Collection<InvoiceItemVO> invoiceItems = Objects.requireNonNull(builder.getInvoiceItems(),
				"invoiceItems must no be null");

		this.order = Objects.requireNonNull(builder.getOrder(), "order must not be null");
		this.status = Objects.requireNonNull(builder.getStatus(), "status must not be null");
		this.invoiceItems = Collections.unmodifiableCollection(invoiceItems);
		this.creationDate = Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null");
		this.currency = Objects.requireNonNull(builder.getCurrency(), "currency must not be null");
		this.sumTotal = calculateSumTotal(invoiceItems);
		this.quantityTotal = calculateQuantityTotal(invoiceItems);
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

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((invoiceItems == null) ? 0 : invoiceItems.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + quantityTotal;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		InvoiceVO other = (InvoiceVO) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (currency != other.currency)
			return false;
		if (invoiceItems == null) {
			if (other.invoiceItems != null)
				return false;
		} else if (!invoiceItems.equals(other.invoiceItems))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (quantityTotal != other.quantityTotal)
			return false;
		if (status != other.status)
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
		return "InvoiceVO [order=" + order + ", invoiceItems=" + invoiceItems + ", status=" + status + ", creationDate="
				+ creationDate + ", currency=" + currency + ", quantityTotal=" + quantityTotal + ", sumTotal="
				+ sumTotal + "]";
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

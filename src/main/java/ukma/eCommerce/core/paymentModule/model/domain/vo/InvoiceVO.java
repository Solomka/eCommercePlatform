package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * value object that represents order's invoice
 * </p>
 * 
 * @author Solomka
 *
 */

public final class InvoiceVO implements IValidateable {

	private final OrderVO order;
	private final Collection<InvoiceItemVO> invoiceItems;
	private final int quantityTotal;
	private final BigDecimal sumTotal;
	private final InvoiceStatus status;
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
		private int quantityTotal;
		private BigDecimal sumTotal;
		private InvoiceStatus status;
		private DateTime creationDate;
		private DateTime paymentDate;

		public Builder(final OrderVO order, final Collection<InvoiceItemVO> invoiceItems, final InvoiceStatus status,
				final DateTime creationDate) {

			this.order = Objects.requireNonNull(order, "order must not be null");
			this.status = Objects.requireNonNull(status, "status must not be null");
			this.creationDate = Objects.requireNonNull(creationDate, "creationDate must not be null");

			this.invoiceItems = Collections
					.unmodifiableCollection(Objects.requireNonNull(invoiceItems, "invoiceItems must no be null"));
			this.quantityTotal = calculateQauntityTotal(invoiceItems);
			this.sumTotal = calculateSumTotal(invoiceItems);

		}

		/**
		 * calculate qauntityTotal field for InvoiceVO
		 * 
		 * @param invoiceItems
		 * @return
		 */

		private int calculateQauntityTotal(Collection<InvoiceItemVO> invoiceItems) {
			int quantityTotal = 0;

			for (InvoiceItemVO item : invoiceItems) {
				quantityTotal += item.getQuantity();
			}
			return quantityTotal;
		}

		/**
		 * calculate sumTotal field for InviceVO
		 * 
		 * @param invoiceItems
		 * @return
		 */

		private BigDecimal calculateSumTotal(Collection<InvoiceItemVO> invoiceItems) {
			BigDecimal sumTotal = BigDecimal.ZERO;

			for (InvoiceItemVO item : invoiceItems) {
				sumTotal = sumTotal.add(item.getSumTotal());
			}
			return sumTotal;
		}

		public Builder(@NotNull final InvoiceVO invoice) {

			if (invoice == null) {
				throw new NullPointerException("invoice must not be null");
			}

			setOrder(invoice.getOrder()).setInvoiceItems(invoice.getInvoiceItems())
					.setQuantityTotal(invoice.getQuantityTotal()).setSumTotal(invoice.getSumTotal())
					.setStatus(invoice.getStatus()).setCreationDate(invoice.getCreationDate())
					.setPaymentDate(invoice.getPaymentDate());

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
			this.invoiceItems = Collections.unmodifiableCollection(invoiceItems);
			return this;
		}

		public int getQuantityTotal() {
			return quantityTotal;
		}

		public Builder setQuantityTotal(int quantityTotal) {
			this.quantityTotal = quantityTotal;
			return this;
		}

		public BigDecimal getSumTotal() {
			return sumTotal;
		}

		public Builder setSumTotal(BigDecimal sumTotal) {
			this.sumTotal = sumTotal;
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
			// TODO Auto-generated method stub
			return new InvoiceVO(this);
		}

	}

	private InvoiceVO(@NotNull Builder builder) {

		if (builder == null) {
			throw new NullPointerException("builder must not be null");
		}

		this.order = builder.getOrder();
		this.invoiceItems = builder.getInvoiceItems();
		this.quantityTotal = builder.getQuantityTotal();
		this.sumTotal = builder.getSumTotal();
		this.status = builder.getStatus();
		this.creationDate = builder.getCreationDate();
		this.paymentDate = builder.getPaymentDate();

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((invoiceItems == null) ? 0 : invoiceItems.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
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
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
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
		return "InvoiceVO [order=" + order + ", invoiceItems=" + invoiceItems + ", quantityTotal=" + quantityTotal
				+ ", sumTotal=" + sumTotal + ", status=" + status + ", creationDate=" + creationDate + ", paymentDate="
				+ paymentDate + "]";
	}

}

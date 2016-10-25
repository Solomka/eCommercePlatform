package ukma.eCommerce.core.paymentModule.model.domain.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * <p>
 * value object that represents order's invoice
 * </p>
 *
 * @author Solomka
 */

public final class Invoice {

	private final InvoiceID id;
	private final OrderID order;
	private final Collection<InvoiceItem> invoiceItems;
	private final Price price;
	private final int quantityTotal;
	private final DateTime creationDate;
	private InvoiceStatus status;

	/**
	 * builder that creates immutable instance of {@linkplain Invoice}
	 *
	 * @author Solomka
	 */

	public static class Builder implements IBuilder<Invoice> {

		private InvoiceID id;
		private OrderID order;
		private Collection<InvoiceItem> invoiceItems;
		private InvoiceStatus status;
		private DateTime creationDate;
		private Currency currency;
		// private IMoneyConverter converter;

		public Builder(Invoice invoice /* , IMoneyConverter converter */) {

			Objects.requireNonNull(invoice);

			setId(invoice.getId()).setOrder(invoice.getOrder()).setInvoiceItems(invoice.getInvoiceItems())
					.setStatus(invoice.getStatus()).setCreationDate(invoice.getCreationDate())
					.setCurrency(invoice.getPrice().getCurrency())
					/* .setConverter(converter) */;
		}

		public Builder() {
		}

		public InvoiceID getId() {
			return id;
		}

		public Builder setId(InvoiceID id) {
			this.id = id;
			return this;
		}

		public OrderID getOrder() {
			return order;
		}

		public Builder setOrder(OrderID order) {
			this.order = order;
			return this;
		}

		public Collection<InvoiceItem> getInvoiceItems() {
			return invoiceItems;
		}

		public Builder setInvoiceItems(Collection<InvoiceItem> invoiceItems) {
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

		/*
		 * public IMoneyConverter getConverter() { return converter; }
		 * 
		 * public Builder setConverter(IMoneyConverter converter) {
		 * this.converter = converter; return this; }
		 */
		public Currency getCurrency() {
			return currency;
		}

		public Builder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		@Override
		public Invoice build() {
			return new Invoice(this);
		}

	}

	private Invoice(Builder builder) {

		Objects.requireNonNull(builder, "builder must not be null");

		if (Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null").isAfterNow())
			throw new IllegalArgumentException("creation time should be earlier then current one");

		// final IMoneyConverter converter =
		// Objects.requireNonNull(builder.getConverter());
		final Collection<InvoiceItem> invoiceItems = ValidationUtil.validate(builder.getInvoiceItems());

		this.id = ValidationUtil.validate(builder.getId());
		this.order = ValidationUtil.validate(builder.getOrder());
		this.invoiceItems = new ArrayList<>(invoiceItems);
		this.creationDate = builder.getCreationDate();
		/*
		 * this.price =
		 * converter.convert(invoiceItems.stream().map(InvoiceItem::getPrice).
		 * collect(Collectors.toList()), builder.getCurrency());
		 */
		this.price = calculateTotalPrice(builder.getInvoiceItems(), builder.getCurrency());
		this.quantityTotal = invoiceItems.stream().mapToInt(InvoiceItem::getQuantity).sum();

		// this.status = Invoice.checkStatus(paymentDate, builder.getStatus());
	}

	private Price calculateTotalPrice(Collection<InvoiceItem> invoiceItems, Currency currency) {

		return price;

	}

	public InvoiceID getId() {
		return id;
	}

	public OrderID getOrder() {
		return order;
	}

	public Collection<InvoiceItem> getInvoiceItems() {
		return Collections.unmodifiableCollection(invoiceItems);
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public Price getPrice() {
		return price;
	}

	public int getQuantityTotal() {
		return quantityTotal;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	/*
	 * public void declineInvoice() {
	 * 
	 * if (status != InvoiceStatus.PENDING || paymentDate != null) throw new
	 * IllegalStateException(
	 * "cannot decline non-pending invoice, or payment date was already set");
	 * 
	 * this.status = InvoiceStatus.NOT_PAID; }
	 * 
	 * public void payInvoice(DateTime date) {
	 * 
	 * if (status != InvoiceStatus.PENDING || paymentDate != null) throw new
	 * IllegalStateException(
	 * "cannot pay for non-pending invoice, or payment date was already set");
	 * 
	 * if (creationDate.compareTo(Objects.requireNonNull(date, "date == null"))
	 * > 0) throw new IllegalArgumentException(
	 * "payment date cannot be earlier than creation date");
	 * 
	 * this.status = InvoiceStatus.PAID; this.paymentDate = date; }
	 * 
	 * private static InvoiceStatus checkStatus(DateTime paymentDate,
	 * InvoiceStatus status) {
	 * 
	 * Objects.requireNonNull(status);
	 * 
	 * if (paymentDate != null && (status == InvoiceStatus.NOT_PAID || status ==
	 * InvoiceStatus.PENDING)) throw new IllegalArgumentException(String.format(
	 * "cannot apply payment date in case status is %s", status));
	 * 
	 * if (paymentDate == null && status == InvoiceStatus.PAID) throw new
	 * IllegalArgumentException( String.format(
	 * "either payment date shouldn't be set or status shouldn't be %s",
	 * status));
	 * 
	 * return status; }
	 */

}

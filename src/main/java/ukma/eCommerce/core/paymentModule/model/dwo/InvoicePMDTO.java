package ukma.eCommerce.core.paymentModule.model.dwo;

import java.math.BigDecimal;
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
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * 
 * @author Solomka
 *
 */
public class InvoicePMDTO {

	private final InvoiceID id;
	private final CustomerID customer;
	private final OrderID order;
	private final Collection<InvoiceItem> invoiceItems;
	private final Price price;
	private final int quantityTotal;
	private final DateTime creationDate;
	private InvoiceStatus status;

	/**
	 * builder that creates immutable instance of {@linkplain InvoicePMDTO}
	 *
	 * @author Solomka
	 */

	public static class Builder implements IBuilder<InvoicePMDTO> {

		private InvoiceID id;
		private CustomerID customer;
		private OrderID order;
		private Collection<InvoiceItem> invoiceItems;
		private InvoiceStatus status;
		private DateTime creationDate;
		private Currency currency;

		public Builder(InvoicePMDTO invoice) {

			Objects.requireNonNull(invoice);

			setId(invoice.getId()).setOrder(invoice.getOrder()).setInvoiceItems(invoice.getInvoiceItems())
					.setStatus(invoice.getStatus()).setCreationDate(invoice.getCreationDate())
					.setCurrency(invoice.getPrice().getCurrency()).setCustomer(invoice.getCustomer());
		}

		public Builder() {
		}

		public CustomerID getCustomer() {
			return customer;
		}

		public Builder setCustomer(CustomerID customer) {
			this.customer = customer;
			return this;
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

		public Currency getCurrency() {
			return currency;
		}

		public Builder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		@Override
		public InvoicePMDTO build() {
			return new InvoicePMDTO(this);
		}

	}

	private InvoicePMDTO(Builder builder) {

		Objects.requireNonNull(builder, "builder must not be null");

		if (Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null").isAfterNow())
			throw new IllegalArgumentException("creation time should be earlier then current one");

		final Collection<InvoiceItem> invoiceItems = ValidationUtil.validate(builder.getInvoiceItems());

		this.id = ValidationUtil.validate(builder.getId());
		this.customer = ValidationUtil.validate(builder.getCustomer());
		this.order = ValidationUtil.validate(builder.getOrder());
		this.invoiceItems = new ArrayList<>(invoiceItems);
		this.creationDate = builder.getCreationDate();

		this.price = calculateTotalPrice(builder.getInvoiceItems(), builder.getCurrency());
		this.quantityTotal = invoiceItems.stream().mapToInt(InvoiceItem::getQuantity).sum();

		this.status = Objects.requireNonNull(builder.getStatus(), "status must not be null");
	}

	/**
	 * calculate total invoice price
	 *
	 * @param invoiceItems
	 * @param currency
	 * @return
	 */

	private Price calculateTotalPrice(Collection<InvoiceItem> invoiceItems, Currency currency) {

		BigDecimal sumTotal = BigDecimal.ZERO;

		for (InvoiceItem item : invoiceItems) {
			sumTotal = sumTotal.add(item.getPrice().getAmount());
		}

		return new Price(currency, sumTotal);

	}

	public CustomerID getCustomer() {
		return customer;
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

	@Override
	public String toString() {
		return "InvoicePMDTO [id=" + id + ", customer=" + customer + ", order=" + order + ", invoiceItems="
				+ invoiceItems + ", price=" + price + ", quantityTotal=" + quantityTotal + ", creationDate="
				+ creationDate + ", status=" + status + "]";
	}
	
	

}

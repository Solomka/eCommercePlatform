package ukma.eCommerce.core.paymentModule.model.domain.bo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((invoiceItems == null) ? 0 : invoiceItems.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + quantityTotal;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Invoice other = (Invoice) obj;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (quantityTotal != other.quantityTotal)
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", order=" + order + ", invoiceItems=" + invoiceItems + ", price=" + price
                + ", quantityTotal=" + quantityTotal + ", creationDate=" + creationDate + ", status=" + status + "]";
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
     */

}

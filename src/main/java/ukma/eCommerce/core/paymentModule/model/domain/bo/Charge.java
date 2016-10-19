package ukma.eCommerce.core.paymentModule.model.domain.bo;

import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Aggregate root object that represents charge
 *
 * @author Solomka
 */
public final class Charge {

    private final long id;
    private final InvoiceID invoice;
    private final CreditCard creditCard;
    private final ChargeStatus status;

    /**
     * builder that creates immutable instance of {@linkplain Charge}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<Charge> {

        private long id;
        private InvoiceID invoice;
        private CreditCard creditCard;
        private ChargeStatus status;

        public Builder() {
        }

        public Builder(@NotNull Charge charge) {

            if (charge == null)
                throw new NullPointerException("charge must not be null");

            setId(charge.getId()).setInvoice(charge.getInvoice()).setCreditCard(charge.getCreditCard())
                    .setStatus(charge.getStatus());
        }

        public long getId() {
            return id;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public InvoiceID getInvoice() {
            return invoice;
        }

        public Builder setInvoice(InvoiceID invoice) {
            this.invoice = invoice;
            return this;
        }

        public CreditCard getCreditCard() {
            return creditCard;
        }

        public Builder setCreditCard(CreditCard creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public ChargeStatus getStatus() {
            return status;
        }

        public Builder setStatus(ChargeStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public Charge build() {
            return new Charge(this);
        }

    }

    private Charge(@NotNull Builder builder) {

        Objects.requireNonNull(builder, "builder must no be null");

        if (builder.getId() < 1)
            throw new IllegalArgumentException("id < 1");

        this.id = builder.getId();
        this.invoice = ValidationUtil.validate(builder.getInvoice());
        this.creditCard = ValidationUtil.validate(builder.getCreditCard());
        this.status = Objects.requireNonNull(builder.getStatus(), "status == null");
    }

    public long getId() {
        return id;
    }

    public InvoiceID getInvoice() {
        return invoice;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public ChargeStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Charge charge = (Charge) o;

        if (id != charge.id) return false;
        if (!invoice.equals(charge.invoice)) return false;
        if (!creditCard.equals(charge.creditCard)) return false;
        return status == charge.status;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + invoice.hashCode();
        result = 31 * result + creditCard.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", invoice=" + invoice +
                ", creditCard=" + creditCard +
                ", status=" + status +
                '}';
    }
}

package ukma.eCommerce.core.paymentModule.model.domain.bo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Aggregate root object that represents charge
 *
 * @author Solomka
 */
public final class Charge {

    private final ChargeID id;
    private final InvoiceID invoice;
    private final ReadWriteLock readWriteLock;
    private DateTime paymentDate;
    private ChargeStatus status;

    /**
     * builder that creates immutable instance of {@linkplain Charge}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<Charge> {

        private ChargeID id;
        private InvoiceID invoice;
        private DateTime paymentDate;
        private ChargeStatus status;

        public Builder() {
        }

        public Builder(Charge charge) {

            Objects.requireNonNull(charge, "charge must not be null");

            setId(charge.getId()).setInvoice(charge.getInvoice())
                    .setStatus(charge.getStatus());
        }

        public ChargeID getId() {
            return id;
        }

        public Builder setId(ChargeID id) {
            this.id = id;
            return this;
        }

		/*
         * public Build setID(){ this.id = UUID.randomUUID() return this; }
		 */

        public InvoiceID getInvoice() {
            return invoice;
        }

        public Builder setInvoice(InvoiceID invoice) {
            this.invoice = invoice;
            return this;
        }

        public DateTime getPaymentDate() {
            return paymentDate;
        }

        public Builder setPaymentDate(DateTime paymentDate) {
            this.paymentDate = paymentDate;
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

        this.id = ValidationUtil.validate(builder.getId());
        this.invoice = ValidationUtil.validate(builder.getInvoice());
        this.paymentDate = builder.getPaymentDate();
        this.status = Objects.requireNonNull(builder.getStatus(), "status == null");

        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public ChargeID getId() {
        return id;
    }

    public InvoiceID getInvoice() {
        return invoice;
    }

    public ChargeStatus getStatus() {
        // status won't be returned until #setPaymentDate finishes execution
        readWriteLock.readLock().lock();

        try {
            return status;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public DateTime getPaymentDate() {
        // paymentDate won't be returned until #setPaymentDate finishes execution
        readWriteLock.readLock().lock();

        try {
            return paymentDate;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * Sets specified payment date. This is thread safe method
     *
     * @param paymentDate payment date to set
     */
    public void setPaymentDate(DateTime paymentDate) {

        if (DateTime.now().compareTo(Objects.requireNonNull(paymentDate, "paymentDate == null")) < 0)
            throw new IllegalArgumentException("payment date cannot be earlier than current date");

        readWriteLock.writeLock().lock();

        try {
            // Only one thread in the moment of time
            // can change status and payment date
            if (status != ChargeStatus.PENDING)
                throw new IllegalStateException("cannot set date for non-pending charge");

            if (this.paymentDate != null)
                throw new IllegalStateException("payment date was already set");
            // if payment date was set => all job is done, payment succeeded
            this.status = Charge.checkStatus(paymentDate, ChargeStatus.SUCCEEDED);
            this.paymentDate = paymentDate;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Changes charge status. This is thread safe method
     *
     * @param status status to be set
     */
    public void changeStatus(ChargeStatus status) {

        readWriteLock.writeLock().lock();

        try {
            this.status = Charge.checkStatus(paymentDate, status);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Checks charge's correctness
     *
     * @param paymentDate payment date which belongs to instance of {@linkplain Charge}
     * @param status      status which belongs to instance of {@linkplain Charge}
     * @return checked charge status
     * @throws IllegalStateException if charge is in self-contradictory state
     */
    private static ChargeStatus checkStatus(DateTime paymentDate, ChargeStatus status) {

        Objects.requireNonNull(status);

        if (paymentDate != null && status == ChargeStatus.PENDING)
            throw new IllegalStateException(
                    String.format("Charge with set paymentDate can't have status %s. Was %s",
                            ChargeStatus.PENDING, paymentDate));

        if (paymentDate == null && (status == ChargeStatus.SUCCEEDED || status == ChargeStatus.RETURNED
                || status == ChargeStatus.FAILED))
            throw new IllegalStateException(
                    String.format("Charge without paymentDate can't have status %s or %s or %s. Was %s",
                            ChargeStatus.SUCCEEDED, ChargeStatus.RETURNED, ChargeStatus.FAILED, status));

        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
        result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
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
        Charge other = (Charge) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (invoice == null) {
            if (other.invoice != null)
                return false;
        } else if (!invoice.equals(other.invoice))
            return false;
        if (paymentDate == null) {
            if (other.paymentDate != null)
                return false;
        } else if (!paymentDate.equals(other.paymentDate))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Charge [id=" + id + ", invoice=" + invoice + ", paymentDate="
                + paymentDate + ", status=" + status + "]";
    }

}

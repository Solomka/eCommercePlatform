package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.util.IBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * value object that represents charge
 *
 * @author Solomka
 */
public final class ChargeVO {

    private final InvoiceVO invoice;
    private final CreditCardVO creditCardVO;
    private final Currency currency;
    private final BigDecimal sumTotal;
    private final ChargeStatus status;
    private final DateTime creationDate;

    /**
     * builder that creates immutable instance of {@linkplain ChargeVO}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<ChargeVO> {

        private InvoiceVO invoice;
        private CreditCardVO creditCardVO;
        private Currency currency;
        private BigDecimal sumTotal;
        private ChargeStatus status;
        private DateTime creationDate;

        public Builder() {
        }

        public Builder(@NotNull ChargeVO charge) {
            if (charge == null)
                throw new NullPointerException("charge must not be null");

            setInvoice(charge.getInvoice()).setCreditCardVO(charge.getCreditCardVO()).setCurrency(charge.getCurrency())
                    .setSumTotal(charge.getSumTotal()).setStatus(charge.getStatus())
                    .setCreationDate(charge.getCreationDate());
        }

        public InvoiceVO getInvoice() {
            return invoice;
        }

        public Builder setInvoice(InvoiceVO invoice) {
            this.invoice = invoice;
            return this;
        }

        public CreditCardVO getCreditCardVO() {
            return creditCardVO;
        }

        public Builder setCreditCardVO(CreditCardVO creditCardVO) {
            this.creditCardVO = creditCardVO;
            return this;
        }

        public Currency getCurrency() {
            return currency;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public BigDecimal getSumTotal() {
            return sumTotal;
        }

        public Builder setSumTotal(BigDecimal sumTotal) {
            this.sumTotal = sumTotal;
            return this;
        }

        public ChargeStatus getStatus() {
            return status;
        }

        public Builder setStatus(ChargeStatus status) {
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

        @Override
        public ChargeVO build() {
            return new ChargeVO(this);
        }

    }

    private ChargeVO(@NotNull Builder builder) {

        Objects.requireNonNull(builder, "builder == null");

        this.invoice = Objects.requireNonNull(builder.getInvoice(), "invoice == null");
        this.creditCardVO = Objects.requireNonNull(builder.getCreditCardVO(), "creditCard == null");
        this.currency = Objects.requireNonNull(builder.getCurrency(), "currency == null");
        this.sumTotal = Objects.requireNonNull(builder.getSumTotal(), "sumTotal == null");
        this.status = Objects.requireNonNull(builder.getStatus(), "status == null");
        this.creationDate = Objects.requireNonNull(builder.getCreationDate(), "creationDate == null");
    }

    public InvoiceVO getInvoice() {
        return invoice;
    }

    public CreditCardVO getCreditCardVO() {
        return creditCardVO;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public ChargeStatus getStatus() {
        return status;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "ChargeVO [invoice=" + invoice + ", creditCardVO=" + creditCardVO + ", currency=" + currency
                + ", sumTotal=" + sumTotal + ", status=" + status + ", creationDate=" + creationDate + "]";
    }

}

package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.util.IBuilder;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * value object that represents charge
 *
 * @author Solomka
 */
public final class ChargeVO {

    private final InvoiceVO invoice;
    private final CreditCardVO creditCardVO;
    private final ChargeStatus status;

    /**
     * builder that creates immutable instance of {@linkplain ChargeVO}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<ChargeVO> {

        private InvoiceVO invoice;
        private CreditCardVO creditCardVO;
        private ChargeStatus status;

        public Builder() {
        }

        public Builder(@NotNull ChargeVO charge) {
            if (charge == null)
                throw new NullPointerException("charge must not be null");

            setInvoice(charge.getInvoice()).setCreditCardVO(charge.getCreditCardVO())
                    .setStatus(charge.getStatus());
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

        public ChargeStatus getStatus() {
            return status;
        }

        public Builder setStatus(ChargeStatus status) {
            this.status = status;
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
        this.status = Objects.requireNonNull(builder.getStatus(), "status == null");
    }

    public CreditCardVO getCreditCardVO() {
        return creditCardVO;
    }

    public ChargeStatus getStatus() {
        return status;
    }

    public DateTime getCreationDate() {
        return invoice.getPaymentDate();
    }

    public InvoiceVO getInvoice() {
        return invoice;
    }

    @Override
    public String toString() {
        return "ChargeVO [invoice=" + invoice + ", creditCardVO=" + creditCardVO
                + ", status=" + status + ", creationDate=" + getCreationDate() + "]";
    }

}

package ukma.eCommerce.core.paymentModule.model.dwo;

import org.joda.time.DateTime;

/**
 * <p>
 * Value object which describes credit card form
 * </p>
 *
 * @author Max Oliynick
 */
public final class CreditCardDTO {

    private String number;
    private String cvv;
    private DateTime expirationDate;

    public CreditCardDTO(String number, String cvv, DateTime expirationDate) {
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public CreditCardDTO() {}

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpirationDate(DateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public String getCvv() {
        return cvv;
    }

    public DateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCardDTO that = (CreditCardDTO) o;

        if (!number.equals(that.number)) return false;
        if (!cvv.equals(that.cvv)) return false;
        return expirationDate.equals(that.expirationDate);

    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + cvv.hashCode();
        result = 31 * result + expirationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "number='" + number + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}


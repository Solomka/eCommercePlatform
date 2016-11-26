package ukma.eCommerce.core.paymentModule.model.dwo;

import org.springframework.validation.annotation.Validated;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;

import javax.validation.constraints.NotNull;

/**
 * dto for providing paymentManager with info required for stripe charging execution
 *
 * @author Solomka
 */
@Validated
public final class PaymentParams {

    @NotNull
    private final CustomerID customerID;
    @NotNull
    private final Price price;
    @NotNull
    private final CreditCard creditCard;

    public PaymentParams(CustomerID customerID, Price price, CreditCard creditCard) {
        this.customerID = customerID;
        this.price = price;
        this.creditCard = creditCard;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public Price getPrice() {
        return price;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    @Override
    public String toString() {
        return "PaymentParams{" +
                "customerID=" + customerID +
                ", price=" + price +
                ", creditCard=" + creditCard +
                '}';
    }
}

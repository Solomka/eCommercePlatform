package ukma.eCommerce.core.paymentModule.model.dwo;

import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;

/**
 * dto for providing paymentManager with info required for stripe charging execution
 * 
 * @author Solomka
 *
 */
public final class InvoicePMDTO {

	private CustomerID customerID;
	private Price price;

	public InvoicePMDTO() {

	}

	public InvoicePMDTO(CustomerID customerID, Price price) {
		this.customerID = customerID;
		this.price = price;
	}

	public CustomerID getCustomerID() {
		return customerID;
	}

	public void setCustomerID(CustomerID customerID) {
		this.customerID = customerID;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "InvoicePMDTO [customerID=" + customerID + ", price=" + price + "]";
	}

}

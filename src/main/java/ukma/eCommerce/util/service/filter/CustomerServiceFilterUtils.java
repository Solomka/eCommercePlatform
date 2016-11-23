package ukma.eCommerce.util.service.filter;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;


/**
 * service - specific filters for CustomerRepository
 * 
 * @author Solomka
 *
 */

public final class CustomerServiceFilterUtils {

	private CustomerServiceFilterUtils() {
		throw new RuntimeException();
	}

	public static CutomerByIdFilter customerById(CustomerID customerId) {
		return new CutomerByIdFilter(customerId);
	}

}

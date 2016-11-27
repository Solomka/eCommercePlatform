package ukma.eCommerce.util.service.filter;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.repository.filter.IExposedFilter;


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

	public static IExposedFilter getCustomerById(CustomerID customerId) {
		return new CutomerByIdFilter(customerId);
	}

}

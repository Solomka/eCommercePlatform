package ukma.eCommerce.util.repository.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;

public class CustomerRepoFilterUtils {

	private CustomerRepoFilterUtils() {
		throw new RuntimeException();
	}

	public static Specification<CustomerPO> findCustomerById(CustomerID customerId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("id"), customerId.getId());
		};
	}

}

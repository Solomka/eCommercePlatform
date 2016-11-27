package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.core.userModule.model.domain.dwo.CustomerSaveDTO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */

@Repository("customerRepository")
@Transactional
public class CustomerRepository
		extends AHibernateRepository<Customer, CustomerID, CustomerSaveDTO, IExposedFilter, CustomerPO, UUID> {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Customer> find(IExposedFilter f) {
		return findAllBySpecification((Specification<CustomerPO>) f.toFilter()).stream()
				.map(customerPO -> CustomerPOConverter.toCustomer(customerPO)).collect(Collectors.toList());
	}

	@Override
	public Customer create(CustomerSaveDTO customerSaveDTO) {
		final CustomerPO customerPO = CustomerPOConverter.fromCustomerSaveDTO(customerSaveDTO);
		savePO(customerPO);
		return CustomerPOConverter.toCustomer(customerPO);
	}

	@Override
	public Customer update(Customer customer) {
		final CustomerPO customerPO = updatePO(CustomerPOConverter.fromCustomer(customer));
		return CustomerPOConverter.toCustomer(customerPO);
	}

	@Override
	public void delete(CustomerID customerId) {
		deletePOById(customerId.getId());

	}

}

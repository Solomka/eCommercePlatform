package ukma.eCommerce.core.userModule.repository;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rx.Observable;
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

	@Override
	public Observable<List<Customer>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Customer> create(CustomerSaveDTO customerSaveDTO) {
		return Observable.create(subsciber -> {
			CustomerPO customerPO = CustomerPOConverter.fromCustomerSaveDTO(customerSaveDTO);
			savePO(customerPO);
			CustomerPOConverter.toCustomer(customerPO);
		});
	}
/*
	@NotNull
	public Customer createTest(CustomerSaveDTO customerSaveDTO) {
		CustomerPO customerPO = CustomerPOConverter.fromCustomerSaveDTO(customerSaveDTO);
		System.out.println("Repo customerPO before: "+ customerPO);
		savePO(customerPO);
		System.out.println("Repo customerPO after: "+ customerPO);
		return CustomerPOConverter.toCustomer(customerPO);
	}
*/
	@Override
	public Observable<Boolean> delete(CustomerID k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Customer> update(Customer t) {
		// TODO Auto-generated method stub
		return null;
	}

}

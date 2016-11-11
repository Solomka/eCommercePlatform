package ukma.eCommerce.core.userModule.repository;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.core.userModule.model.domain.dwo.CustomerEntity;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import java.util.Collection;
import java.util.UUID;

/**
 * 
 * @author Solomka
 *
 */
@Repository("customerRepository")
public class CustomerRepository extends AHibernateRepository<CustomerPO, UUID> implements
		IRepository<Customer, CustomerID, CustomerEntity, IExposedFilter> {

	@Override
	public Observable<Collection<Customer>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Customer> create(CustomerEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Void> delete(CustomerID k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Customer> update(Customer t) {
		// TODO Auto-generated method stub
		return null;
	}

}

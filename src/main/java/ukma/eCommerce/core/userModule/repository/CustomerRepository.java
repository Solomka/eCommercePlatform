package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.core.userModule.model.domain.dwo.CustomerEntity;
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
		extends AHibernateRepository<Customer, CustomerID, CustomerEntity, IExposedFilter, CustomerPO, UUID> {

	@Override
	public Observable<List<Customer>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Customer> create(CustomerEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

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

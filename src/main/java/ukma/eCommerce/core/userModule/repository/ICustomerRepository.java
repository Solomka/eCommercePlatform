package ukma.eCommerce.core.userModule.repository;

import org.springframework.stereotype.Repository;
import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IFilter;

/**
 * Created by Максим on 10/8/2016.
 */
@Repository
public interface ICustomerRepository extends IRepository<Customer, Long, Object, String, IFilter<String>> {
}

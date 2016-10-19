package ukma.eCommerce.core.userModule.repository;

import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.filter.BasicFilter;

/**
 * Created by Максим on 10/8/2016.
 */
public interface ISellerRepository extends IRepository<Seller, Long, Object, BasicFilter> {
}

package ukma.eCommerce.core.paymentModule.repository;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Seller;
import ukma.eCommerce.util.filter.BasicFilter;

/**
 * Created by Максим on 10/8/2016.
 */
public interface ISellerRepository extends IUserRepository<Seller, Object, BasicFilter>  {
}

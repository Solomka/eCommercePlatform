package ukma.eCommerce.core.paymentModule.repository;

import ukma.eCommerce.core.paymentModule.model.domain.bo.User;
import ukma.eCommerce.util.filter.BasicFilter;

/**
 * Created by Максим on 10/8/2016.
 */
public interface IUserRepository<U extends User, VO, F extends BasicFilter> extends IRepository<U, Long, VO, F> {
}

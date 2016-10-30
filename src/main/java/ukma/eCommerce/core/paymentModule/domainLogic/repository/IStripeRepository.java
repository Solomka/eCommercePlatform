package ukma.eCommerce.core.paymentModule.domainLogic.repository;

import org.springframework.stereotype.Repository;
import ukma.eCommerce.util.IReadonlyRepository;

/**
 * Created by Максим on 10/31/2016.
 */
@Repository
public interface IStripeRepository extends IReadonlyRepository<String, StripeFilter> {
}

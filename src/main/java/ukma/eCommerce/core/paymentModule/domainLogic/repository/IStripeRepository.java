package ukma.eCommerce.core.paymentModule.domainLogic.repository;

import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.IRepository;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * Adapts basic repository {@linkplain IRepository} in order to use in conjunction with
 * stripe
 * </p>
 * Created by Максим on 10/31/2016.
 */
public interface IStripeRepository {

    Observable<String> find(@NotNull CustomerID customerID);

}

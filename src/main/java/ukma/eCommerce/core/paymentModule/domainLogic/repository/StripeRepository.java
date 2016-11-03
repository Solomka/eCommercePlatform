package ukma.eCommerce.core.paymentModule.domainLogic.repository;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/3/2016.
 */
@Repository
public final class StripeRepository implements IStripeRepository {

    @Override
    public Observable<String> find(@NotNull CustomerID customerID) {
        return null;
    }
}

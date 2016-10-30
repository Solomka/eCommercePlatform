package ukma.eCommerce.util;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.util.filter.BasicFilter;

import javax.validation.constraints.Null;
import java.util.Collection;

/**
 * Created by Максим on 10/31/2016.
 */
@Repository
public interface IReadonlyRepository<BO, Filter extends BasicFilter> {
    /**
     * Asynchronously finds and returns collection of objects in abstract
     * repository whose type matches specified one
     *
     * @param filter filter chain
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<Collection<BO>> find(@Null Filter filter);

}

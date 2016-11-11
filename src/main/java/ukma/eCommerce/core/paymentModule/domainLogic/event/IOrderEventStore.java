package ukma.eCommerce.core.paymentModule.domainLogic.event;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * Event store for order bounded context
 * </p>
 * Created by Максим on 11/11/2016.
 */
@Qualifier("orderEventStore")
public interface IOrderEventStore {

    /**
     * Stores event into queue
     *
     * @param event event to store
     */
    void store(@NotNull OrderCreatedEvent event);

}

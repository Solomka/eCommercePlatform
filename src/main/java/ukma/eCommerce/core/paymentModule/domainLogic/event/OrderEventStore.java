package ukma.eCommerce.core.paymentModule.domainLogic.event;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/11/2016.
 */
final class OrderEventStore implements IOrderEventStore {

    @Override
    public void store(@NotNull OrderCreatedEvent event) {
        // todo store into queue
    }
}

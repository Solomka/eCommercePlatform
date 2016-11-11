package order;

import ukma.eCommerce.core.paymentModule.domainLogic.event.IOrderEventStore;
import ukma.eCommerce.core.paymentModule.domainLogic.event.OrderCreatedEvent;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/11/2016.
 */
public class TestOrderEventStore implements IOrderEventStore {
    @Override
    public void store(@NotNull OrderCreatedEvent event) {

    }
}

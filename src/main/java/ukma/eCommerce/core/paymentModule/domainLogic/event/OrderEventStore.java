package ukma.eCommerce.core.paymentModule.domainLogic.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/11/2016.
 */
@Component
final class OrderEventStore implements IOrderEventStore {

    private final ApplicationEventPublisher publisher;
   // private final Map

    @Autowired
    public OrderEventStore(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void store(@NotNull OrderCreatedEvent event) {
        // todo store into queue
        publisher.publishEvent(event);
    }

}

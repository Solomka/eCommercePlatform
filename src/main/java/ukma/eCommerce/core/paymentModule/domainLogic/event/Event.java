package ukma.eCommerce.core.paymentModule.domainLogic.event;

import java.util.UUID;

/**
 * Created by Максим on 11/17/2016.
 */
public class Event {

    private final UUID id;

    public Event() {
        id = UUID.randomUUID();
    }

    public final UUID getId() {
        return id;
    }
}

package ukma.eCommerce.core.paymentModule.domainLogic.event;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * <p>
 *     Represents newly created order
 * </p>
 * Created by Максим on 11/11/2016.
 */
public final class OrderCreatedEvent extends Event {

    private final Order order;

    public OrderCreatedEvent(@NotNull Order order) {
        this.order = Objects.requireNonNull(order);
    }

    public Order getOrder() {
        return order;
    }

}

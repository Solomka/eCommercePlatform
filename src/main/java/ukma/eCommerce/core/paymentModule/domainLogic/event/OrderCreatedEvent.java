package ukma.eCommerce.core.paymentModule.domainLogic.event;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderProxy;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * <p>
 *     Represents newly created order
 * </p>
 * Created by Максим on 11/11/2016.
 */
public final class OrderCreatedEvent {

    private final OrderProxy proxy;

    public OrderCreatedEvent(@NotNull OrderProxy proxy) {
        this.proxy = Objects.requireNonNull(proxy);
    }

    public OrderProxy getOrder() {
        return proxy;
    }

}

package ukma.eCommerce.core.paymentModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rx.Observable;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.domainLogic.event.IOrderEventStore;
import ukma.eCommerce.core.paymentModule.domainLogic.event.OrderCreatedEvent;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.InOrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OutOrderDTO;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Default implementation of {@linkplain IOrderApplicationService}</p>
 * Created by Максим on 11/6/2016.
 */
@Service
public final class OrderApplicationService implements IOrderApplicationService {

    private static final Logger LOGGER = Logger.getLogger(OrderApplicationService.class.getName());

    private final IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> repository;
    private final IOrderEventStore eventStore;

    @Autowired
    public OrderApplicationService(@NotNull @Qualifier("orderRepository") IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> repository,
                                   @NotNull @Qualifier("orderEventStore") IOrderEventStore eventStore) {
        this.repository = repository;
        this.eventStore = eventStore;
    }

    @Override
    public Observable<OutOrderDTO> createOrder(InOrderDTO dto) {

        if (!dto.getClass().isAnnotationPresent(Validated.class))
            throw new RuntimeException("@Validate annotation expected!");

        return Observable.create((Observable.OnSubscribe<OutOrderDTO>) subscriber -> {
            // convert dto into entity instance and
            // try to create order in repository asynchronously
            try {
                final Order order = repository.create(OrderConverter.toEntity(dto));
                // handle repository result
                // and publish order instance
                subscriber.onNext(OrderConverter.toDto(order));
                eventStore.store(new OrderCreatedEvent(order));
            } catch (final Exception e) {
                subscriber.onError(e);
                LOGGER.log(Level.SEVERE, "failed to create order", e);
            } finally {
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());
    }

}

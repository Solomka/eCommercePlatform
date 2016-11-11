package ukma.eCommerce.core.paymentModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.domainLogic.event.IOrderEventStore;
import ukma.eCommerce.core.paymentModule.domainLogic.event.OrderCreatedEvent;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderProxy;
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

        return Observable.create(subscriber ->
                // convert dto into entity instance and
                // try to create order in repository asynchronously
                repository.create(OrderDtoConverter.toEntity(dto))
                        // handle repository result
                        .subscribe(
                                order -> {
                                    final OrderProxy proxy = new OrderProxy(order);
                                    // publish proxy object
                                    subscriber.onNext(OrderDtoConverter.toDto(proxy));
                                    eventStore.store(new OrderCreatedEvent(proxy));
                                },
                                th -> {
                                    LOGGER.log(Level.WARNING,
                                            String.format("Error occurred while creating order for dto %s", dto),
                                            th);
                                    // todo change exception class to -> OrderCreationException...
                                    subscriber.onError(new Exception("Failed to create order"));
                                }
                        ));
    }

}

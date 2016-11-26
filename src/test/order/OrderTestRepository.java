package order;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.OrderConverter;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Максим on 11/8/2016.
 */
public final class OrderTestRepository implements IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> {

    private static final Logger LOGGER = Logger.getLogger(OrderTestRepository.class.getName());

    private final Collection<Order> data = new ArrayList<>(0);

    public OrderTestRepository() {
        LOGGER.log(Level.INFO, String.format("repository %s created", getClass().getSimpleName()));
    }

    @Override
    public Collection<Order> find(IExposedFilter iExposedFilter) {
        return null;
    }

    @Override
    public Order create(@NotNull OrderSaveDTO orderEntity) {

        final Order order = OrderConverter.convert(orderEntity, new OrderID(UUID.randomUUID().toString()));
        data.add(order);
        return order;
    }

    @Override
    public void delete(@NotNull OrderID orderID) {

    }

    @Override
    public Order update(@NotNull Order order) {
        return null;
    }
}

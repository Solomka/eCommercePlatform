package ukma.eCommerce.core.paymentModule.service;

import org.springframework.stereotype.Service;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderVO;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.paymentModule.repository.IRepository;
import ukma.eCommerce.util.filter.OrderFilter;
import ukma.eCommerce.util.IRetrieveCallback;

import javax.validation.constraints.NotNull;

@Service("orderService")
public class OrderService implements IOrderService {

    private IRepository<Order, String, OrderVO, OrderFilter> repository;


    @Override
    public void createOrder(@NotNull OrderDTO orderDTO, @NotNull IRetrieveCallback<Order> callback) {

    }
}

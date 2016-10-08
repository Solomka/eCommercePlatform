package ukma.eCommerce.core.paymentModule.service;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.util.IRetrieveCallback;

import javax.validation.constraints.NotNull;

public interface IOrderService {

    void createOrder(@NotNull OrderDTO orderDTO, @NotNull IRetrieveCallback<Order> callback);

}

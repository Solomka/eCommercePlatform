package ukma.eCommerce.core.paymentModule.repository;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderEntity;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/8/2016.
 */
public final class OrderConverter {

    private OrderConverter() {
        throw new RuntimeException();
    }

    public static Order convert(@NotNull OrderEntity entity, @NotNull OrderID id) {

        return new Order.Builder().setId(id)
                .setOrderItems(entity.getOrderItems())
                .setCreationDate(entity.getCreationDate())
                .setFulfilmentDate(entity.getFulfilmentDate())
                .setCustomer(entity.getCustomer())
                .setShipment(entity.getShipment())
                .setOrderStatus(entity.getStatus())
                .build();
    }

}

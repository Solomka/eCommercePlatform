package ukma.eCommerce.core.paymentModule.service;

import org.modelmapper.ModelMapper;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderEntity;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/6/2016.
 */
final class OrderDtoConverter {

    private static final ModelMapper MAPPER = new ModelMapper();

    private OrderDtoConverter() {
        throw new RuntimeException();
    }

    static OrderEntity toEntity(@NotNull OrderDTO dto) {
        return MAPPER.map(dto, OrderEntity.class);
    }

    static OrderDTO toDto(@NotNull Order order) {
        // change
        return MAPPER.map(order, OrderDTO.class);
    }

}

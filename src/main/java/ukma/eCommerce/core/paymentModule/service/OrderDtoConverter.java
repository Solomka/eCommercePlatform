package ukma.eCommerce.core.paymentModule.service;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ShipmentDetails;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderEntity;
import ukma.eCommerce.core.userModule.model.domain.vo.Address;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.model.domain.vo.FullName;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Максим on 11/6/2016.
 */
final class OrderDtoConverter {

    private static final ModelMapper MAPPER = new ModelMapper();

    private OrderDtoConverter() {
        throw new RuntimeException();
    }

    private static Address fromDto(OrderDTO.Shipment.Address address) {
        return new Address.Builder()
                .setCity(address.getCity())
                .setCountry(address.getCountry())
                .setIndex(address.getIndex())
                .setRegion(address.getRegion())
                .setState(address.getState())
                .setStreet(address.getStreet())
                .build();
    }

    private static FullName fromDto(OrderDTO.Shipment.Recipient recipient) {
        return new FullName(recipient.getFirstName(), recipient.getLastName());
    }

    private static Collection<OrderItem> fromDto(Collection<OrderDTO.Item> items) {

        final Collection<OrderItem> result = new ArrayList<>(items.size());

        for (final OrderDTO.Item toConvert : items) {
            result.add(new OrderItem(
                    toConvert.getProduct(),
                    toConvert.getSeller(),
                    new Price(toConvert.getCurrency(), toConvert.getAmount()),
                    toConvert.getQuantity()));
        }

        return result;
    }

    static OrderEntity toEntity(@NotNull OrderDTO dto) {

        return new OrderEntity.Builder()
                .setCustomer(new CustomerID(dto.getCustomer()))
                .setOrderItems(fromDto(dto.getItems()))
                .setCreationDate(DateTime.now())// should be set by db
                .setOrderStatus(OrderStatus.CREATED)
                .setShipment(new Shipment.Builder()
                        .setDeliveryService(dto.getShipment().getDeliveryService())
                        // find better place for this code
                        .setDeliveryDate(DateTime.now().plusMonths(1))
                        .setPrice(new Price(dto.getShipment().getCurrency(), dto.getShipment().getAmount()))
                        .setStatus(ShipmentStatus.IN_SENDER_WAREHOUSE)
                        .setShipmentDetails(new ShipmentDetails.Builder()
                                .setAddress(fromDto(dto.getShipment().getAddress()))
                                .setFullName(fromDto(dto.getShipment().getRecipient()))
                                .setPhone(dto.getShipment().getRecipient().getPhone())
                                .build())
                        .build())
                .build();
    }

    static OrderDTO toDto(@NotNull Order order) {
        return MAPPER.map(order, OrderDTO.class);
    }

}

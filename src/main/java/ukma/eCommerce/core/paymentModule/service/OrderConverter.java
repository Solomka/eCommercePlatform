package ukma.eCommerce.core.paymentModule.service;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.*;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.InOrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OutOrderDTO;
import ukma.eCommerce.core.userModule.model.domain.vo.Address;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.model.domain.vo.FullName;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Максим on 11/6/2016.
 */
final class OrderConverter {

    private static final ModelMapper MAPPER = new ModelMapper();

    private OrderConverter() {
        throw new RuntimeException();
    }

    private static Address fromDto(InOrderDTO.Shipment.Address address) {
        return new Address.Builder()
                .setCity(address.getCity())
                .setCountry(address.getCountry())
                .setIndex(address.getIndex())
                .setRegion(address.getRegion())
                .setState(address.getState())
                .setStreet(address.getStreet())
                .build();
    }

    private static FullName fromDto(InOrderDTO.Shipment.Recipient recipient) {
        return new FullName(recipient.getFirstName(), recipient.getLastName());
    }

    private static Collection<OrderItem> fromDto(Collection<InOrderDTO.Item> items) {

        final Collection<OrderItem> result = new ArrayList<>(items.size());

        for (final InOrderDTO.Item toConvert : items) {
            result.add(new OrderItem(
                    toConvert.getProduct(),
                    toConvert.getSeller(),
                    new Price(toConvert.getCurrency(), toConvert.getAmount()),
                    toConvert.getQuantity()));
        }

        return result;
    }

    static OrderSaveDTO toEntity(@NotNull InOrderDTO dto) {

        return new OrderSaveDTO.Builder()
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

    static OutOrderDTO toDto(@NotNull Order order) {
        return MAPPER.map(order, OutOrderDTO.class);
    }

}

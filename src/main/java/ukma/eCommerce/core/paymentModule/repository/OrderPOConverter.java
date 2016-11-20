package ukma.eCommerce.core.paymentModule.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ShipmentDetails;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.AddressPO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderItemPO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.core.paymentModule.repository.po.ShipmentPO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.core.userModule.repository.po.Address;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;

/**
 * class for Repository OrderPO and Business Logic Order mapping
 * 
 * created on 11/09/16
 * 
 * @author Solomka
 *
 */
final class OrderPOConverter {

	private OrderPOConverter() {
		throw new RuntimeException();
	}

	/**
	 * convert from OrderSaveDTO to OrderPO
	 * 
	 * @param orderEntity
	 * @return
	 */
	static OrderPO fromOrderSaveDTO(@NotNull OrderSaveDTO orderSaveDTO) {
		// CustomerPO
		CustomerPO customerPO = new CustomerPO();
		customerPO.setId(orderSaveDTO.getCustomer().getId());

		// Address
		Address address = new Address();
		address.setCountry(orderSaveDTO.getShipment().getShipmentDetails().getAddress().getCountry());
		address.setState(orderSaveDTO.getShipment().getShipmentDetails().getAddress().getState());
		address.setRegion(orderSaveDTO.getShipment().getShipmentDetails().getAddress().getRegion());
		address.setCity(orderSaveDTO.getShipment().getShipmentDetails().getAddress().getCity());
		address.setStreet(orderSaveDTO.getShipment().getShipmentDetails().getAddress().getStreet());
		address.setIndex(orderSaveDTO.getShipment().getShipmentDetails().getAddress().getIndex());

		// FullName
		FullName fullName = new FullName();
		fullName.setFirstName(orderSaveDTO.getShipment().getShipmentDetails().getFullName().getFirstName());
		fullName.setLastName(orderSaveDTO.getShipment().getShipmentDetails().getFullName().getLastName());

		// AddressPO
		AddressPO addressPO = new AddressPO();
		addressPO.setPhone(orderSaveDTO.getShipment().getShipmentDetails().getPhone());
		addressPO.setCustomer(customerPO);
		addressPO.setAddress(address);
		addressPO.setFullName(fullName);

		// Price
		ukma.eCommerce.core.paymentModule.repository.po.Price price = new ukma.eCommerce.core.paymentModule.repository.po.Price();
		price.setCurrency(orderSaveDTO.getShipment().getPrice().getCurrency());
		price.setPrice(orderSaveDTO.getShipment().getPrice().getAmount());

		// ShipmentPO
		ShipmentPO shipmentPO = new ShipmentPO();
		shipmentPO.setDeliveryService(orderSaveDTO.getShipment().getDeliveryService());
		shipmentPO.setPrice(price);
		shipmentPO.setDeliveryDate(orderSaveDTO.getShipment().getDeliveryDate());
		shipmentPO.setStatus(orderSaveDTO.getShipment().getStatus());
		shipmentPO.setAddress(addressPO);

		// OrderItemsPO
		List<OrderItemPO> orderItemsPO = new ArrayList<OrderItemPO>();

		for (OrderItem orderItem : orderSaveDTO.getOrderItems()) {

			OrderItemPO orderItemPO = new OrderItemPO();
			// ProductPO productPO = new ProductPO();

			// productPO.setId(orderItem.getProduct().getId());

			orderItemPO.setQuantity(orderItem.getQuantity());
			orderItemPO.setTotalSum(orderItem.getPrice().getAmount());
			orderItemsPO.add(orderItemPO);

		}

		// OrderPO
		OrderPO orderPO = new OrderPO();
		orderPO.setCreationDate(orderSaveDTO.getCreationDate());
		orderPO.setFulfilmentDate(orderSaveDTO.getFulfilmentDate());
		orderPO.setStatus(orderSaveDTO.getStatus());
		orderPO.setCustomer(customerPO);
		orderPO.setShipment(shipmentPO);
		orderPO.setOrderItems(orderItemsPO);

		return orderPO;
	}

	/**
	 * convert from Order to OrderPO
	 * 
	 * @param order
	 * @return
	 */

	static OrderPO fromOrder(@NotNull Order order) {
		// CustomerPO
		CustomerPO customerPO = new CustomerPO();
		customerPO.setId(order.getCustomer().getId());

		// Address
		Address address = new Address();
		address.setCountry(order.getShipment().getShipmentDetails().getAddress().getCountry());
		address.setState(order.getShipment().getShipmentDetails().getAddress().getState());
		address.setRegion(order.getShipment().getShipmentDetails().getAddress().getRegion());
		address.setCity(order.getShipment().getShipmentDetails().getAddress().getCity());
		address.setStreet(order.getShipment().getShipmentDetails().getAddress().getStreet());
		address.setIndex(order.getShipment().getShipmentDetails().getAddress().getIndex());

		// FullName
		FullName fullName = new FullName();
		fullName.setFirstName(order.getShipment().getShipmentDetails().getFullName().getFirstName());
		fullName.setLastName(order.getShipment().getShipmentDetails().getFullName().getLastName());

		// AddressPO
		AddressPO addressPO = new AddressPO();
		addressPO.setPhone(order.getShipment().getShipmentDetails().getPhone());
		addressPO.setCustomer(customerPO);
		addressPO.setAddress(address);
		addressPO.setFullName(fullName);

		// Price
		ukma.eCommerce.core.paymentModule.repository.po.Price price = new ukma.eCommerce.core.paymentModule.repository.po.Price();
		price.setCurrency(order.getShipment().getPrice().getCurrency());
		price.setPrice(order.getShipment().getPrice().getAmount());

		// ShipmentPO
		ShipmentPO shipmentPO = new ShipmentPO();
		shipmentPO.setDeliveryService(order.getShipment().getDeliveryService());
		shipmentPO.setPrice(price);
		shipmentPO.setDeliveryDate(order.getShipment().getDeliveryDate());
		shipmentPO.setStatus(order.getShipment().getStatus());
		shipmentPO.setAddress(addressPO);

		// OrderItemsPO
		List<OrderItemPO> orderItemsPO = new ArrayList<OrderItemPO>();

		for (OrderItem orderItem : order.getOrderItems()) {

			OrderItemPO orderItemPO = new OrderItemPO();
			// ProductPO productPO = new ProductPO();

			// productPO.setId(orderItem.getProduct().getId());

			orderItemPO.setQuantity(orderItem.getQuantity());
			orderItemPO.setTotalSum(orderItem.getPrice().getAmount());
			orderItemsPO.add(orderItemPO);

		}

		// OrderPO
		OrderPO orderPO = new OrderPO();
		orderPO.setId(order.getId().getId());
		orderPO.setCreationDate(order.getCreationDate());
		orderPO.setFulfilmentDate(order.getFulfilmentDate());
		orderPO.setStatus(order.getStatus());
		orderPO.setCustomer(customerPO);
		orderPO.setShipment(shipmentPO);
		orderPO.setOrderItems(orderItemsPO);

		return orderPO;
	}

	/**
	 * convert from OrderPO to Order
	 * 
	 * @param orderPO
	 * @return
	 */
	static Order toOrder(@NotNull OrderPO orderPO) {

		OrderID orderID = new OrderID(orderPO.getId());
		CustomerID customerId = new CustomerID(orderPO.getCustomer().getId());

		Price price = new Price(orderPO.getShipment().getPrice().getCurrency(),
				orderPO.getShipment().getPrice().getPrice());

		ukma.eCommerce.core.userModule.model.domain.vo.FullName fullName = new ukma.eCommerce.core.userModule.model.domain.vo.FullName(
				orderPO.getShipment().getAddress().getFullName().getFirstName(),
				orderPO.getShipment().getAddress().getFullName().getLastName());
		ukma.eCommerce.core.userModule.model.domain.vo.Address address = new ukma.eCommerce.core.userModule.model.domain.vo.Address.Builder()
				.setCountry(orderPO.getShipment().getAddress().getAddress().getCountry())
				.setState(orderPO.getShipment().getAddress().getAddress().getState())
				.setRegion(orderPO.getShipment().getAddress().getAddress().getRegion())
				.setCity(orderPO.getShipment().getAddress().getAddress().getCity())
				.setStreet(orderPO.getShipment().getAddress().getAddress().getStreet())
				.setIndex(orderPO.getShipment().getAddress().getAddress().getIndex()).build();

		ShipmentDetails shipmentDetails = new ShipmentDetails.Builder()
				.setPhone(orderPO.getShipment().getAddress().getPhone()).setFullName(fullName).setAddress(address)
				.build();

		Shipment shipment = new Shipment.Builder().setDeliveryDate(orderPO.getShipment().getDeliveryDate())
				.setDeliveryService(orderPO.getShipment().getDeliveryService()).setPrice(price)
				.setShipmentDetails(shipmentDetails).setStatus(orderPO.getShipment().getStatus()).build();

		List<OrderItemPO> orderItemsPO = orderPO.getOrderItems();
		Collection<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (OrderItemPO orderItemPO : orderItemsPO) {
			ProductID productID = new ProductID(orderItemPO.getProduct().getId());
			SellerID sellerID = new SellerID(orderItemPO.getProduct().getSeller().getId());
			OrderItem orderItem = new OrderItem(productID, sellerID,
					new Price(orderItemPO.getProduct().getPrice().getCurrency(), orderItemPO.getTotalSum()),
					orderItemPO.getQuantity());
			orderItems.add(orderItem);
		}

		return new Order.Builder().setId(orderID).setCreationDate(orderPO.getCreationDate())
				.setFulfilmentDate(orderPO.getFulfilmentDate()).setOrderStatus(orderPO.getStatus())
				.setCustomer(customerId).setShipment(shipment).setOrderItems(orderItems).build();
	}

}

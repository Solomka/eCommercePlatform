package ukma.eCommerce.core.paymentModule.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ShipmentDetails;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.AddressPO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderItemID;
import ukma.eCommerce.core.paymentModule.repository.po.OrderItemPO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.core.paymentModule.repository.po.Price;
import ukma.eCommerce.core.paymentModule.repository.po.ProductPO;
import ukma.eCommerce.core.paymentModule.repository.po.ShipmentPO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.core.userModule.repository.po.Address;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;

/**
 * class for converting from Business Logic Order/OrderSaveDTO mapping into
 * Repository OrderPO and vice versa
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
	 * generate CustomerPO field of OrderPO
	 * 
	 * @param customerId
	 * @return
	 */
	private static CustomerPO generateCustomerPO(CustomerID customerId) {
		return new CustomerPO(customerId.getId());
	}

	private static Address generatePOAddress(ukma.eCommerce.core.userModule.model.domain.vo.Address addressVO) {
		return new Address.Builder().setCity(addressVO.getCity()).setCountry(addressVO.getCountry())
				.setIndex(addressVO.getIndex()).setRegion(addressVO.getRegion()).setState(addressVO.getState())
				.setStreet(addressVO.getStreet()).build();
	}

	private static FullName generatePOFullName(ukma.eCommerce.core.userModule.model.domain.vo.FullName fullNameVO) {
		return new FullName(fullNameVO.getFirstName(), fullNameVO.getLastName());

	}

	/**
	 * generate AddressPO field of ShipmentPO
	 * 
	 * @param customerId
	 * @param shipmentDetails
	 * @return
	 */
	private static AddressPO generateAddressPO(CustomerID customerId, ShipmentDetails shipmentDetails) {
		return new AddressPO.Builder().setPhone(shipmentDetails.getPhone()).setCustomer(generateCustomerPO(customerId))
				.setAddress(generatePOAddress(shipmentDetails.getAddress()))
				.setFullName(generatePOFullName(shipmentDetails.getFullName())).build();

	}

	private static Price generatePOPrice(ukma.eCommerce.core.paymentModule.model.domain.vo.Price priceVO) {
		return new Price(priceVO.getAmount(), priceVO.getCurrency());

	}

	/**
	 * generate ShipmentPO field of OrderPO
	 * 
	 * @param customerId
	 * @param shipmentVO
	 * @return
	 */
	private static ShipmentPO generateShipmentPO(CustomerID customerId, Shipment shipmentVO) {
		return new ShipmentPO.Builder().setDeliveryService(shipmentVO.getDeliveryService())
				.setPrice(generatePOPrice(shipmentVO.getPrice())).setDeliveryDate(shipmentVO.getDeliveryDate())
				.setStatus(shipmentVO.getStatus())
				.setAddress(generateAddressPO(customerId, shipmentVO.getShipmentDetails())).build();
	}

	/**
	 * generate orderItems List of OrderPO
	 * 
	 * @param orderItems
	 * @return
	 */
	private static List<OrderItemPO> generateOrderItemPOList(Collection<OrderItem> orderItems, OrderSaveDTO orderSaveDTO) {

		final List<OrderItemPO> orderItemsPO = new ArrayList<OrderItemPO>(orderItems.size());

		for (OrderItem orderItem : orderItems) {						
			OrderItemPO orderItemPO = new OrderItemPO(orderItem.getQuantity(), orderItem.getPrice().getAmount());
			orderItemPO.setProduct(new ProductPO(orderItem.getProduct().getId()));
			orderItemPO.setOrder(fromOrderSaveDTONoItems(orderSaveDTO));
			orderItemsPO.add(orderItemPO);
		}
		return orderItemsPO;
	}
	

	private static OrderPO fromOrderSaveDTONoItems( OrderSaveDTO orderSaveDTO) {
		return new OrderPO.Builder().setCreationDate(orderSaveDTO.getCreationDate())
				.setFulfilmentDate(orderSaveDTO.getFulfilmentDate()).setStatus(orderSaveDTO.getStatus())
				.setCustomer(generateCustomerPO(orderSaveDTO.getCustomer()))
				.setShipment(generateShipmentPO(orderSaveDTO.getCustomer(), orderSaveDTO.getShipment())).build();
		
	}
	
	

	/**
	 * convert from OrderSaveDTO to OrderPO
	 * 
	 * @param orderEntity
	 * @return
	 */
	/*
	@NotNull
	static OrderPO fromOrderSaveDTO(@NotNull OrderSaveDTO orderSaveDTO) {
		return new OrderPO.Builder().setCreationDate(orderSaveDTO.getCreationDate())
				.setFulfilmentDate(orderSaveDTO.getFulfilmentDate()).setStatus(orderSaveDTO.getStatus())
				.setCustomer(generateCustomerPO(orderSaveDTO.getCustomer()))
				.setShipment(generateShipmentPO(orderSaveDTO.getCustomer(), orderSaveDTO.getShipment()))
				.setOrderItems(generateOrderItemPOList(orderSaveDTO.getOrderItems(), orderSaveDTO)).build();
	}
	*/
	
	static OrderPO fromOrderSaveDTO(@NotNull OrderSaveDTO orderSaveDTO) {
		OrderPO order = new OrderPO.Builder().setCreationDate(orderSaveDTO.getCreationDate())
				.setFulfilmentDate(orderSaveDTO.getFulfilmentDate()).setStatus(orderSaveDTO.getStatus())
				.setCustomer(generateCustomerPO(orderSaveDTO.getCustomer()))
				.setShipment(generateShipmentPO(orderSaveDTO.getCustomer(), orderSaveDTO.getShipment()))
				/*.setOrderItems(generateOrderItemPOList(orderSaveDTO.getOrderItems(), orderSaveDTO))*/.build();
		
		
		for (OrderItem orderItem : orderSaveDTO.getOrderItems()) {						
			OrderItemPO orderItemPO = new OrderItemPO(orderItem.getQuantity(), orderItem.getPrice().getAmount());
			orderItemPO.setProduct(new ProductPO(orderItem.getProduct().getId()));
			System.out.println("OrderItemPOOOOOOOOOOOO1: " + orderItemPO);
			order.addToOrderItemPO(orderItemPO);
			System.out.println("OrderItemPOOOOOOOOOOOO2: " + orderItemPO);
					}
				
		//System.out.println("ORDERPOOOOOOOOOOO : "+ order.toString());
		 return order;
	}
	
	

	/**
	 * convert from Order to OrderPO
	 * 
	 * @param order
	 * @return
	 */
	/*
	@NotNull
	static OrderPO fromOrder(@NotNull Order order) {
		return new OrderPO.Builder().setId(order.getId().getId()).setCreationDate(order.getCreationDate())
				.setFulfilmentDate(order.getFulfilmentDate()).setStatus(order.getStatus())
				.setCustomer(generateCustomerPO(order.getCustomer()))
				.setShipment(generateShipmentPO(order.getCustomer(), order.getShipment()))
				.setOrderItems(generateOrderItemPOList(order.getOrderItems(), order)).build();
	}*/
	
	@NotNull
	static OrderPO fromOrder(@NotNull Order order) {
		return null;
		
	}

	private static ukma.eCommerce.core.paymentModule.model.domain.vo.Price generateVOPrice(Price price) {
		return new ukma.eCommerce.core.paymentModule.model.domain.vo.Price(price.getCurrency(), price.getPrice());
	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.FullName generateVOFullName(FullName fullName) {
		return new ukma.eCommerce.core.userModule.model.domain.vo.FullName(fullName.getFirstName(),
				fullName.getLastName());
	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.Address generateVOAddress(Address address) {
		return new ukma.eCommerce.core.userModule.model.domain.vo.Address.Builder().setCountry(address.getCountry())
				.setState(address.getState()).setRegion(address.getRegion()).setCity(address.getCity())
				.setStreet(address.getStreet()).setIndex(address.getIndex()).build();
	}

	private static ShipmentDetails generateShipmentDetails(AddressPO addressPO) {
		return new ShipmentDetails.Builder().setPhone(addressPO.getPhone())
				.setFullName(generateVOFullName(addressPO.getFullName()))
				.setAddress(generateVOAddress(addressPO.getAddress())).build();
	}

	private static Shipment generateShipment(ShipmentPO shipmentPO) {
		return new Shipment.Builder().setDeliveryDate(shipmentPO.getDeliveryDate())
				.setDeliveryService(shipmentPO.getDeliveryService()).setPrice(generateVOPrice(shipmentPO.getPrice()))
				.setShipmentDetails(generateShipmentDetails(shipmentPO.getAddress())).setStatus(shipmentPO.getStatus())
				.build();

	}
/**
 * TODO: solve it somehow, stupid shit ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((
 * @param orderItemPOs
 * @return
 */
	private static Collection<OrderItem> generateOrderItemVOList(List<OrderItemPO> orderItemPOs) {

		final Collection<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (OrderItemPO orderItemPO : orderItemPOs) {
			OrderItem orderItem = new OrderItem(new ProductID(orderItemPO.getProduct().getId()),
					//new SellerID(orderItemPO.getProduct().getSeller().getId()) ahhhhhhhhhahhahhahhah,
					new SellerID(UUID.fromString("393fdccd-b4f8-11e6-af21-db5929c35768")),
					//generateVOPrice(orderItemPO.getProduct().getPrice()), orderItemPO.getQuantity());
					new ukma.eCommerce.core.paymentModule.model.domain.vo.Price(Currency.USD, BigDecimal.TEN) , 1);
			orderItems.add(orderItem);
		}
		return orderItems;
	}
	

	/**
	 * convert from OrderPO to Order
	 * 
	 * @param orderPO
	 * @return
	 */
	@NotNull
	static Order toOrder(@NotNull OrderPO orderPO) {
		return new Order.Builder().setId(new OrderID(orderPO.getId())).setCreationDate(orderPO.getCreationDate())
				.setFulfilmentDate(orderPO.getFulfilmentDate()).setOrderStatus(orderPO.getStatus())
				.setCustomer(new CustomerID(orderPO.getCustomer().getId()))
				.setShipment(generateShipment(orderPO.getShipment()))
				.setOrderItems(generateOrderItemVOList(orderPO.getOrderItems())).build();
	}

}

package order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ShipmentDetails;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.service.filter.order.OrderServiceFilterUtils;
import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.core.userModule.model.domain.vo.Address;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.model.domain.vo.FullName;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.core.userModule.service.filter.CustomerServiceFilterUtils;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class OrderRepositoryTest {

	private static final Logger LOGGER = Logger.getLogger(OrderRepositoryTest.class.getName());

	private static IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> orderRepository;

	private OrderSaveDTO orderSaveDTO;

	@BeforeClass
	public static void prepare() {
		BasicConfigurator.configure(new ConsoleAppender(new SimpleLayout()));
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml");
		orderRepository = ctx.getBean("orderRepository", IRepository.class);
	}

	@Before
	public void before() {
		orderSaveDTO = generateOrderSaveDTO();
		System.out.println("OrderSaveDTO: " + orderSaveDTO);
	}
/*
	@Test
	public void findOrder() {
		List<Order> orders = (List<Order>) orderRepository
				.find(OrderServiceFilterUtils.getOrderById(new OrderID("1efc4ceb-3dba-4f04-a468-32dc1cc118ba")));
		System.out.println("Found order: " + orders.get(0));
		assertNotNull(orders.get(0));

	}
*/
	
	
	@Test
	public void createOrder() {
		Order order = orderRepository.create(orderSaveDTO);
		System.out.println("Created order: " + order);
		assertNotNull(order.getId().getId());

	}
	
	/*
	@Test
	public void deleteOrder() {
		orderRepository.delete(new OrderID("06e0fcee-31ad-4c02-88e5-bc3abc8f200f"));
		Collection<Order> orders = orderRepository
				.find(OrderServiceFilterUtils.getOrderById(new OrderID("06e0fcee-31ad-4c02-88e5-bc3abc8f200f")));
		assertEquals(0, orders.size());

	}*/
	
/*
	@Test
	public void updateOrder() {

		List<Order> orders = (List<Order>) orderRepository
				.find(OrderServiceFilterUtils.getOrderById(new OrderID("1efc4ceb-3dba-4f04-a468-32dc1cc118ba")));
		Order newOrder = orders.get(0);
		System.out.println("Customer to update: "+ newOrder);
		// newOrder.changeFullName(generateNewVOFullName());
		//assertNotNull(orderRepository.update(newOrder));

	}
*/
	

	public static OrderSaveDTO generateOrderSaveDTO() {
		return new OrderSaveDTO.Builder().setCustomer(new CustomerID("1c9a0551-a537-44af-be6e-3b775f44b162"))
				.setCreationDate(DateTime.now()).setShipment(generateShipmentVO()).setOrderItems(generateOrderItems()).setOrderStatus(OrderStatus.CREATED).build();
	}	
	
	private static Shipment generateShipmentVO(){		
		return new Shipment.Builder().setPrice(new Price(Currency.USD, BigDecimal.ZERO)).setShipmentDetails(generateShipmentDetails())
				.setStatus(ShipmentStatus.IN_SENDER_WAREHOUSE).build();	
	}
	
	private static ShipmentDetails generateShipmentDetails(){		
		return new ShipmentDetails.Builder().setAddress(generateAddress()).setFullName(generateFullName()).setPhone("+380968971663").build();		
	}
	
	private static Address generateAddress(){
		return new Address.Builder().setCountry("Ukraine").setCity("Kiev").setStreet("Skovorody 12 str.").setIndex("12345").build();
	}
	
	private static FullName generateFullName(){
		return new FullName("Sasha", "Chaban");
	}	   
	
	private static List<OrderItem> generateOrderItems(){
		final List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem item1 = new OrderItem(new ProductID("2cb84cf7-b4f9-11e6-af21-db5929c35768"), new SellerID("393fdccd-b4f8-11e6-af21-db5929c35768"),new Price(Currency.USD, BigDecimal.TEN) , 1);
		OrderItem item2 = new OrderItem(new ProductID("2cb85046-b4f9-11e6-af21-db5929c35768"), new SellerID("393fdccd-b4f8-11e6-af21-db5929c35768"),new Price(Currency.USD, BigDecimal.TEN) , 1);
		OrderItem item3 = new OrderItem(new ProductID("e995c3bc-b4f8-11e6-af21-db5929c35768"), new SellerID("393fdccd-b4f8-11e6-af21-db5929c35768"),new Price(Currency.USD, BigDecimal.TEN) , 1);
		orderItems.add(item1);
		orderItems.add(item2);
		orderItems.add(item3);
		return orderItems;
		
		
	}

}

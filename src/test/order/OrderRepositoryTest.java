package order;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.repository.OrderRepository;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest {

	private static OrderRepository orderRepository;
	
	private Order order;
	private OrderPO orderPO;

	@BeforeClass
	public static void prepare() {
		//BasicConfigurator.configure(new ConsoleAppender(new SimpleLayout()));
		final ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring_test_context.xml");
		orderRepository = ctx.getBean("orderRepository", OrderRepository.class);
	}
	
	@Before
	public void before(){
		this.order = generateOrder();
	}
	
	
	private static Order generateOrder(){
		return null;
	}
	

}

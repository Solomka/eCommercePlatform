package order;

import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import rx.Observable;
import ukma.eCommerce.core.userModule.repository.po.Credentials;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;

@DirtiesContext
@RunWith(Parameterized.class)
public class CustomerRepositoryTest {

	private static final Logger LOGGER = Logger.getLogger(CustomerRepositoryTest.class.getName());
	private final CustomerPO customerPO;

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void prepare() {
		//BasicConfigurator.configure(new ConsoleAppender(new SimpleLayout()));
		final ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring_test_context.xml");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
	}

	public CustomerRepositoryTest(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public UUID save(CustomerPO entity) {
		return (UUID) getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	public CustomerPO get(UUID key) {
		return (CustomerPO) getSession().get(CustomerPO.class, key);
	}
	
	public Observable<CustomerPO> saveCustomerPO(CustomerPO orderPO) {
		return Observable.just(save(orderPO)).map(uuid -> get(uuid));
	}

	@Test
	public void testSaveCustomerPO() {

		try {
			saveCustomerPO(customerPO).subscribe(
					result -> LOGGER.log(Level.INFO, String.format("service result %s", result)),
					th -> Assert.fail(String.format("unexpected exception: %s", th.toString())));
		} catch (final ConstraintViolationException e) {
			Assert.fail(String.format("unexpected constraint violation: %s", e.getConstraintViolations().toString()));
		}
	}

	@Parameterized.Parameters
	public static Object[] data() {
		return new Object[] { generateCustomerPO() };
	}

	public static CustomerPO generateCustomerPO() {

		CustomerPO customerPO = new CustomerPO();
		// customerPO.setAddress(generateAddress());
		customerPO.setCredentials(generateCredentials());
		customerPO.setFullName(generateFullName());

		return customerPO;

	}
	/*
	 * private static Address generateAddress(){
	 * 
	 * final Address address = new Address(); address.setCountry("Ukraine");
	 * address.setCity("Lviv"); address.setStreet("Skovorody 12 str.");
	 * address.setIndex("11234");
	 * 
	 * return address; }
	 */

	private static Credentials generateCredentials() {

		final Credentials credentials = new Credentials();
		credentials.setEmail("bla@gmail.com");
		credentials.setLogin("Solomka");
		credentials.setPassword("12345678");
		credentials.setPhone("098457812");

		return credentials;
	}

	private static FullName generateFullName() {
		final FullName fullName = new FullName();
		fullName.setFirstName("Solomka");
		fullName.setLastName("Yaremko");
		return fullName;
	}

}

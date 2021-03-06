package customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.core.userModule.model.domain.dwo.CustomerSaveDTO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.repository.po.Credentials;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;
import ukma.eCommerce.core.userModule.service.filter.CustomerServiceFilterUtils;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
// @EnableTransactionManagement
public class CustomerRepositoryTest {

	private static final Logger LOGGER = Logger.getLogger(CustomerRepositoryTest.class.getName());

	// @Autowired
	// @Qualifier("customerRepository")
	private static IRepository<Customer, CustomerID, CustomerSaveDTO, IExposedFilter> customerRepository;

	private CustomerSaveDTO customerSaveDTO;
	// private CustomerPO customerPO;

	@BeforeClass
	public static void prepare() {
		BasicConfigurator.configure(new ConsoleAppender(new SimpleLayout()));
		// final ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("classpath:dispatcher-servlet.xml");
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml");
		customerRepository = ctx.getBean("customerRepository", IRepository.class);
	}

	@Before
	public void before() {
		// this.customerPO = generateCustomerPO();
		customerSaveDTO = generateCustomerSaveDTO();

		System.out.println("customerSaveDTO: " + customerSaveDTO);
	}

	@Test
	public void findCustomer() {
		List<Customer> customers = (List<Customer>) customerRepository.find(
				CustomerServiceFilterUtils.getCustomerById(new CustomerID("b0dcb481-16bb-4e4b-9700-78d182e827ac")));
		System.out.println("Found customer: " + customers.get(0));
		assertNotNull(customers.get(0));
	}

	@Test
	public void createCustomer() {
		Customer customer = customerRepository.create(customerSaveDTO);
		System.out.println("Created customer: " + customer);
		assertNotNull(customer.getId().getId());
	}

	@Test
	public void updateCustomer() {

		List<Customer> oldCustomer = (ArrayList<Customer>) customerRepository.find(
				CustomerServiceFilterUtils.getCustomerById(new CustomerID("b0dcb481-16bb-4e4b-9700-78d182e827ac")));
		Customer newCustomer = oldCustomer.get(0);
		// System.out.println("Customer to update: "+ newCustomer);
		newCustomer.changeFullName(generateNewVOFullName());
		assertNotNull(customerRepository.update(newCustomer));
	}

	/*
	 * @Test public void saveCustomerPO() {
	 * customerRepository.create(customerSaveDTO).subscribe(result -> {
	 * System.out.println("Result: " + result);
	 * assertNotNull(result.getId().getId()); } , th ->
	 * System.out.println("th")); }
	 */

	@Test
	public void deleteCustomer() {
		customerRepository.delete(new CustomerID("766d7567-05a1-4385-90f4-1f384e4d890d"));
		Collection<Customer> customers = customerRepository.find(
				CustomerServiceFilterUtils.getCustomerById(new CustomerID("766d7567-05a1-4385-90f4-1f384e4d890d")));
		assertEquals(0, customers.size());

	}

	@Test
	public void deleteCustomerFail() {
		try {
			customerRepository.delete(new CustomerID("766d7567-05a1-4385-90f4-1f384e4d890d"));
		} catch (Exception ex) {
			fail("No customer with such id \n message: " + ex.getMessage());
		}

	}

	public static CustomerSaveDTO generateCustomerSaveDTO() {
		return new CustomerSaveDTO.Builder().setCredentials(generateVOCredentials()).setFullName(generateVOFullName())
				.build();
	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.Credentials generateVOCredentials() {
		return new ukma.eCommerce.core.userModule.model.domain.vo.Credentials("bla@gmail.com", "+380986746583",
				"solomka123", "password123");

	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.FullName generateVOFullName() {
		return new ukma.eCommerce.core.userModule.model.domain.vo.FullName("Larisa", "Yaremko");

	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.FullName generateNewVOFullName() {
		return new ukma.eCommerce.core.userModule.model.domain.vo.FullName("Sona", "Yaremko");

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

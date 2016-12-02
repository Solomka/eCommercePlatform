package order;

import org.apache.log4j.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.dwo.InOrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OutOrderDTO;
import ukma.eCommerce.core.paymentModule.service.IOrderApplicationService;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import util.RunIf;
import util.RunIfRule;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * <p>
 * Tests {@linkplain IOrderApplicationService}
 * </p>
 * Created by Максим on 11/8/2016.
 */
@RunWith(Parameterized.class)
public final class OrderServiceTest {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceTest.class.getName());

    private static IOrderApplicationService orderApplicationService;
    private final InOrderDTO testDto;

    @Rule
    public final RunIfRule runIfRule;

    private final boolean isValid;

    @BeforeClass
    public static void prepare() {
        BasicConfigurator.configure(new ConsoleAppender(new SimpleLayout()));
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml");
        orderApplicationService = ctx.getBean(IOrderApplicationService.class);
    }

    public OrderServiceTest(InOrderDTO testDto, boolean isValid) {
        this.runIfRule = new RunIfRule(isValid);
        this.testDto = testDto;
        this.isValid = isValid;
    }

    @Test(expected = Exception.class)
    public void testNullArg() {
        orderApplicationService.createOrder(null);
    }

    OrderServiceTest getRef() {
        return this;
    }

    @Test
    @RunIf(condition = true)
    public void testDtoArgValid() {

        final Object sync = new Object();

        try {
            Assume.assumeTrue(isValid);

            final Action1<OutOrderDTO> resultOk = result -> {
                sync.notify();
                LOGGER.log(Level.INFO, String.format("service result %s", result));
            };

            final Action1<Throwable> resultErr = th -> {
             //   sync.notify();
                Assert.fail(String.format("unexpected exception: %s", th.toString()));
            };

            orderApplicationService.createOrder(testDto).subscribeOn(Schedulers.immediate()).subscribe(resultOk, resultErr);

            synchronized (sync) {
                try {
                    sync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (final ConstraintViolationException e) {
            Assert.fail(String.format("unexpected constraint violation: %s", e.getConstraintViolations().toString()));
        }
    }

    @Test(expected = ConstraintViolationException.class)
    @RunIf(condition = false)
    public void testDtoArgInvalidCustomer() {
        orderApplicationService.createOrder(testDto);
        Assert.fail("Shouldn't pass");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> provideTestData() {

        final InOrderDTO dto1 = generateDtoStub();
        dto1.setCustomer(null);

        final InOrderDTO dto2 = generateDtoStub();
        dto2.setShipment(null);

        return Arrays.asList(
                new Object[]{dto1, false},
                new Object[]{generateDtoStub(), true},
                new Object[]{dto2, false}
        );
    }

    public static InOrderDTO.Item generateItemStub() {

        final InOrderDTO.Item item = new InOrderDTO.Item();

        item.setProduct(new ProductID("2cb84cf7-b4f9-11e6-af21-db5929c35768"));
        item.setCurrency(Currency.USD);
        item.setQuantity(1);
        item.setAmount(new BigDecimal("1.4"));
        item.setSeller(new SellerID("393fdccd-b4f8-11e6-af21-db5929c35768"));

        return item;
    }

    public static InOrderDTO generateDtoStub() {

        final InOrderDTO dto = new InOrderDTO();
        final InOrderDTO.Shipment shipment = new InOrderDTO.Shipment();
        final InOrderDTO.Shipment.Address address = new InOrderDTO.Shipment.Address();
        final InOrderDTO.Shipment.Recipient recipient = new InOrderDTO.Shipment.Recipient();

        dto.setCustomer("1c9a0551-a537-44af-be6e-3b775f44b162");

        recipient.setFirstName("Max");
        recipient.setLastName("Oliynick");
        recipient.setPhone("+380993231243");

        address.setStreet("Kharkivske h.17");
        address.setCity("Kyiv");
        address.setIndex("342");
        address.setState(null);
        address.setRegion(null);
        address.setCountry("Ukraine");

        shipment.setAddress(address);
        shipment.setAmount(new BigDecimal("10"));
        shipment.setCurrency(Currency.forId(1));
        shipment.setDeliveryService("Nova Poshta");
        shipment.setRecipient(recipient);
        dto.setShipment(shipment);

        InOrderDTO.Item item2 = generateItemStub();

        item2.setCurrency(Currency.UAH);
        item2.setQuantity(5);
        item2.setAmount(new BigDecimal("2"));

        dto.addAll(Arrays.asList(
                generateItemStub(), item2
        ));

        return dto;
    }

}

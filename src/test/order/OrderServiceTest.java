package order;

import org.apache.log4j.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
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
    private final OrderDTO testDto;

    @Rule
    public final RunIfRule runIfRule;

    @BeforeClass
    public static void prepare() {
        BasicConfigurator.configure(new ConsoleAppender(new SimpleLayout()));
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:serviceBeans.xml");
        orderApplicationService = ctx.getBean(IOrderApplicationService.class);
    }

    public OrderServiceTest(OrderDTO testDto, boolean isValid) {
        this.runIfRule = new RunIfRule(isValid);
        this.testDto = testDto;
    }

    @Test(expected = Exception.class)
    public void testNullArg() {
        orderApplicationService.createOrder(null);
    }

    @Test
    @RunIf(condition = true)
    public void testDtoArgValid() {

        try {
            orderApplicationService.createOrder(testDto).subscribe(
                    result-> LOGGER.log(Level.INFO, String.format("service result %s", result)),
                    th -> Assert.fail(String.format("unexpected exception: %s", th.toString()))
            );
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

        final OrderDTO dto1 = generateDtoStub();
        dto1.setCustomer(null);

        final OrderDTO dto2 = generateDtoStub();
        dto2.setShipment(null);

        return Arrays.asList(
                new Object[]{dto1, false},
                new Object[]{generateDtoStub(), true},
                new Object[]{dto2, false}
        );
    }

    public static OrderDTO.Item generateItemStub() {

        final OrderDTO.Item item = new OrderDTO.Item();

        item.setProduct(new ProductID("9a7efb3c-a5c6-11e6-80f5-76304dec7eb7"));
        item.setCurrency(Currency.USD);
        item.setQuantity(1);
        item.setAmount(new BigDecimal("1.4"));
        item.setSeller(new SellerID("9a7f00aa-a5c6-11e6-80f5-76304dec7eb7"));

        return item;
    }

    public static OrderDTO generateDtoStub() {

        final OrderDTO dto = new OrderDTO();
        final OrderDTO.Shipment shipment = new OrderDTO.Shipment();
        final OrderDTO.Shipment.Address address = new OrderDTO.Shipment.Address();
        final OrderDTO.Shipment.Recipient recipient = new OrderDTO.Shipment.Recipient();

        dto.setCustomer("b6953594-a5c5-11e6-80f5-76304dec7eb7");

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

        OrderDTO.Item item2 = generateItemStub();

        item2.setCurrency(Currency.UAH);
        item2.setQuantity(5);
        item2.setAmount(new BigDecimal("2"));

        dto.addAll(Arrays.asList(
                generateItemStub(), item2
        ));

        return dto;
    }

}

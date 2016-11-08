package ukma.eCommerce.core.paymentModule.model.dwo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import ukma.eCommerce.util.validation.Phone;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * Created by Максим on 10/8/2016.
 */
@Validated
public final class OrderDTO {

    @NotEmpty
    public String customer;
    @Valid
    public Shipment shipment;

    @Validated
    public final static class Shipment {

        @Pattern(regexp = "[a-zA-z]{3,50}")
        public String deliveryService;
        @Min(0)
        public int currency;
        @NotNull
        @Min(0)
        public BigDecimal amount;
        @Valid
        public Address address;
        @Valid
        public Recipient recipient;

        @Validated
        public final static class Address {
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            public String country;
            @Pattern(regexp = "[a-zA-z]{3,50}")
            public String state;
            @Pattern(regexp = "[a-zA-z]{3,50}")
            public String region;
            @NotNull
            @Pattern(regexp = "\\w{3,5}")
            public String index;
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            public String city;
            @NotNull
            @Pattern(regexp = "[\\w\\s-]{3,50}")
            public String street;
        }

        @Validated
        public final static class Recipient {
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            public String firstName;
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            public String lastName;
            @NotEmpty
            @Phone
            public String phone;
        }

    }

}

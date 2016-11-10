package ukma.eCommerce.core.paymentModule.model.dwo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.util.validation.Phone;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Максим on 10/8/2016.
 */
@Validated
public final class InOrderDTO {

    @NotEmpty
    private String customer;
    @Valid
    @NotNull
    private Shipment shipment;
    @Valid
    @NotNull
    private Collection<Item> items = new ArrayList<>(0);

    @Validated
    public static final class Item {

        @Valid
        @NotNull
        private ProductID product;
        @Valid
        @NotNull
        private SellerID seller;
        @NotNull
        private Currency currency;
        @NotNull
        @Min(0)
        private BigDecimal amount;
        @Min(1)
        private int quantity;

        public ProductID getProduct() {
            return product;
        }

        public void setProduct(ProductID product) {
            this.product = product;
        }

        public SellerID getSeller() {
            return seller;
        }

        public void setSeller(SellerID seller) {
            this.seller = seller;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "product=" + product +
                    ", seller=" + seller +
                    ", currency=" + currency +
                    ", amount=" + amount +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    @Validated
    public final static class Shipment {

        @Pattern(regexp = "(\\w\\s?){3,50}")
        private String deliveryService;
        @NotNull
        private Currency currency;
        @NotNull
        @Min(0)
        private BigDecimal amount;
        @Valid
        private Address address;
        @Valid
        private Recipient recipient;

        public String getDeliveryService() {
            return deliveryService;
        }

        public void setDeliveryService(String deliveryService) {
            this.deliveryService = deliveryService;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Recipient getRecipient() {
            return recipient;
        }

        public void setRecipient(Recipient recipient) {
            this.recipient = recipient;
        }

        @Override
        public String toString() {
            return "Shipment{" +
                    "deliveryService='" + deliveryService + '\'' +
                    ", currency=" + currency +
                    ", amount=" + amount +
                    ", address=" + address +
                    ", recipient=" + recipient +
                    '}';
        }

        @Validated
        public final static class Address {
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            private String country;
            @Pattern(regexp = "[a-zA-z]{3,50}")
            private String state;
            @Pattern(regexp = "[a-zA-z]{3,50}")
            private String region;
            @NotNull
            @Pattern(regexp = "\\w{3,5}")
            private String index;
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            private String city;
            @NotNull
            @Pattern(regexp = "[\\w\\s\\-.]{3,50}")
            private String street;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            @Override
            public String toString() {
                return "Address{" +
                        "country='" + country + '\'' +
                        ", state='" + state + '\'' +
                        ", region='" + region + '\'' +
                        ", index='" + index + '\'' +
                        ", city='" + city + '\'' +
                        ", street='" + street + '\'' +
                        '}';
            }
        }

        @Validated
        public final static class Recipient {
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            private String firstName;
            @NotNull
            @Pattern(regexp = "[a-zA-z]{3,50}")
            private String lastName;
            @NotEmpty
            @Phone
            private String phone;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            @Override
            public String toString() {
                return "Recipient{" +
                        "firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", phone='" + phone + '\'' +
                        '}';
            }
        }

    }

    public boolean add(Item item) {
        return items.add(item);
    }

    public boolean remove(Object o) {
        return items.remove(o);
    }

    public boolean addAll(Collection<? extends Item> c) {
        return items.addAll(c);
    }

    public Collection<Item> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "InOrderDTO{" +
                "customer='" + customer + '\'' +
                ", shipment=" + shipment +
                ", items=" + items +
                '}';
    }
}

package ukma.eCommerce.core.paymentModule.model.domain.vo;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;
import ukma.eCommerce.core.userModule.model.domain.vo.FullNameVO;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * <p>
 * value object that represents Address for order shipment
 * </p>
 *
 * @author Solomka
 */

public final class AddressVO {

    @NotNull
    @Pattern(regexp = "[a-zA-z]{3,50}")
    private final String country;
    @Pattern(regexp = "[a-zA-z]{3,50}")
    private final String state;
    @Pattern(regexp = "[a-zA-z]{3,50}")
    private final String region;
    @NotNull
    @Pattern(regexp = "[a-zA-z]{3,50}")
    private final String city;
    @NotNull
    @Pattern(regexp = "[a-zA-z\\d\\s\\-]{3,50}")
    private final String street;
    @NotNull
    @Pattern(regexp = "\\w{3,5}")
    private final String postCode;
    @NotNull
    @Valid
    private final FullNameVO fullName;
    @NotNull
    @Pattern(regexp = ValidationUtil.PHONE_PATTERN)
    private final String phone;
    @NotNull
    @Valid
    private final CustomerVO customer;

    /**
     * builder that creates immutable instance of {@linkplain AddressVO}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<AddressVO> {

        private String country;
        private String state;
        private String region;
        private String city;
        private String street;
        private String postCode;

        private FullNameVO fullName;
        private String phone;

        // shipment address is attached to the specific customer
        private CustomerVO customer;

        public Builder() {
        }

        public Builder(@NotNull AddressVO address) {

            if (address == null)
                throw new NullPointerException("address must not be null");

            setCountry(address.getCountry()).setState(address.getState()).setRegion(address.getRegion())
                    .setCity(address.getCity()).setStreet(address.getStreet()).setPostCode(address.getPostCode())
                    .setFullName(address.getFullName()).setPhone(address.getPhone()).setCustomer(address.getCustomer());
        }

        public String getCountry() {
            return country;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getState() {
            return state;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public String getRegion() {
            return region;
        }

        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        public String getCity() {
            return city;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public String getStreet() {
            return street;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public String getPostCode() {
            return postCode;
        }

        public Builder setPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public FullNameVO getFullName() {
            return fullName;
        }

        public Builder setFullName(FullNameVO fullName) {
            this.fullName = fullName;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerVO getCustomer() {
            return customer;
        }

        public Builder setCustomer(CustomerVO customer) {
            this.customer = customer;
            return this;
        }

        @Override
        public AddressVO build() {
            return new AddressVO(this);
        }

    }

    private AddressVO(@NotNull Builder builder) {

        Objects.requireNonNull(builder, "builder must not be null");

        this.country = Objects.requireNonNull(builder.getCountry(), "country must not be null");
        this.city = Objects.requireNonNull(builder.getCity(), "city must not be null");
        this.street = Objects.requireNonNull(builder.getStreet(), "street must not be null");
        this.postCode = Objects.requireNonNull(builder.getPostCode(), "postCode must not be null");
        this.fullName = Objects.requireNonNull(builder.getFullName(), "fullName must not be null");
        this.phone = Objects.requireNonNull(builder.getPhone(), "phone must not be null");
        this.customer = Objects.requireNonNull(builder.getCustomer(), "customer must not be null");

        this.state = builder.getState();
        this.region = builder.getRegion();
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public FullNameVO getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerVO getCustomer() {
        return customer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressVO other = (AddressVO) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (region == null) {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AddressVO [country=" + country + ", state=" + state + ", region=" + region + ", city=" + city
                + ", street=" + street + ", postCode=" + postCode + ", fullName=" + fullName + ", phone=" + phone
                + ", customer=" + customer + "]";
    }

}

package ukma.eCommerce.core.userModule.model.domain.vo;

import ukma.eCommerce.util.IBuilder;

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
public final class Address {

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
    @Pattern(regexp = "[\\w\\s\\-.]{3,50}")
    private final String street;
    @NotNull
    @Pattern(regexp = "\\w{3,5}")
    private final String index;

    /**
     * builder that creates immutable instance of {@linkplain Address}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<Address> {

        private String country;
        private String state;
        private String region;
        private String city;
        private String street;
        private String index;

        public Builder() {}

        public Builder(@NotNull Address address) {

            Objects.requireNonNull(address, "address must not be null");

            setCountry(address.getCountry()).setState(address.getState()).setRegion(address.getRegion())
                    .setCity(address.getCity()).setStreet(address.getStreet()).setIndex(address.getIndex());
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

        public String getIndex() {
            return index;
        }

        public Builder setIndex(String index) {
            this.index = index;
            return this;
        }

        @Override
        public Address build() {
            return new Address(this);
        }

    }

    private Address(@NotNull Builder builder) {

        Objects.requireNonNull(builder, "builder must not be null");

        this.country = Objects.requireNonNull(builder.getCountry(), "country must not be null");
        this.city = Objects.requireNonNull(builder.getCity(), "city must not be null");
        this.street = Objects.requireNonNull(builder.getStreet(), "street must not be null");
        this.index = Objects.requireNonNull(builder.getIndex(), "index must not be null");
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

    public String getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address addressVO = (Address) o;

        if (!country.equals(addressVO.country)) return false;
        if (state != null ? !state.equals(addressVO.state) : addressVO.state != null) return false;
        if (region != null ? !region.equals(addressVO.region) : addressVO.region != null) return false;
        if (!city.equals(addressVO.city)) return false;
        if (!street.equals(addressVO.street)) return false;
        return index.equals(addressVO.index);

    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + index.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}

package ukma.eCommerce.core.userModule.model.domain.bo;

import ukma.eCommerce.util.IBuilder;

import java.util.Objects;

public final class Seller {

    private final long id;
    private CredentialsVO credentials;
    private AddressVO address;
    private String businessName;

    /**
     * Builder which creates instance of {@linkplain Seller}
     *
     * @author Max Oliynick
     */
    public static class Builder implements IBuilder<Seller> {

        private long id;
        private CredentialsVO credentials;
        private AddressVO address;
        private String businessName;

        public Builder() {
        }

        public Builder(Seller user) {
            Objects.requireNonNull(user, "user == null");
            setId(user.getId()).setCredentials(user.getCredentials()).
                    setAddress(user.getAddress()).setBusinessName(user.getBusinessName());
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public long getId() {
            return id;
        }

        public CredentialsVO getCredentials() {
            return credentials;
        }

        public Builder setCredentials(CredentialsVO credentials) {
            this.credentials = credentials;
            return this;
        }

        public AddressVO getAddress() {
            return address;
        }

        public Builder setAddress(AddressVO address) {
            this.address = address;
            return this;
        }

        public String getBusinessName() {
            return businessName;
        }

        public Builder setBusinessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        @Override
        public Seller build() {
            return new Seller(this);
        }

    }

    /**
     * Constructs instance from builder
     *
     * @param builder builder to use
     */
    private Seller(Builder builder) {

        Objects.requireNonNull(builder, "builder == null");

        if (builder.getId() <= 0)
            throw new IllegalArgumentException("id <= 0");

        Objects.requireNonNull(builder.getBusinessName(), "business name == null");

        final int bNameLen = builder.getBusinessName().length();

        if (bNameLen < 3 || bNameLen > 50)
            throw new IllegalArgumentException(
                    String.format("business name length is out of bounds, was %d", bNameLen));

        this.id = builder.getId();
        this.businessName = Objects.requireNonNull(builder.getBusinessName());
        this.credentials = Objects.requireNonNull(builder.getCredentials());
        this.address = Objects.requireNonNull(builder.getAddress());
    }

    public String getBusinessName() {
        return businessName;
    }

    public long getId() {
        return id;
    }

    public CredentialsVO getCredentials() {
        return credentials;
    }

    public AddressVO getAddress() {
        return address;
    }

}

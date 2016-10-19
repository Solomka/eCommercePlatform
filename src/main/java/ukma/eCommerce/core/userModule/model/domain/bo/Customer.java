package ukma.eCommerce.core.userModule.model.domain.bo;

import ukma.eCommerce.core.userModule.model.domain.vo.FullNameVO;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.util.Objects;

public final class Customer {

    private final long id;
    private FullNameVO fullName;
    private CredentialsVO credentials;

    /**
     * Builder which creates instance of {@linkplain Seller}
     *
     * @author Max Oliynick
     */
    public static class Builder implements IBuilder<Customer> {

        private long id;
        private FullNameVO fullNameVO;
        private CredentialsVO credentials;

        public long getId() {
            return id;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public FullNameVO getFullNameVO() {
            return fullNameVO;
        }

        public Builder setFullNameVO(FullNameVO fullNameVO) {
            this.fullNameVO = fullNameVO;
            return this;
        }

        public CredentialsVO getCredentials() {
            return credentials;
        }

        public Builder setCredentials(CredentialsVO credentials) {
            this.credentials = credentials;
            return this;
        }

        @Override
        public Customer build() {
            return new Customer(this);
        }

    }

    /**
     * Constructs instance from builder
     *
     * @param builder builder to use
     */
    private Customer(Builder builder) {

        Objects.requireNonNull(builder, "builder == null");

        if (builder.getId() <= 0)
            throw new IllegalArgumentException("id <= 0");

        this.id = builder.getId();
        this.fullName = Objects.requireNonNull(builder.getFullNameVO());
        this.credentials = Objects.requireNonNull(builder.getCredentials());

    }

    public long getId() {
        return id;
    }

    public FullNameVO getFullNameVO() {
        return fullName;
    }

    public CredentialsVO getCredentials() {
        return credentials;
    }

    /**
     * @param credentials not null
     */
    public void changeCredentials(CredentialsVO credentials) {

        if (!ValidationUtil.isValid(credentials))
            throw new IllegalArgumentException("credentials aren't valid");

        this.credentials = credentials;
    }

    /**
     * @param fullName not null
     */
    public void changeFullName(FullNameVO fullName) {

        if (!ValidationUtil.isValid(fullName))
            throw new IllegalArgumentException("full name isn't valid");

        this.fullName = fullName;
    }

}

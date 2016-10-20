package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.hibernate.validator.constraints.Range;
import ukma.eCommerce.util.IBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by Максим on 10/20/2016.
 */
public final class ProductInfo {

    @NotNull
    @Pattern(regexp = "^\\w{3,50}$")
    private final String name;
    @NotNull
    @Valid
    private final Money money;
    @Min(1)
    private final int availableQuantity;
    @NotNull
    @Range(min = 100, max = 512)
    private final String description;
    @NotNull
    @Valid
    private final Type type;

    public static class Builder implements IBuilder<ProductInfo> {

        private String name;
        private Money money;
        private int availableQuantity;
        private String description;
        private Type type;

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Money getMoney() {
            return money;
        }

        public Builder setMoney(Money money) {
            this.money = money;
            return this;
        }

        public int getAvailableQuantity() {
            return availableQuantity;
        }

        public Builder setAvailableQuantity(int availableQuantity) {
            this.availableQuantity = availableQuantity;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Type getType() {
            return type;
        }

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        @Override
        public ProductInfo build() {
            return new ProductInfo(this);
        }
    }

    public ProductInfo(Builder builder) {

        Objects.requireNonNull(builder, "builder must no be null");

        this.name = Objects.requireNonNull(builder.getName());
        this.money = Objects.requireNonNull(builder.getMoney());
        this.availableQuantity = builder.getAvailableQuantity();
        this.description = Objects.requireNonNull(builder.getDescription());
        this.type = Objects.requireNonNull(builder.getType());
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInfo that = (ProductInfo) o;

        if (availableQuantity != that.availableQuantity) return false;
        if (!name.equals(that.name)) return false;
        if (!money.equals(that.money)) return false;
        if (!description.equals(that.description)) return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + availableQuantity;
        result = 31 * result + description.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", availableQuantity=" + availableQuantity +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}

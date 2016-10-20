package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.Address;
import ukma.eCommerce.util.IBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * value object that represents the order shipment process
 * </p>
 *
 * @author Solomka
 */
public final class Shipment {

    @Pattern(regexp = "[a-zA-z]{3,50}")
    private final String deliveryService;
    @NotNull
    @Valid
    private final Money money;
    @NotNull
    @Valid
    private final Address address;
    @NotNull
    @Future
    private final DateTime deliveryDate;
    @NotNull
    private final ShipmentStatus status;

    /**
     * builder that creates immutable instance of {@linkplain Shipment}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<Shipment> {

        private String deliveryService;
        private BigDecimal totalSum;
        private Money money;
        private Address address;
        private DateTime deliveryDate;
        private ShipmentStatus status;

        public Builder() {
        }

        public Builder(final Shipment shipment) {

            Objects.requireNonNull(shipment, "shipment must not be null");

            setDeliveryService(shipment.getDeliveryService()).setMoney(shipment.getMoney())
                    .setAddress(shipment.getAddress()).setDeliveryDate(shipment.getDeliveryDate())
                    .setStatus(shipment.getStatus());
        }

        public Money getMoney() {
            return money;
        }

        public Builder setMoney(Money money) {
            this.money = money;
            return this;
        }

        public String getDeliveryService() {
            return deliveryService;
        }

        public Builder setDeliveryService(String deliveryService) {
            this.deliveryService = deliveryService;
            return this;
        }

        public BigDecimal getTotalSum() {
            return totalSum;
        }

        public Builder setTotalSum(BigDecimal totalSum) {
            this.totalSum = totalSum;
            return this;
        }

        public Address getAddress() {
            return address;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public DateTime getDeliveryDate() {
            return deliveryDate;
        }

        public Builder setDeliveryDate(DateTime deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public ShipmentStatus getStatus() {
            return status;
        }

        public Builder setStatus(ShipmentStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public Shipment build() {
            return new Shipment(this);
        }

    }

    private Shipment(Builder builder) {

        Objects.requireNonNull(builder, "builder must not be null");

        this.address = Objects.requireNonNull(builder.getAddress(), "address must not be null");
        this.status = Objects.requireNonNull(builder.getStatus(), "status must not be null");
        this.money = Objects.requireNonNull(builder.getMoney(), "money == null");
        this.deliveryService = builder.getDeliveryService();
        this.deliveryDate = builder.getDeliveryDate();
    }

    public String getDeliveryService() {
        return deliveryService;
    }

    public Address getAddress() {
        return address;
    }

    public DateTime getDeliveryDate() {
        return deliveryDate;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipment shipment = (Shipment) o;

        if (deliveryService != null ? !deliveryService.equals(shipment.deliveryService) : shipment.deliveryService != null)
            return false;
        if (!money.equals(shipment.money)) return false;
        if (!address.equals(shipment.address)) return false;
        if (deliveryDate != null ? !deliveryDate.equals(shipment.deliveryDate) : shipment.deliveryDate != null)
            return false;
        return status == shipment.status;

    }

    @Override
    public int hashCode() {
        int result = deliveryService != null ? deliveryService.hashCode() : 0;
        result = 31 * result + money.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + status.hashCode();
        return result;
    }

}

package ukma.eCommerce.core.paymentModule.model.domain.vo;


import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.util.IBuilder;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * value object that represents the order shipment process
 * </p>
 *
 * @author Solomka
 */
public final class ShipmentVO {

    private final String deliveryService;
    private final BigDecimal totalSum;

    private final AddressVO address;
    private final DateTime deliveryDate;
    private final ShipmentStatus status;

    /**
     * builder that creates immutable instance of {@linkplain ShipmentVO}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<ShipmentVO> {

        private String deliveryService;
        private BigDecimal totalSum;

        private AddressVO address;
        private DateTime deliveryDate;
        private ShipmentStatus status;

        public Builder(final AddressVO address, final ShipmentStatus status) {
            this.address = address;
            this.status = status;
        }

        public Builder(ShipmentVO shipment) {

            if (shipment == null)
                throw new NullPointerException("shipment must not be null");

            setDeliveryService(shipment.getDeliveryService()).setTotalSum(shipment.getTotalSum())
                    .setAddress(shipment.getAddress()).setDeliveryDate(shipment.getDeliveryDate())
                    .setStatus(shipment.getStatus());

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

        public AddressVO getAddress() {
            return address;
        }

        public Builder setAddress(AddressVO address) {
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
        public ShipmentVO build() {
            return new ShipmentVO(this);
        }

    }

    private ShipmentVO(Builder builder) {

        Objects.requireNonNull(builder, "builder must not be null");

        this.address = Objects.requireNonNull(builder.getAddress(), "address must not be null");
        this.status = Objects.requireNonNull(builder.getStatus(), "status must not be null");

        this.deliveryService = builder.getDeliveryService();
        this.totalSum = builder.getTotalSum();
        this.deliveryDate = builder.getDeliveryDate();
    }

    public String getDeliveryService() {
        return deliveryService;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public AddressVO getAddress() {
        return address;
    }

    public DateTime getDeliveryDate() {
        return deliveryDate;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
        result = prime * result + ((deliveryService == null) ? 0 : deliveryService.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((totalSum == null) ? 0 : totalSum.hashCode());
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
        ShipmentVO other = (ShipmentVO) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (deliveryDate == null) {
            if (other.deliveryDate != null)
                return false;
        } else if (!deliveryDate.equals(other.deliveryDate))
            return false;
        if (deliveryService == null) {
            if (other.deliveryService != null)
                return false;
        } else if (!deliveryService.equals(other.deliveryService))
            return false;
        if (status != other.status)
            return false;
        if (totalSum == null) {
            if (other.totalSum != null)
                return false;
        } else if (!totalSum.equals(other.totalSum))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ShipmentVO [deliveryService=" + deliveryService + ", totalSum=" + totalSum + ", address=" + address
                + ", deliveryDate=" + deliveryDate + ", status=" + status + "]";
    }

}

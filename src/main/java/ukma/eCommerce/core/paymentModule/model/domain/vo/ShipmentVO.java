package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * value object that represents the order shipment process
 * </p>
 * 
 * @author Solomka
 *
 */
public final class ShipmentVO implements IValidateable {

	private final String deliveryService;
	private final BigDecimal totalSum;

	private final AddressVO address;
	private final DateTime deliveryDate;
	private final ShipmentStatus status;

	public ShipmentVO(String deliveryService, BigDecimal totalSum, AddressVO address, DateTime deliveryDate,
			ShipmentStatus status) {

		this.deliveryService = deliveryService;
		
		/*
		 * shipment can be free of charge 
		 * ? 
		 * Is is better to have null value totalSum for free of charge shipment 
		 * or this.totalSum = BigDecimal.ZERO;
		 */

		this.totalSum = totalSum;
		this.address = Objects.requireNonNull(address, "address == null");
		this.deliveryDate = deliveryDate;
		this.status = Objects.requireNonNull(status, "status == null");
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
	public String toString() {
		return "ShipmentVO [deliveryService=" + deliveryService + ", totalSum=" + totalSum + ", address=" + address
				+ ", deliveryDate=" + deliveryDate + ", status=" + status + "]";
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

}

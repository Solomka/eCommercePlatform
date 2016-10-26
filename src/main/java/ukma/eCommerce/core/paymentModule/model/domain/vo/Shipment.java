package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.util.IBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	private final Price price;
	@NotNull
	@Valid
	private final ShipmentDetails shipmentDetails;
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
		private Price price;
		private ShipmentDetails shipmentDetails;
		private DateTime deliveryDate;
		private ShipmentStatus status;

		public Builder() {
		}

		public Builder(final Shipment shipment) {

			Objects.requireNonNull(shipment, "shipment must not be null");

			setDeliveryService(shipment.getDeliveryService()).setMoney(shipment.getPrice())
					.setShipmentDetails(shipment.getShipmentDetails()).setDeliveryDate(shipment.getDeliveryDate())
					.setStatus(shipment.getStatus());
		}

		public Price getPrice() {
			return price;
		}

		public Builder setMoney(Price price) {
			this.price = price;
			return this;
		}

		public String getDeliveryService() {
			return deliveryService;
		}

		public Builder setDeliveryService(String deliveryService) {
			this.deliveryService = deliveryService;
			return this;
		}

		public ShipmentDetails getShipmentDetails() {
			return shipmentDetails;
		}

		public Builder setShipmentDetails(ShipmentDetails shipmentDetails) {
			this.shipmentDetails = shipmentDetails;
			return this;
		}

		public Builder setPrice(Price price) {
			this.price = price;
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

		this.shipmentDetails = Objects.requireNonNull(builder.getShipmentDetails(), "address must not be null");
		this.status = Objects.requireNonNull(builder.getStatus(), "status must not be null");
		this.price = Objects.requireNonNull(builder.getPrice(), "price == null");
		this.deliveryService = builder.getDeliveryService();
		this.deliveryDate = builder.getDeliveryDate();
	}

	public String getDeliveryService() {
		return deliveryService;
	}

	public ShipmentDetails getShipmentDetails() {
		return shipmentDetails;
	}

	public DateTime getDeliveryDate() {
		return deliveryDate;
	}

	public ShipmentStatus getStatus() {
		return status;
	}

	public Price getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((deliveryService == null) ? 0 : deliveryService.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((shipmentDetails == null) ? 0 : shipmentDetails.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Shipment other = (Shipment) obj;
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (shipmentDetails == null) {
			if (other.shipmentDetails != null)
				return false;
		} else if (!shipmentDetails.equals(other.shipmentDetails))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shipment [deliveryService=" + deliveryService + ", price=" + price + ", shipmentDetails="
				+ shipmentDetails + ", deliveryDate=" + deliveryDate + ", status=" + status + "]";
	}

}

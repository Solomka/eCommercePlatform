package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.util.IBuilder;

@Entity
@Table(name = "shipment")
public class ShipmentPO implements Serializable {

	/**
	 * TODO: rewrite with Builder
	 * 
	 */
	private static final long serialVersionUID = -5927906493962720311L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "delivery_service")
	private String deliveryService;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "price", column = @Column(name = "total_sum") ),
			@AttributeOverride(name = "currency", column = @Column(name = "currency") ) })
	private Price price;

	@Column(name = "delivery_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deliveryDate;

	@Column(name = "status", nullable = false, columnDefinition = "enum('DUMMY')")
	private ShipmentStatus status;
	
	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "address_id", nullable = false/* , updatable = false */)
	private AddressPO address;

	public static class Builder implements IBuilder<ShipmentPO> {

		private UUID id;
		private String deliveryService;
		private Price price;
		private DateTime deliveryDate;
		private ShipmentStatus status;
		private AddressPO address;

		public Builder() {

		}

		public Builder(ShipmentPO shipmentPO) {
			Objects.requireNonNull(shipmentPO, "shipmentPO must not be null");

			setId(shipmentPO.getId()).setDeliveryService(shipmentPO.getDeliveryService())
					.setPrice(shipmentPO.getPrice()).setDeliveryDate(shipmentPO.getDeliveryDate())
					.setStatus(shipmentPO.getStatus()).setAddress(shipmentPO.getAddress());

		}

		public UUID getId() {
			return id;
		}

		public Builder setId(UUID id) {
			this.id = id;
			return this;
		}

		public String getDeliveryService() {
			return deliveryService;
		}

		public Builder setDeliveryService(String deliveryService) {
			this.deliveryService = deliveryService;
			return this;
		}

		public Price getPrice() {
			return price;
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

		public AddressPO getAddress() {
			return address;
		}

		public Builder setAddress(AddressPO address) {
			this.address = address;
			return this;
		}

		@Override
		public ShipmentPO build() {
			// TODO Auto-generated method stub
			return new ShipmentPO(this);
		}
	}

	public ShipmentPO() {

	}

	public ShipmentPO(Builder builder) {

		Objects.requireNonNull(builder, "builder can not be null");
		this.id = builder.getId();
		this.deliveryService =builder.getDeliveryService();
		this.price = Objects.requireNonNull(builder.getPrice());
		this.deliveryDate = builder.getDeliveryDate();
		this.status = Objects.requireNonNull(builder.getStatus());
		this.address = Objects.requireNonNull(builder.getAddress());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(String deliveryService) {
		this.deliveryService = deliveryService;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public DateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(DateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public ShipmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}

	public AddressPO getAddress() {
		return address;
	}

	public void setAddress(AddressPO address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
		result = prime * result + ((getDeliveryDate() == null) ? 0 : getDeliveryDate().hashCode());
		result = prime * result + ((getDeliveryService() == null) ? 0 : getDeliveryService().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ShipmentPO)) {
			return false;
		}
		ShipmentPO other = (ShipmentPO) obj;
		if (getAddress() == null) {
			if (other.getAddress() != null) {
				return false;
			}
		} else if (!getAddress().equals(other.getAddress())) {
			return false;
		}
		if (getDeliveryDate() == null) {
			if (other.getDeliveryDate() != null) {
				return false;
			}
		} else if (!getDeliveryDate().equals(other.getDeliveryDate())) {
			return false;
		}
		if (getDeliveryService() == null) {
			if (other.getDeliveryService() != null) {
				return false;
			}
		} else if (!getDeliveryService().equals(other.getDeliveryService())) {
			return false;
		}
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		if (getStatus() != other.getStatus()) {
			return false;
		}
		if (getPrice() == null) {
			if (other.getPrice() != null) {
				return false;
			}
		} else if (!getPrice().equals(other.getPrice())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ShipmentPO [id=" + id + ", deliveryService=" + deliveryService + ", price=" + price + ", deliveryDate="
				+ deliveryDate + ", status=" + status + ", address=" + address + "]";
	}

}

package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;

@Entity
@Table(name = "shipment")
public class ShipmentPO implements Serializable {

	/**
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

	@Column(name = "total_sum", nullable = false)
	private BigDecimal totalSum;

	@Column(name = "delivery_date")
	private DateTime deliveryDate;

	@Column(name = "status", nullable = false)
	private ShipmentStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "address_id", nullable = false/* , updatable = false */)
	private AddressPO address;

	public ShipmentPO() {

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

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
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
		result = prime * result + ((getTotalSum() == null) ? 0 : getTotalSum().hashCode());
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
		if (getTotalSum() == null) {
			if (other.getTotalSum() != null) {
				return false;
			}
		} else if (!getTotalSum().equals(other.getTotalSum())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ShipmentPO [id=" + id + ", deliveryService=" + deliveryService + ", totalSum=" + totalSum
				+ ", deliveryDate=" + deliveryDate + ", status=" + status + ", address=" + address + "]";
	}

}

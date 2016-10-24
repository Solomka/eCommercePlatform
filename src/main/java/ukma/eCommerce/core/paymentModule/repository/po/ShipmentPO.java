package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "delivery_service")
	private String deliveryService;

	@Column(name = "total_sum", nullable = false)
	private BigDecimal totalSum;

	@Column(name = "delivery_date")
	private DateTime deliveryDate;

	@Column(name = "status", nullable = false)
	private ShipmentStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "address_id", unique = true, nullable = false, updatable = false)
	private AddressPO address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}

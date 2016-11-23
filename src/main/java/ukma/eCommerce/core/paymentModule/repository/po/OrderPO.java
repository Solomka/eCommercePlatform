package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;

@Entity
@Table(name = "orderS")
public class OrderPO implements Serializable {

	/**
	 * TODO: rewrite with Builder
	 * 
	 */
	private static final long serialVersionUID = -6339656414384828558L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "creation_date", nullable = false)
	private DateTime creationDate;

	@Column(name = "fulfilment_date")
	private DateTime fulfilmentDate;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", nullable = false/* , updatable = false */)
	private CustomerPO customer;

	//@OneToOne(optional = false)
	//@JoinColumn(name = "shipment_id", nullable = false/* , updatable = false */)
	/*
	 * Here is the annotation to add in order to Hibernate to automatically
	 * insert and update ShipmentPO (if any)
	 */
	@OneToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private ShipmentPO shipment;

	// @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	/*
	 * Here is the annotation to add in order to Hibernate to automatically
	 * insert and update OrderItems (if any)
	 */
	//@SuppressWarnings("deprecation")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemId.order", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE })
	private List<OrderItemPO> orderItems;

	@OneToMany(mappedBy = "order", cascade = CascadeType.REFRESH)
	private List<InvoicePO> invoices;

	public OrderPO() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DateTime getFulfilmentDate() {
		return fulfilmentDate;
	}

	public void setFulfilmentDate(DateTime fulfilmentDate) {
		this.fulfilmentDate = fulfilmentDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public CustomerPO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerPO customer) {
		this.customer = customer;
	}

	public ShipmentPO getShipment() {
		return shipment;
	}

	public void setShipment(ShipmentPO shipment) {
		this.shipment = shipment;
	}

	public List<OrderItemPO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemPO> orderItems) {
		this.orderItems = orderItems;
	}

	public List<InvoicePO> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoicePO> invoices) {
		this.invoices = invoices;
	}

	/**
	 * rewrite fields access to getters access for props because of Hibernate
	 * proxy
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
		result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
		result = prime * result + ((getFulfilmentDate() == null) ? 0 : getFulfilmentDate().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getInvoices() == null) ? 0 : getInvoices().hashCode());
		result = prime * result + ((getOrderItems() == null) ? 0 : getOrderItems().hashCode());
		result = prime * result + ((getShipment() == null) ? 0 : getShipment().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
		if (!(obj instanceof OrderPO)) {
			return false;
		}
		OrderPO other = (OrderPO) obj;
		if (getCreationDate() == null) {
			if (other.getCreationDate() != null) {
				return false;
			}
		} else if (!getCreationDate().equals(other.getCreationDate())) {
			return false;
		}
		if (getCustomer() == null) {
			if (other.getCustomer() != null) {
				return false;
			}
		} else if (!getCustomer().equals(other.getCustomer())) {
			return false;
		}
		if (getFulfilmentDate() == null) {
			if (other.getFulfilmentDate() != null) {
				return false;
			}
		} else if (!getFulfilmentDate().equals(other.getFulfilmentDate())) {
			return false;
		}
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		if (getInvoices() == null) {
			if (other.getInvoices() != null) {
				return false;
			}
		} else if (!getInvoices().equals(other.getInvoices())) {
			return false;
		}
		if (getOrderItems() == null) {
			if (other.getOrderItems() != null) {
				return false;
			}
		} else if (!getOrderItems().equals(other.getOrderItems())) {
			return false;
		}
		if (getShipment() == null) {
			if (other.getShipment() != null) {
				return false;
			}
		} else if (!getShipment().equals(other.getShipment())) {
			return false;
		}
		if (getStatus() != other.getStatus()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "OrderPO [id=" + id + ", creationDate=" + creationDate + ", fulfilmentDate=" + fulfilmentDate
				+ ", status=" + status + ", customer=" + customer + ", shipment=" + shipment + ", orderItems="
				+ orderItems + ", invoices=" + invoices + "]";
	}

}

package ukma.eCommerce.core.paymentModule.repository.po;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;

@Entity
@Table(name = "order")
public class OrderPO {

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

	@OneToOne(optional = false)
	@JoinColumn(name = "shipment_id", nullable = false/* , updatable = false */)
	private ShipmentPO shipment;

	/*
	 * Here is the annotation to add in order to Hibernate to automatically
	 * insert and update ProducItems (if any)
	 * 
	 * @SuppressWarnings("deprecation")
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = {
	 * CascadeType.PERSIST, CascadeType.MERGE })
	 * 
	 * @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	 * org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	 * 
	 */
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
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
	 * TODO: rewrite fields access to getters access for props because of
	 * Hibernate proxy
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((fulfilmentDate == null) ? 0 : fulfilmentDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((shipment == null) ? 0 : shipment.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (creationDate == null) {
			if (other.creationDate != null) {
				return false;
			}
		} else if (!creationDate.equals(other.creationDate)) {
			return false;
		}
		if (customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!customer.equals(other.customer)) {
			return false;
		}
		if (fulfilmentDate == null) {
			if (other.fulfilmentDate != null) {
				return false;
			}
		} else if (!fulfilmentDate.equals(other.fulfilmentDate)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (invoices == null) {
			if (other.invoices != null) {
				return false;
			}
		} else if (!invoices.equals(other.invoices)) {
			return false;
		}
		if (orderItems == null) {
			if (other.orderItems != null) {
				return false;
			}
		} else if (!orderItems.equals(other.orderItems)) {
			return false;
		}
		if (shipment == null) {
			if (other.shipment != null) {
				return false;
			}
		} else if (!shipment.equals(other.shipment)) {
			return false;
		}
		if (status != other.status) {
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

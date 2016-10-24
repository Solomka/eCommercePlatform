package ukma.eCommerce.core.paymentModule.repository.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;

@Entity
@Table(name = "order")
public class OrderPO {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "creation_date", nullable = false)
	private DateTime creationDate;

	@Column(name = "fulfilment_date")
	private DateTime fulfilmentDate;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", unique = true, nullable = false, updatable = false)
	private CustomerPO customer;

	@OneToOne(optional = false)
	@JoinColumn(name = "shipment_id", unique = true, nullable = false, updatable = false)
	private ShipmentPO shipment;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItemPO> orderItems;

	@OneToMany(mappedBy = "order", cascade = CascadeType.REFRESH)
	private List<InvoicePO> invoices;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	

}

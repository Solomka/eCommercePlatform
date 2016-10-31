package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;

@Entity
@Table(name = "invoice")
public class InvoicePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -427517393546381804L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "creation_date", nullable = false)
	private DateTime creationDate;

	@Column(name = "quantity", nullable = false)
	private int totalQuantity;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "price", column = @Column(name = "total_sum") ),
			@AttributeOverride(name = "currency", column = @Column(name = "currency") ) })
	private Price price;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private InvoiceStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", nullable = false, updatable = false)
	private OrderPO order;

	public InvoicePO() {

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

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Price getMoney() {
		return price;
	}

	public void setMoney(Price money) {
		this.price = money;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	public OrderPO getOrder() {
		return order;
	}

	public void setOrder(OrderPO order) {
		this.order = order;
	}
	
	/**
	 * TODO: rewrite fields access to getters access for props because of Hibernate proxy   
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalQuantity;
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
		if (!(obj instanceof InvoicePO)) {
			return false;
		}
		InvoicePO other = (InvoicePO) obj;
		if (creationDate == null) {
			if (other.creationDate != null) {
				return false;
			}
		} else if (!creationDate.equals(other.creationDate)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (order == null) {
			if (other.order != null) {
				return false;
			}
		} else if (!order.equals(other.order)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (totalQuantity != other.totalQuantity) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "InvoicePO [id=" + id + ", creationDate=" + creationDate + ", totalQuantity=" + totalQuantity
				+ ", money=" + price + ", status=" + status + ", order=" + order + "]";
	}

}

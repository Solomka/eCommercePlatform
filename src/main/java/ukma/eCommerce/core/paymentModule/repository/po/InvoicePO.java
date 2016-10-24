package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "creation_date", nullable = false)
	private DateTime creationDate;

	@Column(name = "quantity", nullable = false)
	private int totalQuantity;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "price", column = @Column(name = "total_sum") ),
			@AttributeOverride(name = "currency", column = @Column(name = "currency") ) })
	private Money money;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private InvoiceStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", unique = true, nullable = false, updatable = false)
	private OrderPO order;

	public InvoicePO() {

	}

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

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
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

}

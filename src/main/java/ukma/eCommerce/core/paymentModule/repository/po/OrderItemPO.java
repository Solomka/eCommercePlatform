package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/*
 * TODO DON'T WORK WITH UUID IDS =( 
 */

@Entity
@Table(name = "order_item")
@IdClass(OrderItemID.class)
public class OrderItemPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1849033798209228948L;

	@Id
	@Column(name = "order_id", insertable = false, updatable = false, nullable = false)
	private long orderId;

	@Id
	@Column(name = "product_id", insertable = false, updatable = false, nullable = false)
	private long productId;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "price", column = @Column(name = "total_sum") ),
			@AttributeOverride(name = "currency", column = @Column(name = "currency") ) })
	private Price money;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "id")
	/*
	 * if this JPA model doesn't create a table for the "PROJ_EMP" entity,
	 * please comment out the @PrimaryKeyJoinColumn, and use the ff:
	 * 
	 * @JoinColumn(name = "employeeId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "employeeId", updatable = false, insertable =
	 * false, referencedColumnName = "id")
	 */
	private OrderPO order;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "id")
	/*
	 * the same goes here: if this JPA model doesn't create a table for the
	 * "PROJ_EMP" entity, please comment out the @PrimaryKeyJoinColumn, and use
	 * the ff:
	 * 
	 * @JoinColumn(name = "projectId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "projectId", updatable = false, insertable = false,
	 * referencedColumnName = "id")
	 */
	private ProductPO product;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Price getMoney() {
		return money;
	}

	public void setMoney(Price money) {
		this.money = money;
	}

	public OrderPO getOrder() {
		return order;
	}

	public void setOrder(OrderPO order) {
		this.order = order;
	}

	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

}

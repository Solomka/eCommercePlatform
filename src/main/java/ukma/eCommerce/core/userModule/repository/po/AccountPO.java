package ukma.eCommerce.core.userModule.repository.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1603853420217120603L;

	@Id
	@Column(name = "stripeAccId", unique = true, nullable = false, length = 128)
	private String stripeAccountId;

	@OneToOne(optional = false)
	@JoinColumn(name = "seller_id", unique = true, nullable = false /*
																	 * ,
																	 * updatable
																	 * = false
																	 */)
	private SellerPO seller;

	public AccountPO() {

	}

	public String getStripeAccountId() {
		return stripeAccountId;
	}

	public void setStripeAccountId(String stripeAccountId) {
		this.stripeAccountId = stripeAccountId;
	}

	public SellerPO getSeller() {
		return seller;
	}

	public void setSeller(SellerPO seller) {
		this.seller = seller;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSeller() == null) ? 0 : getSeller().hashCode());
		result = prime * result + ((getStripeAccountId() == null) ? 0 : getStripeAccountId().hashCode());
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
		if (!(obj instanceof AccountPO)) {
			return false;
		}
		AccountPO other = (AccountPO) obj;
		if (getSeller() == null) {
			if (other.getSeller() != null) {
				return false;
			}
		} else if (!getSeller().equals(other.getSeller())) {
			return false;
		}
		if (getStripeAccountId() == null) {
			if (other.getStripeAccountId() != null) {
				return false;
			}
		} else if (!getStripeAccountId().equals(other.getStripeAccountId())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AccountPO [stripeAccountId=" + stripeAccountId + ", seller=" + seller + "]";
	}

}

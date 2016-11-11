package ukma.eCommerce.util.repository.filter;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ukma.eCommerce.core.paymentModule.repository.po.InvoicePO;

/**
 * 
 * @author Solomka
 *
 */
public class InvoiceFilter implements IExposedFilter{
	
	@Autowired
	private SessionFactory sessionFactory;


	private CriteriaBuilder criteriaBuilder = null;
	private final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
	private final Root invoice = criteriaQuery.from(InvoicePO.class);
	
	private UUID id;
	
	
	public InvoiceFilter(CriteriaBuilder criteriaBuilder, UUID id) {
		this.criteriaBuilder = criteriaBuilder;
		this.id = id;
	}

	private Object getJpaTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate toFilter() {
		return criteriaBuilder.equal(invoice.get("id"), id);
	}
	
}



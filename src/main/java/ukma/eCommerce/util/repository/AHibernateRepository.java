package ukma.eCommerce.util.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * Class that provides Hibernate Repository implementation
 * 
 * @author Solomka
 *
 * @param <T>
 * @param <K>
 * @param <E>
 * @param <F>
 * @param <ObjectPO>
 */
public abstract class AHibernateRepository<T, K, E, F extends IExposedFilter, ObjectPO, KeyPO extends Serializable>
		implements IRepository<T, K, E, F> {

	@PersistenceContext
	EntityManager entityManager;
	// ***PO.java
	private final Class<ObjectPO> entityClass;
	
	private final CriteriaBuilder criteriaBuilder;
	private final CriteriaQuery<ObjectPO> criteriaQuery;
	private final Root<ObjectPO> root;

	@SuppressWarnings("unchecked")
	public AHibernateRepository() {
		this.entityClass = (Class<ObjectPO>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = (CriteriaQuery<ObjectPO>) criteriaBuilder.createQuery(entityClass);
		this.root = (Root<ObjectPO>) criteriaQuery.from(entityClass);
	}

	public ObjectPO getPOById(KeyPO key) {
		return entityManager.find(entityClass, key);
	}

	public void savePO(ObjectPO entity) {
		entityManager.persist(entity);
		// entityManager.flush();
	}

	public ObjectPO updatePO(ObjectPO entity) {
		return entityManager.merge(entity);
	}

	/**
	 * return a proxy object with the given identity value
	 * 
	 * @param key
	 * @return
	 */

	public ObjectPO getReference(KeyPO key) {
		return entityManager.getReference(entityClass, key);
	}

	public void deletePO(ObjectPO entity) {
		entityManager.remove(entity);
	}

	public boolean deletePOById(KeyPO k) {
		ObjectPO objectPO = getReference(k);
		if (objectPO != null) {
			deletePO(objectPO);
			return true;
		}
		return false;
	}

	public List<ObjectPO> findAllBySpecification(Specification<ObjectPO> specification) {

		// get predicate from specification
		Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
		// set predicate and execute query
		criteriaQuery.where(predicate);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}

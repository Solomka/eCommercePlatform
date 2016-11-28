package ukma.eCommerce.util.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * Class that provides JPA/Hibernate Repository implementation
 * 
 * @author Solomka
 *
 * @param <T>
 * @param <K>
 * @param <E>
 * @param <F>
 * @param <ObjectPO>
 *            persistence object
 * @param <KeyPO>
 *            persistence object key
 */

public abstract class AHibernateRepository<T, K, E, F extends IExposedFilter, ObjectPO extends Serializable, KeyPO extends Serializable>
		implements IRepository<T, K, E, F> {

	// ***PO.java
	private final Class<ObjectPO> entityClass;

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public AHibernateRepository() {
		this.entityClass = (Class<ObjectPO>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[4];
	}

	public void savePO(ObjectPO entity) {
		entityManager.persist(entity);
		//entityManager.flush();
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

	public void deletePOById(KeyPO k) {
		ObjectPO objectPO = getReference(k);
		deletePO(objectPO);
	}

	/*
	 * public boolean deletePOById(KeyPO k) { ObjectPO objectPO =
	 * getReference(k); if (objectPO != null) { deletePO(objectPO); return true;
	 * } return false; }
	 */
	public Collection<ObjectPO> findAllBySpecification(Specification<ObjectPO> specification) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ObjectPO> criteriaQuery = (CriteriaQuery<ObjectPO>) criteriaBuilder.createQuery(entityClass);
		Root<ObjectPO> root = (Root<ObjectPO>) criteriaQuery.from(entityClass);

		// get predicate from specification
		Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
		// set predicate and execute query
		criteriaQuery.where(predicate);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}

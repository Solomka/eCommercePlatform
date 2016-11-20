package ukma.eCommerce.util.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

	// ***PO.java
	private final Class<ObjectPO> entityClass;

	@SuppressWarnings("unchecked")
	public AHibernateRepository() {
		this.entityClass = (Class<ObjectPO>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public KeyPO save(ObjectPO entity) {
		return (KeyPO) getSession().save(entity);
	}

	public void updateEntity(ObjectPO entity) {
		getSession().update(entity);
	}

	public boolean deleteEntity(KeyPO k) {

		ObjectPO objectPO = loadEntity(k);
		if (objectPO != null) {
			getSession().delete(objectPO);
			return true;
		}
		return false;
	}

	/**
	 * return object by id from db
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ObjectPO get(KeyPO key) {
		return (ObjectPO) getSession().get(entityClass, key);
	}

	/**
	 * return a proxy object with the given identity value
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ObjectPO loadEntity(KeyPO key) {
		return (ObjectPO) getSession().load(entityClass, key);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(entityClass);
	}

	protected Query createQuery(String query) {
		return getSession().createQuery(query);
	}

}

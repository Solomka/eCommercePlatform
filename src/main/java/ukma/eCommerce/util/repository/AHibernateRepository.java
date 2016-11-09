package ukma.eCommerce.util.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * abstract class that has to be extended by each repo hibernate implementation
 * class
 * 
 * @author Solomka
 *
 * @param <Entity>
 *            entity with which Hibernate is working
 * @param <Key>
 *            primary key type
 */
public abstract class AHibernateRepository<Entity, Key extends Serializable> {

	// ***PO.java
	private final Class<Entity> entityClass;

	@SuppressWarnings("unchecked")
	public AHibernateRepository() {
		this.entityClass = (Class<Entity>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	// get element by id
	@SuppressWarnings("unchecked")
	public Entity get(Key key) {
		return (Entity) getSession().get(entityClass, key);
	}

	public void persist(Entity entity) {
		getSession().persist(entity);
	}

	public void update(Entity entity) {
		getSession().update(entity);
	}

	public void delete(Entity entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public Entity loadEntity(Key key) {
		return (Entity) getSession().load(entityClass, key);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(entityClass);
	}

	protected Query createQuery(String query) {
		return getSession().createQuery(query);
	}

}

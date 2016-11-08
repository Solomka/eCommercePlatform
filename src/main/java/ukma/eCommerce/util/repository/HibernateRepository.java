package ukma.eCommerce.util.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateRepository<Entity, Key extends Serializable> {

	private final Class<Entity> entityClass;

	@SuppressWarnings("unchecked")
	public HibernateRepository() {
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

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(entityClass);
	}

	protected Query createQuery(String query) {
		return getSession().createQuery(query);
	}

}

package sigma.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import sigma.dao.GenericDAO;
import sigma.exceptions.DataAccessException;

public abstract class GenericHBDAOImpl<E> extends HibernateDaoSupport implements GenericDAO<E> {

	protected Class<?> domainClass;
	private final Logger LOGGER = LoggerFactory
			.getLogger(GenericHBDAOImpl.class);

	@Override
	public void saveOrUpdate(E e) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(e);
		} catch (Exception exc) {
			LOGGER.error("saveOrUpdate() - Error al guardar Entidad ["
					+ domainClass.getClass() + "]", exc);
			throw new DataAccessException(exc);
		}
	}

	@Override
	public void delete(E e) throws DataAccessException {
		try {
			getHibernateTemplate().delete(e);
		} catch (Exception exc) {
			LOGGER.error(
					"delete() - Error al eliminar Entidad ["
							+ domainClass.getClass() + "]", exc);
			throw new DataAccessException(exc);
		}
	}

	@Override
	public void delete(Long id) throws DataAccessException {
		E e = null;
		try {
			e = (E) getHibernateTemplate().get(domainClass, id);
			getHibernateTemplate().delete(e);
		} catch (Exception exc) {
			LOGGER.error(
					"delete() - Error al eliminar Entidad ["
							+ domainClass.getClass() + "] - ID [" + id + "]",
					exc);
			throw new DataAccessException(exc);
		}
	}

	public List<E> getAll() throws DataAccessException {
		try {
			return (List<E>) getHibernateTemplate().loadAll(domainClass);
		} catch (Exception exc) {
			LOGGER.error("getAll() - Error al obtener todas las Entidades ["
					+ domainClass.getClass() + "]", exc);
			throw new DataAccessException(exc);
		}
	}

	public E getById(Long id) throws DataAccessException {
		try {
			return (E) getHibernateTemplate().get(domainClass, id);
		} catch (Exception exc) {
			LOGGER.error(
					"getById() - Error al obtener Entidad ["
							+ domainClass.getClass() + "] - ID [" + id + "]",
					exc);
			throw new DataAccessException(exc);
		}
	}

	public void setDomainClass(Class<?> domainClass) {
		this.domainClass = domainClass;
	}
	
	protected Session getSession(){
		return currentSession();
	}
}

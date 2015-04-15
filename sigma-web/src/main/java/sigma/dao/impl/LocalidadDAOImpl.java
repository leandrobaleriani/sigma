package sigma.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.dao.LocalidadDAO;
import sigma.entities.Localidad;
import sigma.exceptions.DataAccessException;
import sigma.filters.LocalidadFilter;

public class LocalidadDAOImpl extends GenericHBDAOImpl<Localidad> implements
		LocalidadDAO {

	private final Logger LOGGER = LoggerFactory
			.getLogger(LocalidadDAOImpl.class);

	@Override
	public List<Localidad> search(LocalidadFilter filter)
			throws DataAccessException {

		Long idProvincia = filter.getIdProvincia();

		Criteria criteria = getSession().createCriteria(Localidad.class);
		criteria.createAlias("provincia", "provincia");
		criteria.add(Restrictions.eq("provincia.id", idProvincia));

		try {
			return criteria.list();
		} catch (HibernateException hexc) {
			LOGGER.error("search() - Error al realizar busqueda de Usuarios",
					hexc);
			throw new PersistenceException(hexc);
		}
	}

}

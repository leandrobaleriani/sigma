package sigma.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.dao.PartidoDAO;
import sigma.entities.Partido;
import sigma.exceptions.DataAccessException;
import sigma.filters.PartidoFilter;

public class PartidoDAOImpl extends GenericHBDAOImpl<Partido> implements
		PartidoDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(PartidoDAOImpl.class);

	@Override
	public List<Partido> search(PartidoFilter filter)
			throws DataAccessException {

		Long idProvincia = filter.getIdProvincia();

		Criteria criteria = getSession().createCriteria(Partido.class);
		criteria.createAlias("provincia", "provincia");
		criteria.add(Restrictions.eq("provincia.id", idProvincia));

		try {
			return criteria.list();
		} catch (HibernateException hexc) {
			LOGGER.error("search() - Error al realizar busqueda de Partidos",
					hexc);
			throw new PersistenceException(hexc);
		}
	}

}

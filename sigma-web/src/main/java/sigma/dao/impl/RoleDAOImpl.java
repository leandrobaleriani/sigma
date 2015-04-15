package sigma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.dao.RoleDAO;
import sigma.entities.Role;
import sigma.exceptions.DataAccessException;
import sigma.filters.Order;
import sigma.filters.RoleFilter;

public class RoleDAOImpl extends GenericHBDAOImpl<Role> implements RoleDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(RoleDAOImpl.class);

	@Override
	public List<Role> search(RoleFilter filter) throws DataAccessException {

		try {
			Criteria criteria = buildCriteria(filter);
			return criteria.list();

		} catch (Exception exc) {
			LOGGER.error("search() - Error al realizar busqueda de Usuarios", exc);
			throw new DataAccessException(exc);
		}
	}
	
	private Criteria buildCriteria(RoleFilter filter) {

		Criteria criteria = getSession().createCriteria(Role.class);

		// Filtros
		Long idAplicacion = filter.getIdAplicacion();

		if (null != idAplicacion) {
			criteria.add(Restrictions.eq("idAplicacion", idAplicacion));
		}

		// Ordenamiento
//		Order order = filter.getOrder();
//		if (null != order) {
//			String campoOrdenamiento = filter.getOrder().getCampoOrdenamiento();
//			boolean orderDescendente = filter.getOrder().isDescendente();
//			if (orderDescendente) {
//				criteria.addOrder(org.hibernate.criterion.Order
//						.desc(campoOrdenamiento));
//			} else {
//				criteria.addOrder(org.hibernate.criterion.Order
//						.asc(campoOrdenamiento));
//			}
//		}

		return criteria;
	}

}

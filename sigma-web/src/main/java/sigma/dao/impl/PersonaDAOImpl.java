package sigma.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.common.Utils;
import sigma.dao.PersonaDAO;
import sigma.entities.Persona;
import sigma.exceptions.DataAccessException;
import sigma.filters.PersonaFilter;
import sigma.results.SearchResult;
import sigma.utils.SearchOrder;
import sigma.utils.SearchPage;

public class PersonaDAOImpl extends GenericHBDAOImpl<Persona> implements
		PersonaDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(PersonaDAOImpl.class);

	@Override
	public SearchResult<Persona> search(PersonaFilter filter)
			throws DataAccessException {
		SearchResult<Persona> searchResult = new SearchResult<Persona>();
		try {
			Criteria criteria = buildCriteria(filter);
			criteria.setProjection(Projections.rowCount());
			long count = (Long) criteria.uniqueResult();
			List<Persona> result = null;

			if (count > 0) {

				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

				SearchOrder ordenamiento = filter.getSearchOrder();
				if (null != ordenamiento) {
					String orderBy = ordenamiento.getCampo();
					String order = ordenamiento.getOrden();

					if (Utils.isNotEmptyString(orderBy)) {
						criteria.createAlias("obraSocial", "obraSocial");
						criteria.createAlias("localidad", "localidad");
						boolean asc = order.equalsIgnoreCase(SearchOrder.ASC);
						if (asc) {
							if (orderBy.equals("nombreApellido")) {
								criteria.addOrder(Order.asc("apellido"));
								criteria.addOrder(Order.asc("nombre"));
							} else {
								criteria.addOrder(Order.asc(orderBy));
							}
						} else {
							if (orderBy.equals("nombreApellido")) {
								criteria.addOrder(Order.desc("apellido"));
								criteria.addOrder(Order.desc("nombre"));
							} else {
								criteria.addOrder(Order.desc(orderBy));
							}
						}
					}
				}

				SearchPage searchPage = filter.getSearchPage();
				if (null != searchPage) {
					int pageSize = searchPage.getPageSize();
					if (count > pageSize) {
						criteria.setFirstResult(searchPage.getFrom());
						criteria.setMaxResults(pageSize);
					}
				}
				result = criteria.list();
			}
			searchResult.setData(result);
			searchResult.setTotalResults(count);
			return searchResult;
		} catch (HibernateException hexc) {
			LOGGER.error("search() - Error al realizar busqueda de Usuarios",
					hexc);
			throw new PersistenceException(hexc);
		}
	}

	private Criteria buildCriteria(PersonaFilter filter) {

		Criteria criteria = getSession().createCriteria(Persona.class);

		// Filtros
		String campoBusqueda = filter.getCampoBusqueda();

		if (Utils.isNotEmptyString(campoBusqueda)) {
			Disjunction disj = Restrictions.disjunction();
			Integer dni = Utils.convertStringToInteger(campoBusqueda);
			if (null != dni) {
				disj.add(Restrictions.eq("dni", dni.longValue()));
			}
			disj.add(Restrictions.like("nombre", campoBusqueda,
					MatchMode.ANYWHERE));
			disj.add(Restrictions.like("apellido", campoBusqueda,
					MatchMode.ANYWHERE));
			criteria.add(disj);
		}

		return criteria;
	}

}

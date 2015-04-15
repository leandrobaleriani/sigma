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
import sigma.dao.AtencionDAO;
import sigma.entities.Atencion;
import sigma.entities.Persona;
import sigma.exceptions.DataAccessException;
import sigma.filters.AtencionFilter;
import sigma.filters.PersonaFilter;
import sigma.results.SearchResult;
import sigma.utils.SearchOrder;
import sigma.utils.SearchPage;

public class AtencionDAOImpl extends GenericHBDAOImpl<Atencion> implements
		AtencionDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(PersonaDAOImpl.class);

	@Override
	public List<Atencion> search(AtencionFilter filter)
			throws DataAccessException {
		try {
			Criteria criteria = buildCriteria(filter);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<Atencion> result = null;

			SearchOrder ordenamiento = filter.getSearchOrder();
			if (null != ordenamiento) {
				String orderBy = ordenamiento.getCampo();
				String order = ordenamiento.getOrden();

				if (Utils.isNotEmptyString(orderBy)) {
					boolean asc = order.equalsIgnoreCase(SearchOrder.ASC);
					if (asc) {
						if (orderBy.equals("atencion")) {
							criteria.addOrder(Order.asc("fechaRecepcion"));
							criteria.addOrder(Order.asc("inicioAtencion"));
						} else {
							criteria.addOrder(Order.asc(orderBy));
						}
					} else {
						if (orderBy.equals("atencion")) {
							criteria.addOrder(Order.desc("fechaRecepcion"));
							criteria.addOrder(Order.desc("inicioAtencion"));
						} else {
							criteria.addOrder(Order.desc(orderBy));
						}
					}
				}
			}
			result = criteria.list();
			return result;
		} catch (HibernateException hexc) {
			LOGGER.error("search() - Error al realizar busqueda de Atenciones",
					hexc);
			throw new PersistenceException(hexc);
		}
	}

	private Criteria buildCriteria(AtencionFilter filter) {

		Criteria criteria = getSession().createCriteria(Atencion.class);

		// Filtros
		Boolean finalizado = filter.getFinalizado();
		Long idLugarAtencion = filter.getIdLugarAtencion();

		if (null != finalizado) {
			if (finalizado) {
				criteria.add(Restrictions.isNotNull("finAtencion"));
			} else {
				criteria.add(Restrictions.isNull("finAtencion"));
			}
		}
		
		if (null == idLugarAtencion) {
			criteria.add(Restrictions.eq("idLugarAtencion", idLugarAtencion));
		}

		return criteria;
	}

}

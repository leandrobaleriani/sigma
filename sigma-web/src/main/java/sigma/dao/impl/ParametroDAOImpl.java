package sigma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import sigma.dao.ParametroDAO;
import sigma.entities.Parametro;
import sigma.exceptions.DataAccessException;

public class ParametroDAOImpl extends GenericHBDAOImpl<Parametro> implements
		ParametroDAO {

	private final Logger LOGGER = LoggerFactory
			.getLogger(ParametroDAOImpl.class);

	@Override
	public int getMaxAttempts() throws DataAccessException {
		try {
			Criteria criteria = getSession().createCriteria(Parametro.class);
			criteria.add(Restrictions.eq("nombre", "MAX_ATTEMPTS"));
			List<Parametro> parametros = criteria.list();
			if (!CollectionUtils.isEmpty(parametros)) {
				Parametro parametro = parametros.get(0);
				return Integer.valueOf(parametro.getValor());
			}
		} catch (Exception exc) {
			LOGGER.error("getMaxAttempts() - Error al obtener Max Attempts", exc);
			throw new DataAccessException(exc);
		}
		return 0;
	}

}

package sigma.bo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.UrgenciaBO;
import sigma.dao.UserDAO;
import sigma.entities.User;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.UserFilter;

public class UrgenciaBOImpl implements UrgenciaBO {

	private final Logger LOGGER = LoggerFactory.getLogger(UrgenciaBOImpl.class);
	private UserDAO userDAO;

	@Override
	public List<User> obtenerEnAtencion() throws BusinessException {
		try {
			UserFilter filter = new UserFilter();
			filter.setEnUrgencia(Boolean.FALSE);
			filter.setPrestador(Boolean.TRUE);
			return userDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar busqueda de Usuarios", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<User> obtenerEnUrgencia() throws BusinessException {
		try {
			UserFilter filter = new UserFilter();
			filter.setEnUrgencia(Boolean.TRUE);
			filter.setPrestador(Boolean.TRUE);
			return userDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar busqueda de Usuarios", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void establecerUrgencia(List<Long> idsUsuario)
			throws BusinessException {
		try {
			if (null != idsUsuario) {
				for (Long idUsuario : idsUsuario) {
					User user = userDAO.getById(idUsuario);
					user.setUrgencia(Boolean.TRUE);
					userDAO.saveOrUpdate(user);
				}
			}
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al registrar urgencia", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void finalizarUrgencia(List<Long> idsUsuario)
			throws BusinessException {
		try {
			if (null != idsUsuario) {
				for (Long idUsuario : idsUsuario) {
					User user = userDAO.getById(idUsuario);
					user.setUrgencia(Boolean.FALSE);
					userDAO.saveOrUpdate(user);
				}
			}
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al finalizar urgencia", daexc);
			throw new BusinessException(daexc);
		}
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}

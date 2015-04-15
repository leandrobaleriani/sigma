package sigma.bo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import sigma.bo.UserBO;
import sigma.dao.UserDAO;
import sigma.entities.User;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.UserFilter;

public class UserBOImpl implements UserBO {

	private final Logger LOGGER = LoggerFactory.getLogger(UserBOImpl.class);
	private UserDAO userDAO;
	private String defaultPassword;

	@Override
	public List<User> search(UserFilter filter) throws BusinessException {
		try {
			return userDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar busqueda de Usuarios", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void saveOrUpdate(User user) throws BusinessException {
		if (user.getId() == null) { 
			user.setPassword(defaultPassword);
			user.setActivo(Boolean.TRUE);
			user.setAttempts(0);
			user.setLocked(Boolean.FALSE);
		}
		try {
			userDAO.saveOrUpdate(user);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Usuario", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public User getById(Long idUsuario) throws BusinessException {
		try {
			return userDAO.getById(idUsuario);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Usuario", daexc);
			throw new BusinessException(daexc);
		}
	}
	
	@Override
	public UserDetails login(String userName) throws BusinessException {
		try {
			return userDAO.login(userName);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar Login", daexc);
			throw new BusinessException(daexc);
		}
	}
	
	@Override
	public void updateFailAttempts(String userName) throws BusinessException {
		try {
			userDAO.updateFailAttempts(userName);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al registrar intentos fallidos de Login", daexc);
			throw new BusinessException(daexc);
		}	
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

}

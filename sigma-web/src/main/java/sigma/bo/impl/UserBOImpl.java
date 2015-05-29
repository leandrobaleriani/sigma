package sigma.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import sigma.bo.UserBO;
import sigma.dao.UserDAO;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;

public class UserBOImpl implements UserBO {

	private final Logger LOGGER = LoggerFactory.getLogger(UserBOImpl.class);
	private UserDAO userDAO;

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
	public void actualizarIntentosFallidos(String userName)
			throws BusinessException {
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

}

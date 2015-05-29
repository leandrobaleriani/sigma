package sigma.bo;

import org.springframework.security.core.userdetails.UserDetails;

import sigma.exceptions.BusinessException;

public interface UserBO {

	UserDetails login(String userName) throws BusinessException;

	void actualizarIntentosFallidos(String userName) throws BusinessException;
}

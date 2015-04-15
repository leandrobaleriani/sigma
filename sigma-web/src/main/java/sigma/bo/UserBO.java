package sigma.bo;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import sigma.entities.User;
import sigma.exceptions.BusinessException;
import sigma.filters.UserFilter;

public interface UserBO {

	List<User> search(UserFilter filter) throws BusinessException;

	void saveOrUpdate(User user) throws BusinessException;

	User getById(Long idUsuario) throws BusinessException;

	UserDetails login(String userName) throws BusinessException;
	
	void updateFailAttempts(String userName) throws BusinessException;
}

package sigma.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import sigma.entities.Role;
import sigma.entities.User;
import sigma.exceptions.DataAccessException;
import sigma.filters.UserFilter;

public interface UserDAO extends GenericDAO<User> {
	
	List<User> search(UserFilter filter) throws DataAccessException;
	
	List<Role> getAllRoles() throws DataAccessException;

	UserDetails login(String userName) throws DataAccessException;

	void updateFailAttempts(String userName) throws DataAccessException;
}

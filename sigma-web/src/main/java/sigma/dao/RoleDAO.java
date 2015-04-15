package sigma.dao;

import java.util.List;

import sigma.entities.Role;
import sigma.exceptions.DataAccessException;
import sigma.filters.RoleFilter;

public interface RoleDAO extends GenericDAO<Role> {

	List<Role> search(RoleFilter filter) throws DataAccessException;

}

package sigma.bo;

import java.util.List;

import sigma.entities.Role;
import sigma.exceptions.BusinessException;
import sigma.filters.RoleFilter;

public interface RoleBO {

	List<Role> search(RoleFilter filter) throws BusinessException;

	void saveOrUpdate(Role role) throws BusinessException;
	
	List<Role> getAll() throws BusinessException;

}

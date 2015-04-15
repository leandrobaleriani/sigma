package sigma.bo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.RoleBO;
import sigma.dao.RoleDAO;
import sigma.entities.Role;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.RoleFilter;

public class RoleBOImpl implements RoleBO {

	private final Logger LOGGER = LoggerFactory.getLogger(RoleBOImpl.class);
	private RoleDAO roleDAO;

	@Override
	public List<Role> search(RoleFilter filter) throws BusinessException {
		try {
			return roleDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar busqueda de Roles", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void saveOrUpdate(Role role) throws BusinessException {
		try {
			roleDAO.saveOrUpdate(role);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Rol", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Role> getAll() throws BusinessException {
		try {
			return roleDAO.getAll();
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Roles", daexc);
			throw new BusinessException(daexc);
		}
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}

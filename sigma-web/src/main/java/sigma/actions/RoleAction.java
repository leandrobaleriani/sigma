package sigma.actions;

import sigma.bo.RoleBO;
import sigma.entities.Role;

public class RoleAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private RoleBO roleBO;
	private Long idAplicacion;
	private Role rol;
	private String[] rolesUsuario;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String search() throws Exception {
		// UserFilter filter = new UserFilter();
		// filter.setCampoBusqueda(campoBusqueda);
		// List<User> users = userBO.search(filter);
		// getServletRequest().setAttribute("users", users);
		return "result";
	}

	public String showNew() throws Exception {
		// List<Role> roles = userBO.getAllRoles();
		// getServletRequest().setAttribute("roles", roles);
		return "new";
	}

	public String save() throws Exception {

		int a = 0;

		// userBO.saveOrUpdate(user);
		return SUCCESS;
	}

	public String showEdit() throws Exception {
		return SUCCESS;
	}

	public String activateDesactivate() throws Exception {
		return SUCCESS;
	}

	public String unlock() throws Exception {
		return SUCCESS;
	}

	public String[] getRolesUsuario() {
		return rolesUsuario;
	}

	public void setRolesUsuario(String[] rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	public void setRoleBO(RoleBO roleBO) {
		this.roleBO = roleBO;
	}

}

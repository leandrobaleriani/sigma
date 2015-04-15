package sigma.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sigma.bo.RoleBO;
import sigma.bo.UserBO;
import sigma.entities.Role;
import sigma.entities.User;
import sigma.filters.UserFilter;

public class UserAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private UserBO userBO;
	private RoleBO roleBO;
	private UserFilter userFilter;
	private User user;
	private Long[] rolesUsuario;
	private String selectedItem;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String search() throws Exception {
		List<User> users = userBO.search(userFilter);
		getServletRequest().setAttribute("users", users);
		return "result";
	}

	public String showNew() throws Exception {
		List<Role> roles = roleBO.getAll();
		getServletRequest().setAttribute("roles", roles);
		return "new";
	}

	public String save() throws Exception {
		boolean exito = Boolean.FALSE;
		boolean validacion = Boolean.TRUE;
		String mensaje = "";
//		try {
			// user.setRoles(getSelectedRoles());
//			 userBO.saveOrUpdate(user);
//			 mensaje = getText("operacion.exito");
			String mensajeValidacion = "El DNI ingresado ya existe en el Sistema|Debe ingresar un Nombre y Apellido";
			 exito = Boolean.FALSE;
			 validateUser();
//		} catch (BusinessException bexc) {
//			if (bexc.getTypeError().equals(TypeError.MENSAJE)) {
//				mensaje = bexc.getMessage();
//			}
//		}
		getServletRequest().setAttribute("mensaje", mensaje);
		getServletRequest().setAttribute("mensajeValidacion", mensajeValidacion);
		getServletRequest().setAttribute("exito", exito);
		getServletRequest().setAttribute("validacion", validacion);
		return "respResult";
	}

	public String showEdit() throws Exception {
		Long idUsuario = Long.valueOf(selectedItem);
		user = userBO.getById(idUsuario);
		rolesUsuario = getRolesUsuario(user);
		return SUCCESS;
	}

	private String validateUser(){
		
		List<String> validaciones = new ArrayList<String>();
		
		
		
		
		return "";
	}
	
	private Set<Role> getSelectedRoles() {
		Set<Role> roles = new HashSet<Role>();
		for (Long idUsuario : rolesUsuario) {
			roles.add(new Role(idUsuario));
		}
		return roles;
	}

	private Long[] getRolesUsuario(User user) {
		List<Long> idsRoles = new ArrayList<Long>();
		Set<Role> roles = user.getRoles();
		if (null != roles) {
			for (Role role : roles) {
				idsRoles.add(role.getId());
			}
		}
		return (Long[]) idsRoles.toArray();
	}

	public String activateDesactivate() throws Exception {
		return SUCCESS;
	}

	public String unlock() throws Exception {
		return SUCCESS;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long[] getRolesUsuario() {
		return rolesUsuario;
	}

	public void setRolesUsuario(Long[] rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	public void setRoleBO(RoleBO roleBO) {
		this.roleBO = roleBO;
	}

	public UserFilter getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(UserFilter userFilter) {
		this.userFilter = userFilter;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	public void validate() {
	
		int a = 0;
		
		if (a > 0) {
			
		}
		
	}
	
}

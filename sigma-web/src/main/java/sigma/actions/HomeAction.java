package sigma.actions;

import java.util.List;

import sigma.authentication.entities.AteneaUser;
import sigma.authentication.entities.AtheneaRole;
import sigma.entities.LugarAtencion;

public class HomeAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 8510842817663365742L;
	private Long seleccionLugarAtencion;

	@Override
	public String execute() throws Exception {

		AteneaUser user = getLoggedUser();
		if (null != user) {
			getSession().put("loggedUser", user);
			List<LugarAtencion> lugaresAtencion = user.getLugaresAtencion();
			setLugarAtencion(lugaresAtencion.get(0));
		}
		return SUCCESS;
	}

	public String showSeleccionarLugarAtencion() throws Exception {
		return SUCCESS;
	}

	public String seleccionarLugarAtencion() throws Exception {
		return SUCCESS;
	}

	public Long getSeleccionLugarAtencion() {
		return seleccionLugarAtencion;
	}

	public void setSeleccionLugarAtencion(Long seleccionLugarAtencion) {
		this.seleccionLugarAtencion = seleccionLugarAtencion;
	}

}

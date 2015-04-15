package sigma.actions;

import java.util.List;

import sigma.entities.LugarAtencion;

public class HomeAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 8510842817663365742L;
	private Long seleccionLugarAtencion;

	@Override
	public String execute() throws Exception {
		String loggedUser = getLoggedUser().getNombreCompleto();
		getSession().put("loggedUser", loggedUser);
		List<LugarAtencion> lugaresAtencion = getLoggedUser()
				.getLugaresAtencion();
		if (null != seleccionLugarAtencion) {
			for (LugarAtencion lugarAtencion : lugaresAtencion) {
				if (lugarAtencion.getId().equals(seleccionLugarAtencion)) {
					setLugarAtencion(lugarAtencion);
					break;
				}
			}
		} else {
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

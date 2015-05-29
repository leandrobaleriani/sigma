package sigma.actions;

import java.util.Arrays;
import java.util.List;

import sigma.bo.UrgenciaBO;
import sigma.entities.User;
import sigma.exceptions.BusinessException;
import sigma.exceptions.BusinessException.TypeError;

public class UrgenciaAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private UrgenciaBO urgenciaBO;
	private Long[] selectedItems;

	private String RESULT_URGENCIAS = "result";
	private String ADD_URGENCIA = "agregarUrgencias";

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String showEnUrgencias() throws Exception {

		List<User> users = urgenciaBO.obtenerEnUrgencia();
		getServletRequest().setAttribute("users", users);
		return RESULT_URGENCIAS;
	}

	public String showAgregarUrgencia() throws Exception {
		List<User> users = urgenciaBO.obtenerEnAtencion();
		getServletRequest().setAttribute("users", users);
		return ADD_URGENCIA;
	}

	public String addUrgencia() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		try {
			List<Long> idsUsuario = Arrays.asList(selectedItems);
			urgenciaBO.establecerUrgencia(idsUsuario);
			mensaje = getText("operacion.exito");
			exito = Boolean.TRUE;
		} catch (BusinessException bexc) {
			if (bexc.getTypeError().equals(TypeError.MENSAJE)) {
				mensaje = bexc.getMessage();
			}
		}
		createJSONResponse(exito, mensaje, null);
		return JSON;
	}

	public String finalizarUrgencia() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		try {
			List<Long> idsUsuario = Arrays.asList(selectedItems);
			urgenciaBO.finalizarUrgencia(idsUsuario);
			mensaje = getText("operacion.exito");
			exito = Boolean.TRUE;
		} catch (BusinessException bexc) {
			if (bexc.getTypeError().equals(TypeError.MENSAJE)) {
				mensaje = bexc.getMessage();
			}
		}
		createJSONResponse(exito, mensaje, null);
		return JSON;
	}

	public Long[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(Long[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public void setUrgenciaBO(UrgenciaBO urgenciaBO) {
		this.urgenciaBO = urgenciaBO;
	}

}

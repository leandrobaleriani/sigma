package sigma.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sigma.bo.AtencionBO;
import sigma.common.Utils;
import sigma.entities.Atencion;
import sigma.exceptions.BusinessException;
import sigma.exceptions.BusinessException.TypeError;
import sigma.utils.ValidationError;

public class AtencionAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private AtencionBO atencionBO;

	private Atencion atencion;
	private Long idAtencion;
	private String diagnostico;
	private boolean internacion;
	private Long idPersona;

	private String RESULT = "result";
	private String ATENCION = "atencion";
	private String RESULT_HISTORIAL = "resultHistorial";

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String showEspera() throws Exception {

		List<Atencion> atenciones = atencionBO.obtenerEnEspera();

		getServletRequest().setAttribute("atenciones", atenciones);

		return RESULT;
	}

	public String showAtencion() throws Exception {

		List<Atencion> atenciones = atencionBO.obtenerEnAtencion();

		getServletRequest().setAttribute("atenciones", atenciones);

		return ATENCION;
	}

	public String atender() throws Exception {
		boolean exito = atencionBO.atender(idAtencion, getLoggedUser().getId());
		String mensaje = "";
		if (!exito) {
			mensaje = "El Paciente ya se encuentra en Atención";
		}
		createJSONResponse(exito, mensaje, null);
		return JSON;
	}

	public String diagnosticar() throws Exception {
		Atencion atencion = atencionBO.obtener(idAtencion);
		setAtencion(atencion);

		List<Atencion> atenciones = atencionBO.obtenerHistorial(atencion
				.getIdPersona());

		if (null != atenciones && null != idAtencion) {
			Iterator<Atencion> iter = atenciones.iterator();
			while (iter.hasNext()) {
				if (iter.next().getId().equals(idAtencion)) {
					iter.remove();
					break;
				}
			}
		}

		getSession().put("historialAtenciones", atenciones);

		return ATENCION;
	}

	public String paginarHistorial() throws Exception {
		return RESULT_HISTORIAL;
	}

	public String finalizar() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		List<ValidationError> validaciones = validarAtencion();
		if (Utils.isEmptyCollection(validaciones)) {
			try {
				atencionBO.finalizar(idAtencion, diagnostico, internacion);
				mensaje = getText("operacion.exito");
				exito = Boolean.TRUE;
			} catch (BusinessException bexc) {
				if (bexc.getTypeError().equals(TypeError.MENSAJE)) {
					mensaje = bexc.getMessage();
				}
			}
		}
		createJSONResponse(exito, mensaje, validaciones);
		return JSON;
	}

	public String finalizarAusencia() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		try {
			atencionBO.finalizarMotivo(idAtencion, "AUSENCIA", getLoggedUser()
					.getId());
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

	public String finalizarAtencion() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		try {
			atencionBO.finalizarMotivo(idAtencion, "CASO SIN CERRAR",
					getLoggedUser().getId());
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

	private List<ValidationError> validarAtencion() {
		List<ValidationError> validaciones = new ArrayList<ValidationError>();

		if (null == diagnostico || diagnostico.isEmpty()) {
			validaciones.add(new ValidationError("diagnostico",
					"Debe ingresar un Diagnóstico"));
		}
		return validaciones;
	}

	public String showAtencionesPendientes() throws Exception {
		List<Atencion> atenciones = atencionBO
				.obtenerPendientesPorUsuario(getLoggedUser().getId());
		getServletRequest().setAttribute("atencionesPendientes", atenciones);
		return RESULT;
	}

	public String showAtencionesUltimas() throws Exception {
		List<Atencion> atenciones = atencionBO
				.obtenerUltimasAtenciones(getLoggedUser().getId());
		getServletRequest().setAttribute("atencionesUltimas", atenciones);
		return RESULT;
	}

	public void setAtencionBO(AtencionBO atencionBO) {
		this.atencionBO = atencionBO;
	}

	public Atencion getAtencion() {
		return atencion;
	}

	public void setAtencion(Atencion atencion) {
		this.atencion = atencion;
	}

	public Long getIdAtencion() {
		return idAtencion;
	}

	public void setIdAtencion(Long idAtencion) {
		this.idAtencion = idAtencion;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public boolean isInternacion() {
		return internacion;
	}

	public void setInternacion(boolean internacion) {
		this.internacion = internacion;
	}

}

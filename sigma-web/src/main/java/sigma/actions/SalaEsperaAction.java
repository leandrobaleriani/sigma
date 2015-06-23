package sigma.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import sigma.bo.AtencionBO;
import sigma.bo.ParametricoBO;
import sigma.bo.UrgenciaBO;
import sigma.common.Utils;
import sigma.dto.AtencionDTO;
import sigma.entities.Atencion;
import sigma.entities.LugarAtencion;
import sigma.entities.SexoEnum;
import sigma.entities.User;
import sigma.utils.AtencionComparator;

public class SalaEsperaAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private static final String RESULT = "result";

	private AtencionBO atencionBO;
	private ParametricoBO parametricoBO;
	private UrgenciaBO urgenciaBO;
	private Long seleccionLugarAtencion;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String showSalaEspera() throws Exception {

		List<Atencion> atenciones = atencionBO.obtenerEnEsperaEnAtencion();
		List<AtencionDTO> atencionesDTO = new ArrayList<AtencionDTO>();
		if (Utils.isNotEmptyCollection(atenciones)) {

			Collections.sort(atenciones, new AtencionComparator());

			for (Atencion atencion : atenciones) {
				atencionesDTO.add(new AtencionDTO(atencion.getPersona()
						.getNombreCompleto(), atencion.getTipoAtencion()
						.getDescripcion(),
						atencion.getInicioAtencion() == null, Utils
								.convertToFechaHora(atencion
										.getFechaRecepcion()), Utils
								.convertToFechaHora(atencion
										.getInicioAtencion()), atencion.getId()
								+ ""));
			}
		}

		List<User> usuariosUrgencia = urgenciaBO.obtenerEnUrgencia();
		List<String> urgencias = new ArrayList<String>();
		if (null != usuariosUrgencia) {
			for (User user: usuariosUrgencia) {
				if (user.getPersona().getSexo().equals(SexoEnum.MASCULINO)) {
					urgencias.add("Dr. " + user.getPersona().getNombreCompleto());
				} else {
					urgencias.add("Dra. " + user.getPersona().getNombreCompleto());
				}
			}
		}

		LinkedHashMap<String, Object> jsonResponse = createJSONResponse();
		jsonResponse.put("data", atencionesDTO);
		jsonResponse.put("urgencias", urgencias);
		return JSON;
	}

	public String showSeleccionarLugarAtencion() throws Exception {
		List<LugarAtencion> lugaresAtencion = parametricoBO
				.getAllLugaresAtencion();
		getSession().put("lstLugaresAtencion", lugaresAtencion);
		return SUCCESS;
	}

	public String seleccionarLugarAtencion() throws Exception {
		List<LugarAtencion> lugaresAtencion = (List<LugarAtencion>) getSession()
				.get("lstLugaresAtencion");
		LugarAtencion lugarAtencionSeleccion = null;
		for (LugarAtencion lugarAtencion : lugaresAtencion) {
			if (lugarAtencion.getId().equals(seleccionLugarAtencion)) {
				lugarAtencionSeleccion = lugarAtencion;
				break;
			}
		}
		getSession().put("lugarAtencionMonitor", lugarAtencionSeleccion);
		createCustomJSONResponse(lugarAtencionSeleccion);
		return JSON;
	}

	public String getSalaEspera() throws Exception {

		int enEspera = 0;
		int enAtencion = 0;

		List<Atencion> atenciones = atencionBO.obtenerEnEsperaEnAtencion();
		List<AtencionDTO> atencionesDTO = new ArrayList<AtencionDTO>();
		if (Utils.isNotEmptyCollection(atenciones)) {

			Collections.sort(atenciones, new AtencionComparator());

			AtencionDTO atencionDTO = null;
			for (Atencion atencion : atenciones) {
				atencionDTO = new AtencionDTO(atencion.getPersona()
						.getNombreCompleto(), atencion.getTipoAtencion()
						.getDescripcion(),
						atencion.getInicioAtencion() == null,
						Utils.convertToFechaHora(atencion.getFechaRecepcion()),
						Utils.convertToFechaHora(atencion.getInicioAtencion()),
						atencion.getId() + "");
				atencionesDTO.add(atencionDTO);
				if (atencionDTO.isEnEspera()) {
					enEspera++;
				} else {
					enAtencion++;
				}
			}
		}

		getServletRequest().setAttribute("enEspera", enEspera);
		getServletRequest().setAttribute("enAtencion", enAtencion);
		getServletRequest().setAttribute("atenciones", atencionesDTO);
		return RESULT;
	}

	public void setParametricoBO(ParametricoBO parametricoBO) {
		this.parametricoBO = parametricoBO;
	}

	public void setAtencionBO(AtencionBO atencionBO) {
		this.atencionBO = atencionBO;
	}

	public Long getSeleccionLugarAtencion() {
		return seleccionLugarAtencion;
	}

	public void setSeleccionLugarAtencion(Long seleccionLugarAtencion) {
		this.seleccionLugarAtencion = seleccionLugarAtencion;
	}

	public void setUrgenciaBO(UrgenciaBO urgenciaBO) {
		this.urgenciaBO = urgenciaBO;
	}

}

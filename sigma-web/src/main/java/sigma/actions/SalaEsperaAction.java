package sigma.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import sigma.bo.AtencionBO;
import sigma.bo.ParametricoBO;
import sigma.common.Utils;
import sigma.dto.AtencionDTO;
import sigma.entities.Atencion;
import sigma.entities.LugarAtencion;

public class SalaEsperaAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private static final String RESULT = "result";
	
	private AtencionBO atencionBO;
	private ParametricoBO parametricoBO;
	private Long seleccionLugarAtencion;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String showSalaEspera() throws Exception {

//		LugarAtencion lugarAtencion = (LugarAtencion) getSession().get(
//				"lugarAtencionMonitor");
//		if (null == lugarAtencion) {
//			List<LugarAtencion> lugaresAtencion = (List<LugarAtencion>) getSession()
//					.get("lstLugaresAtencion");
//			if (null == lugaresAtencion) {
//				lugaresAtencion = parametricoBO.getAllLugaresAtencion();
//				getSession().put("lstLugaresAtencion", lugaresAtencion);
//			}
//			lugarAtencion = lugaresAtencion.get(0);
//			getSession().put("lugarAtencionMonitor", lugarAtencion);
//		}
		
//		Long idLugarAtencion = lugarAtencion.getId();
		
		List<Atencion> atenciones = atencionBO.obtenerEnEsperaEnAtencion();
		List<AtencionDTO> atencionesDTO = new ArrayList<AtencionDTO>();
		if (Utils.isNotEmptyCollection(atenciones)) {

			Collections.sort(atenciones, new Comparator<Atencion>() {
				@Override
				public int compare(Atencion o1, Atencion o2) {

					Date inicioAtencion1 = o1.getInicioAtencion();
					Date inicioAtencion2 = o2.getInicioAtencion();

					Date recepcion1 = o1.getFechaRecepcion();
					Date recepcion2 = o2.getFechaRecepcion();

					int result = 0;

					if (null == inicioAtencion1 && null == inicioAtencion2) {
						result = recepcion1.compareTo(recepcion2);
					} else if (null == inicioAtencion1) {
						result = 1;
					} else if (null == inicioAtencion2) {
						result = -1;
					} else {
						result = inicioAtencion1.compareTo(inicioAtencion2);
					}

					return result;
				}
			});

			for (Atencion atencion : atenciones) {
				atencionesDTO
						.add(new AtencionDTO(atencion.getPersona()
								.getNombreCompleto(), atencion
								.getTipoAtencion().getDescripcion(), atencion
								.getInicioAtencion() == null, Utils.convertToFechaHora(atencion.getFechaRecepcion())
								, Utils.convertToFechaHora(atencion.getInicioAtencion()), atencion.getId() + ""));
			}
		}
		LinkedHashMap<String, Object> jsonResponse = createJSONResponse();
		jsonResponse.put("data", atencionesDTO);
		jsonResponse.put("usuariosUrgencia", atencionesDTO);
//		jsonResponse.put("lugarAtencion", lugarAtencion.getNombre());
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

			Collections.sort(atenciones, new Comparator<Atencion>() {
				@Override
				public int compare(Atencion o1, Atencion o2) {

					Date inicioAtencion1 = o1.getInicioAtencion();
					Date inicioAtencion2 = o2.getInicioAtencion();

					Date recepcion1 = o1.getFechaRecepcion();
					Date recepcion2 = o2.getFechaRecepcion();

					int result = 0;

					if (null == inicioAtencion1 && null == inicioAtencion2) {
						result = recepcion1.compareTo(recepcion2);
					} else if (null == inicioAtencion1) {
						result = 1;
					} else if (null == inicioAtencion2) {
						result = -1;
					} else {
						result = inicioAtencion1.compareTo(inicioAtencion2);
					}

					return result;
				}
			});

			AtencionDTO atencionDTO = null;
			for (Atencion atencion : atenciones) {
				atencionDTO = new AtencionDTO(atencion.getPersona()
						.getNombreCompleto(), atencion
						.getTipoAtencion().getDescripcion(), atencion
						.getInicioAtencion() == null, Utils.convertToFechaHora(atencion.getFechaRecepcion())
						, Utils.convertToFechaHora(atencion.getInicioAtencion()), atencion.getId() + "");
				atencionesDTO
						.add(atencionDTO);
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

}

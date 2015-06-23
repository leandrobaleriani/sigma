package sigma.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sigma.bo.AtencionBO;
import sigma.bo.ParametricoBO;
import sigma.bo.PersonaBO;
import sigma.common.Utils;
import sigma.entities.Barrio;
import sigma.entities.ObraSocial;
import sigma.entities.Persona;
import sigma.entities.Provincia;
import sigma.entities.SexoEnum;
import sigma.entities.TipoAtencionEnum;
import sigma.entities.TipoDocEnum;
import sigma.exceptions.BusinessException;
import sigma.exceptions.BusinessException.TypeError;
import sigma.filters.PersonaFilter;
import sigma.helpers.PaginationHelper;
import sigma.results.SearchResult;
import sigma.utils.SearchOrder;
import sigma.utils.SearchPage;
import sigma.utils.ValidationError;

public class PersonaAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private PersonaBO personaBO;
	private ParametricoBO parametricoBO;
	private AtencionBO atencionBO;

	private PersonaFilter personaFilter;
	private Persona persona;
	private String selectedItem;
	private PaginationHelper paginationHelper;
	private Long idProvincia;
	private TipoAtencionEnum tipoAtencion;
	private static final String RECEPCIONAR = "recepcionar";

	@Override
	public String execute() throws Exception {
		getServletRequest().setAttribute("totalResult", 0);
		return SUCCESS;
	}

	public String search() throws Exception {
		SearchOrder searchOrder = paginationHelper.getSearchOrder(
				getServletRequest(), "personas");
		SearchPage searchPage = paginationHelper.getSearchPage(
				getServletRequest(), "personas");

		personaFilter.setSearchOrder(searchOrder);
		personaFilter.setSearchPage(searchPage);

		SearchResult<Persona> result = personaBO.buscarPacientes(personaFilter);

		List<Persona> personas = result.getData();
		int totalResult = result.getTotalResults();

		getServletRequest().setAttribute("personas", personas);
		getServletRequest().setAttribute("totalResult", totalResult);
		return "result";
	}

	public String showNew() throws Exception {

		persona = new Persona();

		loadParametricos();

		return NEW;
	}

	public String showRecepcionar() throws Exception {
		Long idPersona = Long.valueOf(selectedItem);
		persona = personaBO.obtener(idPersona);
		loadParametricos();
		return RECEPCIONAR;
	}

	public String recepcionar() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		List<ValidationError> validaciones = validarPersona();

		if (Utils.isEmptyCollection(validaciones)) {
			try {
				if (null != persona.getId()) {
					atencionBO.recepcionarActualizar(getLoggedUser().getId(),
							tipoAtencion, persona, getLugarAtencion().getId());
				} else {
					atencionBO.recepcionarIngresar(getLoggedUser().getId(),
							tipoAtencion, persona, getLugarAtencion().getId());
				}
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

	public String save() throws Exception {
		boolean exito = Boolean.FALSE;
		String mensaje = "";
		List<ValidationError> validaciones = validarPersona();
		if (Utils.isEmptyCollection(validaciones)) {
			try {
				if (null != persona.getId()) {
					personaBO.actualizar(persona);
				} else {
					personaBO.ingresar(persona);
				}
				mensaje = getText("operacion.exito");
				exito = Boolean.TRUE;
			} catch (BusinessException bexc) {
				bexc.printStackTrace();
				if (bexc.getTypeError().equals(TypeError.MENSAJE)) {
					mensaje = bexc.getMessage();
				}
			}
		}
		createJSONResponse(exito, mensaje, validaciones);
		return JSON;
	}


	private List<ValidationError> validarPersona() {

		List<ValidationError> validaciones = new ArrayList<ValidationError>();

		Long documento = persona.getDoc();
		String nombre = persona.getNombre();
		String apellido = persona.getApellido();
		Date fechaNacimiento = persona.getFechaNacimiento();
		String direccion = persona.getDireccion();
		Long idBarrio = persona.getIdBarrio();
		Long idLocalidad = persona.getIdLocalidad();
		String cp = persona.getCodPostal();

		if (null == documento) {
			validaciones.add(new ValidationError("persona_documento",
					"Debe ingresar un Nro. de Documento"));
		}
		if (Utils.isEmptyString(nombre)) {
			validaciones.add(new ValidationError("persona_nombre",
					"Debe ingresar un Nombre"));
		}
		if (Utils.isEmptyString(apellido)) {
			validaciones.add(new ValidationError("persona_apellido",
					"Debe ingresar un Apellido"));
		}
		if (null == fechaNacimiento) {
			validaciones.add(new ValidationError("persona_fecha_nacimiento",
					"Debe ingresar una Fecha de Nacimiento"));
		}
		if (Utils.isEmptyString(direccion)) {
			validaciones.add(new ValidationError("persona_direccion",
					"Debe ingresar una Dirección"));
		}
		if (null == idBarrio) {
			validaciones.add(new ValidationError("persona_barrio",
					"Debe seleccionar un Barrio"));
		}
		if (null == idLocalidad) {
			validaciones.add(new ValidationError("persona_localidad",
					"Debe seleccionar una Localidad"));
		}
		if (Utils.isEmptyString(cp)) {
			validaciones.add(new ValidationError("persona_cp",
					"Debe ingresar un Cod. Postal"));
		}
		return validaciones;
	}

	public String showEdit() throws Exception {
		Long idPersona = Long.valueOf(selectedItem);
		persona = personaBO.obtener(idPersona);
		loadParametricos();
		return EDIT;
	}

	private void loadParametricos() throws Exception {
		List<SexoEnum> lstSexo = new ArrayList<SexoEnum>();
		lstSexo.addAll(Arrays.asList(SexoEnum.values()));
		getServletRequest().setAttribute("lstSexo", lstSexo);

		List<TipoDocEnum> tiposDoc = new ArrayList<TipoDocEnum>();
		tiposDoc.addAll(Arrays.asList(TipoDocEnum.values()));
		getServletRequest().setAttribute("lstTiposDoc", tiposDoc);

		List<Provincia> provincias = parametricoBO.getAllProvincias();
		getServletRequest().setAttribute("lstProvincia", provincias);

		List<ObraSocial> obrasSociales = parametricoBO.getAllObrasSociales();
		getServletRequest().setAttribute("lstObraSocial", obrasSociales);

		List<Barrio> barrios = parametricoBO.getAllBarrios();
		getServletRequest().setAttribute("lstBarrio", barrios);

		List<TipoAtencionEnum> lstTipoAtencion = new ArrayList<TipoAtencionEnum>();
		lstTipoAtencion.addAll(Arrays.asList(TipoAtencionEnum.values()));
		getServletRequest().setAttribute("lstTipoAtencion", lstTipoAtencion);
	}

	public String getLocalidades() throws Exception {

		return null;
	}

	public String activateDesactivate() throws Exception {
		return SUCCESS;
	}

	public String unlock() throws Exception {
		return SUCCESS;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public PersonaFilter getPersonaFilter() {
		return personaFilter;
	}

	public void setPersonaFilter(PersonaFilter personaFilter) {
		this.personaFilter = personaFilter;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setPersonaBO(PersonaBO personaBO) {
		this.personaBO = personaBO;
	}

	public void setPaginationHelper(PaginationHelper paginationHelper) {
		this.paginationHelper = paginationHelper;
	}

	public void setParametricoBO(ParametricoBO parametricoBO) {
		this.parametricoBO = parametricoBO;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public void setAtencionBO(AtencionBO atencionBO) {
		this.atencionBO = atencionBO;
	}

	public TipoAtencionEnum getTipoAtencion() {
		return tipoAtencion;
	}

	public void setTipoAtencion(TipoAtencionEnum tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
	}

}

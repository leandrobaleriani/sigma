package sigma.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import sigma.bo.AtencionBO;
import sigma.common.Utils;
import sigma.entities.Atencion;
import sigma.entities.ObraSocial;
import sigma.reportes.AtencionReporte;

public class ReporteAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1767225656947241477L;

	private AtencionBO atencionBO;
	private Long idAtencion;
	private String desde;
	private String hasta;
	private String desdeHora;
	private String hastaHora;

	private String RESULT_PLANILLA = "planilla";
	private String RESULT_CABO = "cabo";
	private Map<String, Object> parameters;

	private List<Object> dataSource;

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String showImprimirPlanilla() throws Exception {
		Date hoy = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -12);
		Date desdeDate = calendar.getTime();
		String horaHasta = Utils.convertToHora(hoy);
		String horaDesde = Utils.convertToHora(desdeDate);
		String hasta = Utils.convertToFecha(hoy);
		String desde = Utils.convertToFecha(desdeDate);

		setDesde(desde);
		setHasta(hasta);

		setDesdeHora(horaDesde);
		setHastaHora(horaHasta);

		return "showPlanilla";
	}

	public String imprimirPlanilla() throws Exception {

		dataSource = new ArrayList<Object>();

		Date desdeDate = Utils.convertFechaHoraToDate(desde, desdeHora);
		Date hastaDate = Utils.convertFechaHoraToDate(hasta, hastaHora);

		desdeDate = Utils.setMinSeconds(desdeDate);
		hastaDate = Utils.setMaxSeconds(hastaDate);

		List<Atencion> atenciones = atencionBO.obtenerFinalizadas(desdeDate,
				hastaDate, getLoggedUser().getId());

		AtencionReporte atencionReporte = null;
		for (Atencion atencion : atenciones) {
			atencionReporte = new AtencionReporte();
			atencionReporte.setFecha(atencion.getFinAtencion());
			atencionReporte.setDiagnostico(atencion.getDiagnostico());
			atencionReporte.setEdad(atencion.getPersona().getEdad());
			atencionReporte
					.setSexo(atencion.getPersona().getSexo().getCodigo());
			atencionReporte.setPartido(atencion.getPersona().getLocalidad()
					.getNombre());
			atencionReporte
					.setNombre(atencion.getPersona().getNombreCompleto());

			if (null != atencion.getPersona().getDatosMedico().getObraSocial()) {
				atencionReporte.setPlanPrivadoMutual(atencion.getPersona()
						.getDatosMedico().getObraSocial().getAbrev());
			} else {
				atencionReporte.setNinguno("X");
			}
			dataSource.add(atencionReporte);
		}

		return RESULT_PLANILLA;
	}

	public String imprimirCabo() throws Exception {

		Atencion atencion = atencionBO.obtener(idAtencion);

		dataSource = new ArrayList<Object>();
		parameters = new HashMap<String, Object>();

		final String path = ServletActionContext.getServletContext()
				.getRealPath("/");
		final String imageUrl = path + "reportes/escudo.jpg";
		parameters.put("IMAGE", imageUrl);
		parameters.put("FECHA", atencion.getFechaRecepcion());
		parameters.put("TDOC", atencion.getPersona().getTipoDoc().name());
		parameters.put("DOC", atencion.getPersona().getDoc().toString());
		parameters.put("NOMBRE", atencion.getPersona().getNombreCompleto());

		parameters.put("SEXO", atencion.getPersona().getSexo().name());

		String edad = "" + atencion.getPersona().getEdad();
		edad = StringUtils.leftPad(edad, 3, "-");
		int longitud = edad.length();
		for (int i = 0; i < longitud; i++) {
			parameters.put("EDAD_DIGITO" + (i + 1), edad.charAt(i) + "");
		}

		ObraSocial os = atencion.getPersona().getDatosMedico().getObraSocial();

		if (null != os) {
			parameters.put("NOMBRE_OS", os.getNombre());
			parameters.put("ABREV_OS", os.getAbrev());
			parameters.put("COD_OS", os.getCodigo());
			parameters.put("NRO_CARNET_OS", atencion.getPersona()
					.getDatosMedico().getNroCarnet());
		}

		return RESULT_CABO;
	}

	public void setAtencionBO(AtencionBO atencionBO) {
		this.atencionBO = atencionBO;
	}

	public List<Object> getDataSource() {
		return dataSource;
	}

	public void setDataSource(List<Object> dataSource) {
		this.dataSource = dataSource;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public Long getIdAtencion() {
		return idAtencion;
	}

	public void setIdAtencion(Long idAtencion) {
		this.idAtencion = idAtencion;
	}

	public String getDesdeHora() {
		return desdeHora;
	}

	public void setDesdeHora(String desdeHora) {
		this.desdeHora = desdeHora;
	}

	public String getHastaHora() {
		return hastaHora;
	}

	public void setHastaHora(String hastaHora) {
		this.hastaHora = hastaHora;
	}

}
package sigma.actions;

import java.util.List;

import sigma.bo.ParametricoBO;
import sigma.entities.Localidad;

public class ParametricoAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 3094871401312455972L;
	private static final String OPCIONES_LOCALIDADES = "opciones_localidades";
	private Long idProvincia;
	private ParametricoBO parametricoBO;

	public String getLocalidadesByProvincia() throws Exception {
		List<Localidad> localidades = parametricoBO
				.getLocalidadesByProvincia(idProvincia);
		if (null != localidades) {
			getServletRequest().setAttribute("lstLocalidad", localidades);
		}
		return OPCIONES_LOCALIDADES;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public void setParametricoBO(ParametricoBO parametricoBO) {
		this.parametricoBO = parametricoBO;
	}

}

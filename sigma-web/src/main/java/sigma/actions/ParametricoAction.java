package sigma.actions;

import java.util.List;

import sigma.bo.ParametricoBO;
import sigma.entities.Localidad;
import sigma.entities.Partido;

public class ParametricoAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 3094871401312455972L;
	private static final String OPCIONES_LOCALIDADES = "opciones_localidades";
	private static final String OPCIONES_PARTIDOS = "opciones_partidos";
	private Long idProvincia;
	private Long idPartido;
	private ParametricoBO parametricoBO;

	public String getPartidosByProvincia() throws Exception {
		List<Partido> partidos = parametricoBO
				.getPartidosByProvincia(idProvincia);
		if (null != partidos) {
			getServletRequest().setAttribute("lstPartido", partidos);
		}
		return OPCIONES_PARTIDOS;
	}

	public String getLocalidadesByPartido() throws Exception {
		List<Localidad> localidades = parametricoBO
				.getLocalidadesByPartido(idPartido);
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

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

}

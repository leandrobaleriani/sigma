package sigma.filters;

import java.util.List;

public class AtencionFilter extends BaseFilter {

	public enum Estado {
		ATENCION, ESPERA, FINALIZADO
	}

	private Estado estado;
	private List<Estado> estados;
	private Long idLugarAtencion;
	private Long idUsuarioAtencion;
	private Integer maxResults;
	private Long idPersona;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getIdLugarAtencion() {
		return idLugarAtencion;
	}

	public void setIdLugarAtencion(Long idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}

	public Long getIdUsuarioAtencion() {
		return idUsuarioAtencion;
	}

	public void setIdUsuarioAtencion(Long idUsuarioAtencion) {
		this.idUsuarioAtencion = idUsuarioAtencion;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

}

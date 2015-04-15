package sigma.filters;

public class AtencionFilter extends BaseFilter {

	private Boolean finalizado;
	private Long idLugarAtencion;

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Long getIdLugarAtencion() {
		return idLugarAtencion;
	}

	public void setIdLugarAtencion(Long idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}

}

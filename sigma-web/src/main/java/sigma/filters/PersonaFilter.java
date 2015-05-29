package sigma.filters;

public class PersonaFilter extends BaseFilter {

	private String campoBusqueda;
	private Boolean paciente;

	public Boolean getPaciente() {
		return paciente;
	}

	public void setPaciente(Boolean paciente) {
		this.paciente = paciente;
	}

	public String getCampoBusqueda() {
		return campoBusqueda;
	}

	public void setCampoBusqueda(String campoBusqueda) {
		this.campoBusqueda = campoBusqueda;
	}

}

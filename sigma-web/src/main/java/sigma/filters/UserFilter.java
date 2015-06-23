package sigma.filters;

public class UserFilter extends BaseFilter {

	private String campoBusqueda;
	private String dni;
	private Boolean enUrgencia;
	private Boolean medico;

	public String getCampoBusqueda() {
		return campoBusqueda;
	}

	public void setCampoBusqueda(String campoBusqueda) {
		this.campoBusqueda = campoBusqueda;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Boolean getEnUrgencia() {
		return enUrgencia;
	}

	public void setEnUrgencia(Boolean enUrgencia) {
		this.enUrgencia = enUrgencia;
	}

	public Boolean getMedico() {
		return medico;
	}

	public void setMedico(Boolean medico) {
		this.medico = medico;
	}

}

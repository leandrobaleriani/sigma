package sigma.dto;

public class AtencionDTO {

	private String nombre;
	private String tipoAtencion;
	private boolean enEspera = Boolean.FALSE;

	public AtencionDTO(String nombre, String tipoAtencion, boolean enEspera) {
		this.nombre = nombre;
		this.tipoAtencion = tipoAtencion;
		this.enEspera = enEspera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoAtencion() {
		return tipoAtencion;
	}

	public void setTipoAtencion(String tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
	}

	public boolean isEnEspera() {
		return enEspera;
	}

	public void setEnEspera(boolean enEspera) {
		this.enEspera = enEspera;
	}

}

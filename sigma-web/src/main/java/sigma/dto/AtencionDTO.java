package sigma.dto;

public class AtencionDTO {

	private String nombre;
	private String tipoAtencion;
	private String fechaRecepcion;
	private String fechaAtencion;
	private boolean enEspera = Boolean.FALSE;
	private String id;

	public AtencionDTO(String nombre, String tipoAtencion, boolean enEspera) {
		this(nombre, tipoAtencion, enEspera, null, null, null);
	}

	public AtencionDTO(String nombre, String tipoAtencion, boolean enEspera,
			String fechaRecepcion, String fechaAtencion, String id) {
		this.nombre = nombre;
		this.tipoAtencion = tipoAtencion;
		this.enEspera = enEspera;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaAtencion = fechaAtencion;
		this.id = id;
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

	public String getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

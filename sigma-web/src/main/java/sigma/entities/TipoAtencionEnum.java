package sigma.entities;

public enum TipoAtencionEnum {

	PEDIATRIA("PEDIATR�A"), CLINICA("CL�NICA"), ENFERMERIA("ENFERMER�A");

	private String descripcion;

	private TipoAtencionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}

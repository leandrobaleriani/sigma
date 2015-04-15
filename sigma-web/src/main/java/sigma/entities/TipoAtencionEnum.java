package sigma.entities;

public enum TipoAtencionEnum {

	PEDIATRIA("PEDIATRÍA"), CLINICA("CLÍNICA"), ENFERMERIA("ENFERMERÍA");

	private String descripcion;

	private TipoAtencionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}

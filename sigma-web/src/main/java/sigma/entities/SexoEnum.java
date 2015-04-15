package sigma.entities;

public enum SexoEnum {

	M(1, "MASCULINO"), F(2, "FEMENINO");

	private int id;
	private String descripcion;

	private SexoEnum(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return descripcion;
	}
}

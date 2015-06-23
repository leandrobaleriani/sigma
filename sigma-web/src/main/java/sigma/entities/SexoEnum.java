package sigma.entities;

public enum SexoEnum {
	MASCULINO("M"), FEMENINO("F");

	private String codigo;

	private SexoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}

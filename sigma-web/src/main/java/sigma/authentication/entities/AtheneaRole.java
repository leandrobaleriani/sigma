package sigma.authentication.entities;

public class AtheneaRole implements
		org.springframework.security.core.GrantedAuthority {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 849240057973216002L;
	private String codigo;
	private String descripcion;
	private String aplicacion;

	public AtheneaRole(String codigo, String descripcion, String aplicacion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.aplicacion = aplicacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	@Override
	public String getAuthority() {
		return codigo;
	}

}

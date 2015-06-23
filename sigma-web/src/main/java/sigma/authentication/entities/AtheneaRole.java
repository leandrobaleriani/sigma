package sigma.authentication.entities;

public class AtheneaRole implements
		org.springframework.security.core.GrantedAuthority {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 849240057973216002L;
	private String codigo;

	public AtheneaRole(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String getAuthority() {
		return codigo;
	}

}

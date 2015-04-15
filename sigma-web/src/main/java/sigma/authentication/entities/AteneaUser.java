package sigma.authentication.entities;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import sigma.entities.LugarAtencion;

public class AteneaUser extends User {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 4611070327209424464L;
	private String nombreCompleto;
	private Long id;
	private List<AtheneaRole> roles;
	private List<LugarAtencion> lugaresAtencion;

	public AteneaUser(String username, String password, boolean enabled,
			boolean accountNonLocked, List<AtheneaRole> roles,
			String nombreCompleto, Long id, List<LugarAtencion> lugaresAtencion) {
		super(username, password, enabled, true, true, accountNonLocked, roles);
		this.roles = roles;
		this.nombreCompleto = nombreCompleto;
		this.id = id;
		this.lugaresAtencion = lugaresAtencion;
	}

	public List<AtheneaRole> getRoles() {
		return roles;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public Long getId() {
		return id;
	}

	public List<LugarAtencion> getLugaresAtencion() {
		return lugaresAtencion;
	}

}

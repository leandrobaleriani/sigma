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

	private boolean admin;
	private boolean medico;
	private boolean recepcionista;
	private boolean directivo;

	public AteneaUser(String username, String password, boolean enabled,
			boolean accountNonLocked, List<AtheneaRole> roles,
			String nombreCompleto, Long id, List<LugarAtencion> lugaresAtencion) {
		super(username, password, enabled, true, true, accountNonLocked, roles);
		this.roles = roles;
		this.nombreCompleto = nombreCompleto;
		this.id = id;
		this.lugaresAtencion = lugaresAtencion;

		for (AtheneaRole role : roles) {
			if (role.getCodigo().equals("ROL_ADM")) {
				this.admin = Boolean.TRUE;
			}
			if (role.getCodigo().equals("ROL_REC")  || this.admin) {
				this.recepcionista = Boolean.TRUE;
			}
			if (role.getCodigo().equals("ROL_MED")  || this.admin) {
				this.medico = Boolean.TRUE;
			}
			if (role.getCodigo().equals("ROL_DIR")  || this.admin) {
				this.directivo = Boolean.TRUE;
			}
		}
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

	public boolean isAdmin() {
		return admin;
	}

	public boolean isMedico() {
		return medico;
	}

	public boolean isRecepcionista() {
		return recepcionista;
	}

	public boolean isDirectivo() {
		return directivo;
	}

}

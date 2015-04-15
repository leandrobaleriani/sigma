package sigma.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -1319138953487059575L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "ACTIVO")
	private boolean activo;
	@Column(name = "ATTEMPTS")
	private int attempts;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "ID_USER", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_ROLE", nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>(0);
	@Column(name = "LOCKED")
	private boolean locked;
	@ManyToMany(fetch = FetchType.EAGER
			, cascade = CascadeType.ALL)
	@JoinTable(name = "users_lugares_atencion", joinColumns = { @JoinColumn(name = "ID_USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_LUGAR_ATENCION", nullable = false, updatable = false) })
	private List<LugarAtencion> lugaresAtencion;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<LugarAtencion> getLugaresAtencion() {
		return lugaresAtencion;
	}

	public void setLugaresAtencion(List<LugarAtencion> lugaresAtencion) {
		this.lugaresAtencion = lugaresAtencion;
	}

}

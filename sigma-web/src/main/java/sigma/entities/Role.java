package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -6431256889118683331L;

	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "DESCRIPCION")
	private String descripcion;

	public Role() {
	}

	public Role(Long id) {
		setId(id);
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

}

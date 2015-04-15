package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn(name = "ID_APLICACION", insertable = false, updatable = false)
	private Aplicacion aplicacion;
	@Column(name = "ID_APLICACION")
	private Long idAplicacion;
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

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

}

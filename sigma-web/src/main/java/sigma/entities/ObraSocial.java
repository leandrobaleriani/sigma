package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "obras_sociales")
public class ObraSocial extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 2215003087640007201L;
	@Column(name = "ABREV")
	private String abrev;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "CODIGO")
	private String codigo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbrev() {
		return abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "barrios")
public class Barrio extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 2215003087640007201L;
	@Column(name = "NOMBRE")
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

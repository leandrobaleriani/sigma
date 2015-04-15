package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lugares_atencion")
public class LugarAtencion extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 2215003087640007201L;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DIRECCION")
	private String direccion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}

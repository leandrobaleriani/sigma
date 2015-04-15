package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parametros")
public class Parametro extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -1319138953487059575L;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "VALOR")
	private String valor;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

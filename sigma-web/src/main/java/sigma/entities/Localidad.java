package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localidades")
public class Localidad extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 2215003087640007201L;
	@Column(name = "NOMBRE")
	private String nombre;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROVINCIA", insertable = false, updatable = false)
	private Provincia provincia;
	@Column(name = "ID_PROVINCIA")
	private Long idProvincia;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

}

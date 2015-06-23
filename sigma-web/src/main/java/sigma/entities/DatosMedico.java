package sigma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "datos_medicos")
public class DatosMedico extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OBRA_SOCIAL", insertable = false, updatable = false)
	private ObraSocial obraSocial;
	@Column(name = "ID_OBRA_SOCIAL")
	private Long idObraSocial;
	@Column(name = "PLAN_OBRA_SOCIAL")
	private String planObraSocial;
	@Column(name = "NRO_CARNET")
	private String nroCarnet;

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public Long getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getPlanObraSocial() {
		return planObraSocial;
	}

	public void setPlanObraSocial(String planObraSocial) {
		this.planObraSocial = planObraSocial;
	}

	public String getNroCarnet() {
		return nroCarnet;
	}

	public void setNroCarnet(String nroCarnet) {
		this.nroCarnet = nroCarnet;
	}

}

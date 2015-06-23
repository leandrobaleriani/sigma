package sigma.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atenciones")
public class Atencion extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 796037576154923358L;
	@Column(name = "ID_USUARIO_RECEPCION")
	private Long idUsuarioRecepcion;
	@Column(name = "ID_USUARIO_ATENCION")
	private Long idUsuarioAtencion;
	@Column(name = "FECHA_RECEPCION")
	private Date fechaRecepcion;
	@Column(name = "INICIO_ATENCION")
	private Date inicioAtencion;
	@Column(name = "FIN_ATENCION")
	private Date finAtencion;
	@Column(name = "DIAGNOSTICO")
	private String diagnostico;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "TIPO_ATENCION")
	private TipoAtencionEnum tipoAtencion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PERSONA", insertable = false, updatable = false)
	private Persona persona;
	@Column(name = "ID_PERSONA")
	private Long idPersona;
	@Column(name = "ID_LUGAR_ATENCION")
	private Long idLugarAtencion;
	@Column(name = "MOTIVO")
	private String motivo;
	@Column(name = "CANCELACION_ATENCION")
	private Date cancelacionAtencion;
	@Column(name = "ID_USUARIO_CANCELACION")
	private Long idUsuarioCancelacion;
	@Column(name = "INTERNACION")
	private boolean internacion;

	public Long getIdUsuarioRecepcion() {
		return idUsuarioRecepcion;
	}

	public void setIdUsuarioRecepcion(Long idUsuarioRecepcion) {
		this.idUsuarioRecepcion = idUsuarioRecepcion;
	}

	public Long getIdUsuarioAtencion() {
		return idUsuarioAtencion;
	}

	public void setIdUsuarioAtencion(Long idUsuarioAtencion) {
		this.idUsuarioAtencion = idUsuarioAtencion;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getInicioAtencion() {
		return inicioAtencion;
	}

	public void setInicioAtencion(Date inicioAtencion) {
		this.inicioAtencion = inicioAtencion;
	}

	public Date getFinAtencion() {
		return finAtencion;
	}

	public void setFinAtencion(Date finAtencion) {
		this.finAtencion = finAtencion;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public TipoAtencionEnum getTipoAtencion() {
		return tipoAtencion;
	}

	public void setTipoAtencion(TipoAtencionEnum tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Long getIdLugarAtencion() {
		return idLugarAtencion;
	}

	public void setIdLugarAtencion(Long idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getCancelacionAtencion() {
		return cancelacionAtencion;
	}

	public void setCancelacionAtencion(Date cancelacionAtencion) {
		this.cancelacionAtencion = cancelacionAtencion;
	}

	public Long getIdUsuarioCancelacion() {
		return idUsuarioCancelacion;
	}

	public void setIdUsuarioCancelacion(Long idUsuarioCancelacion) {
		this.idUsuarioCancelacion = idUsuarioCancelacion;
	}

	public boolean isInternacion() {
		return internacion;
	}

	public void setInternacion(boolean internacion) {
		this.internacion = internacion;
	}

}

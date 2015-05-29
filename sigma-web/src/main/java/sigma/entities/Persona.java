package sigma.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sigma.utils.SigmaUtils;

@Entity
@Table(name = "personas")
public class Persona extends BaseEntity {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 2215003087640007201L;
	@Column(name = "DOC")
	private Long doc;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "FECHA_NAC")
	private Date fechaNacimiento;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "SEXO")
	private SexoEnum sexo;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "TELEFONO")
	private String telefono;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_LOCALIDAD", insertable = false, updatable = false)
	private Localidad localidad;
	@Column(name = "ID_LOCALIDAD")
	private Long idLocalidad;
	@Column(name = "COD_POSTAL")
	private String codPostal;
	@Column(name = "APELLIDO")
	private String apellido;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "TIPO_DOC")
	private TipoDocEnum tipoDoc;
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "persona", cascade = CascadeType.ALL)
	private DatosMedico datosMedico;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_BARRIO", insertable = false, updatable = false)
	private Barrio barrio;
	@Column(name = "ID_BARRIO")
	private Long idBarrio;
	@Column(name = "BARRIO")
	private String observacionBarrio;
	@Column(name = "MAIL")
	private String mail;
	@Column(name = "PACIENTE")
	private boolean paciente;

	public Long getDoc() {
		return doc;
	}

	public void setDoc(Long doc) {
		this.doc = doc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public TipoDocEnum getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(TipoDocEnum tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	public String getObservacionBarrio() {
		return observacionBarrio;
	}

	public void setObservacionBarrio(String observacionBarrio) {
		this.observacionBarrio = observacionBarrio;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public Long getIdBarrio() {
		return idBarrio;
	}

	public void setIdBarrio(Long idBarrio) {
		this.idBarrio = idBarrio;
	}

	public DatosMedico getDatosMedico() {
		return datosMedico;
	}

	public void setDatosMedico(DatosMedico datosMedico) {
		this.datosMedico = datosMedico;
	}

	public String getNombreCompleto() {
		return this.nombre + " " + this.apellido;
	}

	public boolean isPaciente() {
		return paciente;
	}

	public void setPaciente(boolean paciente) {
		this.paciente = paciente;
	}

	public int getEdad() {
		return SigmaUtils.getEdad(getFechaNacimiento());
	}
}

package sigma.dto;

import java.util.List;

public class DashboardDTO {

	public DashboardDTO() {
		turnoManiana = new TurnoDTO();
		turnoTarde = new TurnoDTO();
		turnoTarde = new TurnoDTO();
	}

	private List<String> usuariosUrgencias;
	private int enEspera;
	private int enAtencion;
	private TurnoDTO turnoManiana;
	private TurnoDTO turnoTarde;
	private TurnoDTO turnoNoche;

	public List<String> getUsuariosUrgencias() {
		return usuariosUrgencias;
	}

	public void setUsuariosUrgencias(List<String> usuariosUrgencias) {
		this.usuariosUrgencias = usuariosUrgencias;
	}

	public int getEnEspera() {
		return enEspera;
	}

	public void setEnEspera(int enEspera) {
		this.enEspera = enEspera;
	}

	public int getEnAtencion() {
		return enAtencion;
	}

	public void setEnAtencion(int enAtencion) {
		this.enAtencion = enAtencion;
	}

	public TurnoDTO getTurnoManiana() {
		return turnoManiana;
	}

	public void setTurnoManiana(TurnoDTO turnoManiana) {
		this.turnoManiana = turnoManiana;
	}

	public TurnoDTO getTurnoTarde() {
		return turnoTarde;
	}

	public void setTurnoTarde(TurnoDTO turnoTarde) {
		this.turnoTarde = turnoTarde;
	}

	public TurnoDTO getTurnoNoche() {
		return turnoNoche;
	}

	public void setTurnoNoche(TurnoDTO turnoNoche) {
		this.turnoNoche = turnoNoche;
	}

}


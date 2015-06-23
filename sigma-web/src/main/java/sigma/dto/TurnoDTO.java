package sigma.dto;

import java.util.Date;

public class TurnoDTO {

	private Date desde;
	private Date hasta;
	private int enEspera;
	private int enAtencion;
	private int minutosEspera;
	private int minutosAtencion;
	private int minutosTotales;

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
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

	public int getMinutosEspera() {
		return minutosEspera;
	}

	public void setMinutosEspera(int minutosEspera) {
		this.minutosEspera = minutosEspera;
	}

	public int getMinutosAtencion() {
		return minutosAtencion;
	}

	public void setMinutosAtencion(int minutosAtencion) {
		this.minutosAtencion = minutosAtencion;
	}

	public int getMinutosTotales() {
		return minutosTotales;
	}

	public void setMinutosTotales(int minutosTotales) {
		this.minutosTotales = minutosTotales;
	}

}

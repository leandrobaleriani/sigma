package sigma.filters;

public class Order {

	private String campoOrdenamiento;
	private boolean descendente;

	public String getCampoOrdenamiento() {
		return campoOrdenamiento;
	}

	public void setCampoOrdenamiento(String campoOrdenamiento) {
		this.campoOrdenamiento = campoOrdenamiento;
	}

	public boolean isDescendente() {
		return descendente;
	}

	public void setDescendente(boolean descendente) {
		this.descendente = descendente;
	}
}

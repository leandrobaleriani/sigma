package sigma.utils;

public class ValidationError {

	private String key;
	private String mensaje;

	public ValidationError(String key, String mensaje) {
		this.key = key;
		this.mensaje = mensaje;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}

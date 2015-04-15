/**
 * Autor : sergio.orona ( Neoris Argentina ) Creacion : 18/04/2011 - 13:15:17
 */
package sigma.utils;

public class SearchOrder {

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	private String campo;
	private String orden;

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

}

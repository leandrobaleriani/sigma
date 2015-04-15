package sigma.actions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;

import sigma.authentication.entities.AteneaUser;
import sigma.common.Utils;
import sigma.entities.LugarAtencion;
import sigma.utils.ValidationError;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ParameterNameAware, SessionAware {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -687092953515188108L;
	private HttpServletRequest request;
	private Map<String, Object> session;
	protected LinkedHashMap<String, Object> jsonData;
	protected static final String JSON = "json";
	private final String LUGAR_ATENCION = "lugarAtencion";

	protected static final String NEW = "new";
	protected static final String EDIT = "edit";

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	protected AteneaUser getLoggedUser() {
		return (AteneaUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}
	
	protected LugarAtencion getLugarAtencion(){
		return (LugarAtencion)getSession().get(LUGAR_ATENCION);
	}
	
	protected void setLugarAtencion(LugarAtencion lugarAtencion){
		getSession().put(LUGAR_ATENCION, lugarAtencion);
	}

	/**
	 * This method will filter out parameters that start with "d-" followed by a
	 * numeric digit, because parameters of this form are generated by
	 * displayTag, and are treated by webwork as an invalid OGNL expressions
	 * causing webwork to throw an ognl.InappropriateExpressionException
	 * exception. Note that the exception is only thrown when webwork is set in
	 * devmode. However, to prevent this error, the ParameterNameAware interface
	 * has been implemented which requires this method. This method could be
	 * implemented more simply using java's regular expression support, but such
	 * an implementation may suffer from readability except for people who are
	 * very strong in understanding java's RegEX semantics.
	 */
	public boolean acceptableParameterName(final String parameterName) {
		boolean retVal = true;
		if (parameterName != null && parameterName.startsWith("d-")
				&& parameterName.length() > 2) {
			final String thirdCharacter = parameterName.substring(2, 3);
			if (StringUtils.isNumeric(thirdCharacter)) {
				retVal = false;
			}
		}
		return retVal;
	}

	public LinkedHashMap<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	protected LinkedHashMap<String, Object> createJSONResponse(boolean exito,
			String mensaje, List<ValidationError> mensajesValidacion) {
		boolean validacion = Utils.isNotEmptyCollection(mensajesValidacion);
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("exito", exito);
		jsonData.put("validacion", validacion);
		jsonData.put("mensaje", mensaje);
		if (validacion) {
			jsonData.put("mensajesValidacion", mensajesValidacion);
		}
		return jsonData;
	}
	
	protected LinkedHashMap<String, Object> createCustomJSONResponse(Object object) {
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("data", object);
		return jsonData;
	}
	
	protected LinkedHashMap<String, Object> createJSONResponse() {
		jsonData = new LinkedHashMap<String, Object>();
		return jsonData;
	}

}
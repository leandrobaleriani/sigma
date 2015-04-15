package sigma.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import sigma.authentication.entities.AteneaUser;
import sigma.common.Utils;
import sigma.entities.LugarAtencion;

public class CustomAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String redirectTo;

		AteneaUser ateneaUser = (AteneaUser) authentication.getPrincipal();
		List<LugarAtencion> lugaresAtencion = ateneaUser.getLugaresAtencion();
		if (Utils.isNotEmptyCollection(lugaresAtencion)
				&& lugaresAtencion.size() > 1) {
			request.getSession().setAttribute("lstLugarAtencion",
					lugaresAtencion);
			redirectTo = "/showSeleccionarLugarAtencion.action";
		} else {
			redirectTo = "/home.action";
		}
		setDefaultTargetUrl(redirectTo);
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
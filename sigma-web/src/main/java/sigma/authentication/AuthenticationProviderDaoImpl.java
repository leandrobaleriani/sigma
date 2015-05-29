package sigma.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import sigma.bo.UserBO;
import sigma.exceptions.BusinessException;

public class AuthenticationProviderDaoImpl extends DaoAuthenticationProvider {

	private UserBO userBO;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		try {
			Authentication auth = super.authenticate(authentication);
			return auth;
		} catch (BadCredentialsException e) {
			try {
				userBO.actualizarIntentosFallidos(authentication.getName());
			} catch (BusinessException bexc) {
				throw e;
			}
			throw e;
		}
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

}

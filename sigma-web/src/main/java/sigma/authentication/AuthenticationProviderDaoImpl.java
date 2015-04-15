package sigma.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import sigma.bo.UserBO;
import sigma.dao.UserDAO;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;

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
				userBO.updateFailAttempts(authentication.getName());
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

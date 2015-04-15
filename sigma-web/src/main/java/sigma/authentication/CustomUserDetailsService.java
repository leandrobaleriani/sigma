package sigma.authentication;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sigma.bo.UserBO;
import sigma.common.Utils;

public class CustomUserDetailsService implements UserDetailsService {

	private UserBO userBO;

	/**
	 * Retrieves a user record containing the user's credentials and access.
	 */
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		UserDetails user = null;

		if (Utils.isEmptyString(userName)) {
			throw new InsufficientAuthenticationException(
					"El Usuario o Password no pueden ser vacios");
		}

		try {
			user = userBO.login(userName);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Error al obtener Usuario");
		}

		if (null == user) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		return user;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

}

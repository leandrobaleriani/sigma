package sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import sigma.authentication.entities.AteneaUser;
import sigma.authentication.entities.AtheneaRole;
import sigma.common.Utils;
import sigma.dao.UserDAO;
import sigma.entities.LugarAtencion;
import sigma.entities.Parametro;
import sigma.entities.Role;
import sigma.entities.User;
import sigma.exceptions.DataAccessException;
import sigma.filters.UserFilter;

public class UserDAOImpl extends GenericHBDAOImpl<User> implements UserDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
	private final String PARAM_MAX_ATTEMPTS = "MAX_ATTEMPTS";

	private int getMaxAttempts() throws DataAccessException {
		Criteria criteria = getSession().createCriteria(Parametro.class);
		criteria.add(Restrictions.eq("nombre", PARAM_MAX_ATTEMPTS));
		Parametro parametro = (Parametro) criteria.uniqueResult();
		int maxAttempts = Integer.valueOf(parametro.getValor());
		return maxAttempts;
	}

	@Override
	public UserDetails login(String userName) throws DataAccessException {

		User usuario = getUsuarioByUserName(userName);

		String username = usuario.getPersona().getDoc() + "";
		String password = usuario.getPassword();
		boolean activo = usuario.isActivo();
		boolean locked = usuario.isLocked();
		String nombreCompleto = usuario.getPersona().getNombreCompleto();
		Long idUsuario = usuario.getId();
		List<LugarAtencion> lugaresAtencion = usuario.getLugaresAtencion();

		return new AteneaUser(username, password, activo, !locked,
				getAuthorities(usuario), nombreCompleto, idUsuario,
				lugaresAtencion);
	}

	@Override
	public void updateFailAttempts(String userName) throws DataAccessException {

		int maxAttempts = getMaxAttempts();

		User usuario = getUsuarioByUserName(userName);
		if (null != usuario) {

			int attempts = usuario.getAttempts();
			attempts++;

			if (attempts >= maxAttempts) {
				usuario.setAttempts(attempts);
				usuario.setLocked(Boolean.TRUE);
				saveOrUpdate(usuario);
			}
		}
	}

	private List<AtheneaRole> getAuthorities(User usuario) {

		List<AtheneaRole> rolesUsuario = new ArrayList<AtheneaRole>();

		Set<Role> roles = usuario.getRoles();
		if (Utils.isNotEmptyCollection(roles)) {
			for (Role role : roles) {
				rolesUsuario.add(new AtheneaRole(role.getCodigo(), role
						.getDescripcion(), role.getAplicacion().getNombre()));
			}
		}

		return rolesUsuario;
	}

	@Override
	public List<User> search(UserFilter filter) throws DataAccessException {

		try {
			Criteria criteria = buildCriteria(filter);
			List<User> users = criteria.list();
			if (null != users) {
				for (User user : users) {
					Hibernate.initialize(user.getPersona());
				}
			}
			return users;

		} catch (Exception exc) {
			LOGGER.error("search() - Error al realizar busqueda de Usuarios",
					exc);
			throw new DataAccessException(exc);
		}
	}

	@Override
	public List<Role> getAllRoles() throws DataAccessException {

		try {
			Criteria criteria = getSession().createCriteria(Role.class);
			criteria.createAlias("aplicacion", "aplicacion");
			criteria.addOrder(org.hibernate.criterion.Order
					.desc("aplicacion.nombre"));
			return criteria.list();

		} catch (Exception exc) {
			LOGGER.error("getAllRoles() - Error al obtener Roles", exc);
			throw new DataAccessException(exc);
		}
	}

	private Criteria buildCriteria(UserFilter filter) {

		Criteria criteria = getSession().createCriteria(User.class);

		// Filtros
		String nroDoc = filter.getDni();
		Boolean enUrgencia = filter.getEnUrgencia();
		Boolean prestador = filter.getPrestador();
		
		if (null != nroDoc) {
			criteria.createAlias("persona", "persona");
			criteria.add(Restrictions.eq("persona.doc", Long.valueOf(nroDoc)));
		}
		
		if (null != enUrgencia) {
			criteria.add(Restrictions.eq("urgencia", enUrgencia));
		}
		
		if (null != prestador) {
			criteria.add(Restrictions.eq("prestador", prestador));
		}

		return criteria;
	}

	private User getUsuarioByUserName(String userName)
			throws DataAccessException {
		UserFilter filter = new UserFilter();
		filter.setDni(userName);
		List<User> usuarios = search(filter);
		if (Utils.isNotEmptyCollection(usuarios)) {
			return usuarios.get(0);
		}
		return null;
	}

}

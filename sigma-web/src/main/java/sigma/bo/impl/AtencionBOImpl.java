package sigma.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.AtencionBO;
import sigma.common.Utils;
import sigma.dao.AtencionDAO;
import sigma.dao.PersonaDAO;
import sigma.dao.UserDAO;
import sigma.dto.DashboardDTO;
import sigma.entities.Atencion;
import sigma.entities.Persona;
import sigma.entities.TipoAtencionEnum;
import sigma.entities.User;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.AtencionFilter;
import sigma.filters.AtencionFilter.Estado;
import sigma.utils.SearchOrder;

public class AtencionBOImpl implements AtencionBO {

	private final Logger LOGGER = LoggerFactory.getLogger(AtencionBOImpl.class);

	private AtencionDAO atencionDAO;
	private PersonaDAO personaDAO;
	private UserDAO userDAO;

	@Override
	public Atencion obtener(Long id) throws BusinessException {
		try {
			return atencionDAO.getById(id);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Atencion", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerFinalizadas(Date desde, Date hasta,
			Long idUsuario) throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setDesde(desde);
			filter.setHasta(hasta);
			filter.setIdUsuarioAtencion(idUsuario);
			filter.setEstado(Estado.FINALIZADO);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerEnEspera() throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setEstado(Estado.ESPERA);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones en Espera", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerEnEsperaEnAtencion() throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			List<Estado> estados = new ArrayList<AtencionFilter.Estado>();
			estados.add(Estado.ESPERA);
			estados.add(Estado.ATENCION);
			filter.setEstados(estados);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones en Espera", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public boolean atender(Long idAtencion, Long idUsuarioAtencion)
			throws BusinessException {
		try {
			Atencion atencion = atencionDAO.getById(idAtencion);
			if (null == atencion.getInicioAtencion()) {
				atencion.setIdUsuarioAtencion(idUsuarioAtencion);
				atencion.setInicioAtencion(new Date());
				User user = userDAO.getById(idUsuarioAtencion);
				user.setUrgencia(Boolean.FALSE);
				userDAO.saveOrUpdate(user);
				atencionDAO.saveOrUpdate(atencion);
				return true;
			} else {
				return false;
			}
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar Atención", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void finalizar(Long idAtencion, String diagnostico,
			boolean internacion) throws BusinessException {
		try {
			Atencion atencion = atencionDAO.getById(idAtencion);
			atencion.setFinAtencion(new Date());
			atencion.setDiagnostico(diagnostico);
			atencion.setInternacion(internacion);
			atencionDAO.saveOrUpdate(atencion);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar Atención", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerPendientesPorUsuario(Long idUsuario)
			throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setEstado(Estado.ATENCION);
			filter.setIdUsuarioAtencion(idUsuario);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones Pendientes de Finalizar",
					daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerUltimasAtenciones(Long idUsuario)
			throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("desc");
			filter.setSearchOrder(searchOrder);
			filter.setEstado(Estado.FINALIZADO);
			filter.setIdUsuarioAtencion(idUsuario);
			filter.setMaxResults(3);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones Finalizadas", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerHistorial(Long idPersona)
			throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setIdPersona(idPersona);
			filter.setEstado(Estado.FINALIZADO);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Historial de Atenciones", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> obtenerEnAtencion() throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setEstado(Estado.ATENCION);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones Pendientes de Finalizar",
					daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void recepcionarActualizar(Long idUsuario,
			TipoAtencionEnum tipoAtencion, Persona persona, Long idLugarAtencion)
			throws BusinessException {
		try {
			AtencionFilter atencionFilter = new AtencionFilter();
			atencionFilter.setIdPersona(persona.getId());
			List<Estado> estados = new ArrayList<AtencionFilter.Estado>();
			estados.add(Estado.ATENCION);
			estados.add(Estado.ESPERA);
			atencionFilter.setEstados(estados);
			List<Atencion> atenciones = atencionDAO.search(atencionFilter);

			if (Utils.isEmptyCollection(atenciones)) {
				Atencion atencion = new Atencion();
				atencion.setFechaRecepcion(new Date());
				atencion.setIdUsuarioRecepcion(idUsuario);
				atencion.setTipoAtencion(tipoAtencion);
				atencion.setIdPersona(persona.getId());
				atencion.setIdLugarAtencion(idLugarAtencion);
				atencionDAO.saveOrUpdate(atencion);
				personaDAO.saveOrUpdate(persona);
			} else {
				LOGGER.warn("Error al guardar Atención");
				throw new BusinessException(
						"El Paciente ya se encuentra recepcionado",
						BusinessException.TypeError.MENSAJE);
			}
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Atención", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void recepcionarIngresar(Long idUsuario,
			TipoAtencionEnum tipoAtencion, Persona persona, Long idLugarAtencion)
			throws BusinessException {
		try {
			personaDAO.saveOrUpdate(persona);
			Atencion atencion = new Atencion();
			atencion.setFechaRecepcion(new Date());
			atencion.setIdUsuarioRecepcion(idUsuario);
			atencion.setTipoAtencion(tipoAtencion);
			atencion.setIdPersona(persona.getId());
			atencion.setIdLugarAtencion(idLugarAtencion);
			atencionDAO.saveOrUpdate(atencion);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Atención", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void finalizarMotivo(Long idAtencion, String motivo,
			Long idUsuarioCancelacion) throws BusinessException {
		try {
			Atencion atencion = atencionDAO.getById(idAtencion);
			atencion.setCancelacionAtencion(new Date());
			atencion.setMotivo(motivo);
			atencion.setIdUsuarioCancelacion(idUsuarioCancelacion);
			atencionDAO.saveOrUpdate(atencion);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar Cancelación de Atención", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public DashboardDTO obtenerDashboard() throws BusinessException {

		List<String> usuariosUrgencias = new ArrayList<String>();
		
		
		DashboardDTO dashboard = new DashboardDTO();
		dashboard.setEnAtencion(3);
		dashboard.setEnEspera(5);
		dashboard.setUsuariosUrgencias(usuariosUrgencias);
		
		
		return dashboard;
	}

	public void setAtencionDAO(AtencionDAO atencionDAO) {
		this.atencionDAO = atencionDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}

package sigma.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.AtencionBO;
import sigma.dao.AtencionDAO;
import sigma.entities.Atencion;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.AtencionFilter;
import sigma.filters.AtencionFilter.Estado;
import sigma.utils.SearchOrder;

public class AtencionBOImpl implements AtencionBO {

	private final Logger LOGGER = LoggerFactory.getLogger(AtencionBOImpl.class);

	private AtencionDAO atencionDAO;

	@Override
	public void saveOrUpdate(Atencion atencion) throws BusinessException {
		try {
			atencionDAO.saveOrUpdate(atencion);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Atencion", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public Atencion getById(Long id) throws BusinessException {
		try {
			return atencionDAO.getById(id);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Atencion", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> search(AtencionFilter filter)
			throws BusinessException {
		try {
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> getAtencionesEnEspera() throws BusinessException {
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
	public List<Atencion> getAtenciones() throws BusinessException {
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
				atencionDAO.saveOrUpdate(atencion);
				return true;
			} else {
				return false;
			}
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar Atenci�n", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void finalizar(Long idAtencion, String diagnostico)
			throws BusinessException {
		try {
			Atencion atencion = atencionDAO.getById(idAtencion);
			atencion.setFinAtencion(new Date());
			atencion.setDiagnostico(diagnostico);
			atencionDAO.saveOrUpdate(atencion);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar Atenci�n", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Atencion> getAtencionesPendientes(Long idUsuario)
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
	public List<Atencion> getAtencionesUltimas(Long idUsuario)
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
	public List<Atencion> getAtencionesHistorial(Long idPersona)
			throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setIdPersona(idPersona);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Historial de Atenciones", daexc);
			throw new BusinessException(daexc);
		}
	}

	public void setAtencionDAO(AtencionDAO atencionDAO) {
		this.atencionDAO = atencionDAO;
	}

}

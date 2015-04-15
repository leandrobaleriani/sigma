package sigma.bo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.AtencionBO;
import sigma.dao.AtencionDAO;
import sigma.entities.Atencion;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.AtencionFilter;
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
	public List<Atencion> getAtencionesEnEspera(Long idLugarAtencion) throws BusinessException {
		try {
			AtencionFilter filter = new AtencionFilter();
			SearchOrder searchOrder = new SearchOrder();
			searchOrder.setCampo("atencion");
			searchOrder.setOrden("asc");
			filter.setSearchOrder(searchOrder);
			filter.setFinalizado(Boolean.FALSE);
			filter.setIdLugarAtencion(idLugarAtencion);
			return atencionDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al buscar Atenciones en Espera", daexc);
			throw new BusinessException(daexc);
		}
	}

	public void setAtencionDAO(AtencionDAO atencionDAO) {
		this.atencionDAO = atencionDAO;
	}

}

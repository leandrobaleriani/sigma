package sigma.bo.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.ParametricoBO;
import sigma.common.Utils;
import sigma.dao.BarrioDAO;
import sigma.dao.LocalidadDAO;
import sigma.dao.LugarAtencionDAO;
import sigma.dao.ObraSocialDAO;
import sigma.dao.ProvinciaDAO;
import sigma.entities.Barrio;
import sigma.entities.Localidad;
import sigma.entities.LugarAtencion;
import sigma.entities.ObraSocial;
import sigma.entities.Provincia;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.LocalidadFilter;

public class ParametricoBOImpl implements ParametricoBO {

	private final Logger LOGGER = LoggerFactory
			.getLogger(ParametricoBOImpl.class);
	private ProvinciaDAO provinciaDAO;
	private LocalidadDAO localidadDAO;
	private ObraSocialDAO obraSocialDAO;
	private BarrioDAO barrioDAO;
	private LugarAtencionDAO lugarAtencionDAO;

	@Override
	public List<Provincia> getAllProvincias() throws BusinessException {
		try {
			List<Provincia> provincias = provinciaDAO.getAll();
			if (Utils.isNotEmptyCollection(provincias)) {
				Collections.sort(provincias, new BeanComparator("nombre"));
			}
			return provincias;
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Provincias", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Localidad> getLocalidadesByProvincia(Long idProvincia)
			throws BusinessException {
		try {
			LocalidadFilter filter = new LocalidadFilter();
			filter.setIdProvincia(idProvincia);
			List<Localidad> localidades = localidadDAO.search(filter);
			if (Utils.isNotEmptyCollection(localidades)) {
				Collections.sort(localidades, new BeanComparator("nombre"));
			}
			return localidades;
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Localidades de Provincia ID: "
					+ idProvincia, daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<ObraSocial> getAllObrasSociales() throws BusinessException {
		try {
			List<ObraSocial> obrasSociales = obraSocialDAO.getAll();
			if (Utils.isNotEmptyCollection(obrasSociales)) {
				Collections.sort(obrasSociales, new BeanComparator("nombre"));
			}
			return obrasSociales;
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Obras Sociales", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<Barrio> getAllBarrios() throws BusinessException {
		try {
			List<Barrio> barrios = barrioDAO.getAll();
			if (Utils.isNotEmptyCollection(barrios)) {
				Collections.sort(barrios, new BeanComparator("nombre"));
			}
			return barrios;
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Barrios", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public List<LugarAtencion> getAllLugaresAtencion() throws BusinessException {

		try {
			List<LugarAtencion> lugaresAtencion = lugarAtencionDAO.getAll();
			if (Utils.isNotEmptyCollection(lugaresAtencion)) {
				Collections.sort(lugaresAtencion, new BeanComparator("nombre"));
			}
			return lugaresAtencion;
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Lugares de Atención", daexc);
			throw new BusinessException(daexc);
		}
	}

	public void setProvinciaDAO(ProvinciaDAO provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
	}

	public void setLocalidadDAO(LocalidadDAO localidadDAO) {
		this.localidadDAO = localidadDAO;
	}

	public void setObraSocialDAO(ObraSocialDAO obraSocialDAO) {
		this.obraSocialDAO = obraSocialDAO;
	}

	public void setBarrioDAO(BarrioDAO barrioDAO) {
		this.barrioDAO = barrioDAO;
	}

	public void setLugarAtencionDAO(LugarAtencionDAO lugarAtencionDAO) {
		this.lugarAtencionDAO = lugarAtencionDAO;
	}

}

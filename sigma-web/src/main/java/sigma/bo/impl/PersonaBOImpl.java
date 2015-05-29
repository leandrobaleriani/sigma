package sigma.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sigma.bo.PersonaBO;
import sigma.dao.PersonaDAO;
import sigma.entities.Persona;
import sigma.exceptions.BusinessException;
import sigma.exceptions.DataAccessException;
import sigma.filters.PersonaFilter;
import sigma.results.SearchResult;

public class PersonaBOImpl implements PersonaBO {

	private final Logger LOGGER = LoggerFactory.getLogger(PersonaBOImpl.class);

	private PersonaDAO personaDAO;

	@Override
	public SearchResult<Persona> buscarPacientes(PersonaFilter filter)
			throws BusinessException {

		try {
			filter.setPaciente(Boolean.TRUE);
			return personaDAO.search(filter);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al realizar busqueda de Personas", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void ingresar(Persona persona) throws BusinessException {
		try {
			personaDAO.saveOrUpdate(persona);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Persona", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public void actualizar(Persona persona) throws BusinessException {
		try {
			personaDAO.saveOrUpdate(persona);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al guardar Persona", daexc);
			throw new BusinessException(daexc);
		}
	}

	@Override
	public Persona obtener(Long id) throws BusinessException {
		try {
			return personaDAO.getById(id);
		} catch (DataAccessException daexc) {
			LOGGER.error("Error al obtener Persona", daexc);
			throw new BusinessException(daexc);
		}
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

}

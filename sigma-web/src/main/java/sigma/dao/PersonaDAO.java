package sigma.dao;

import sigma.entities.Persona;
import sigma.exceptions.DataAccessException;
import sigma.filters.PersonaFilter;
import sigma.results.SearchResult;


public interface PersonaDAO extends GenericDAO<Persona> {

	SearchResult<Persona> search(PersonaFilter filter) throws DataAccessException;

}

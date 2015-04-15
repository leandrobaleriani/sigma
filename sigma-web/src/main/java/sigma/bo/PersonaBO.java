package sigma.bo;

import sigma.entities.Persona;
import sigma.exceptions.BusinessException;
import sigma.filters.PersonaFilter;
import sigma.results.SearchResult;

public interface PersonaBO {

	SearchResult<Persona> search(PersonaFilter filter) throws BusinessException;
	
	void saveOrUpdate(Persona persona)  throws BusinessException;
	
	Persona getById(Long id) throws BusinessException;
}

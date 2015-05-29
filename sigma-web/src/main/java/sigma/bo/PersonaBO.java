package sigma.bo;

import sigma.entities.Persona;
import sigma.exceptions.BusinessException;
import sigma.filters.PersonaFilter;
import sigma.results.SearchResult;

public interface PersonaBO {

	SearchResult<Persona> buscarPacientes(PersonaFilter filter) throws BusinessException;
	
	void ingresar(Persona persona)  throws BusinessException;
	
	void actualizar(Persona persona)  throws BusinessException;
	
	Persona obtener(Long id) throws BusinessException;
}

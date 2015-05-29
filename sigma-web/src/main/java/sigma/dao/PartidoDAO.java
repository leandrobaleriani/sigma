package sigma.dao;

import java.util.List;

import sigma.entities.Partido;
import sigma.exceptions.DataAccessException;
import sigma.filters.PartidoFilter;

public interface PartidoDAO extends GenericDAO<Partido> {

	List<Partido> search(PartidoFilter filter) throws DataAccessException;

}

package sigma.dao;

import java.util.List;

import sigma.entities.Atencion;
import sigma.exceptions.DataAccessException;
import sigma.filters.AtencionFilter;

public interface AtencionDAO extends GenericDAO<Atencion> {

	List<Atencion> search(AtencionFilter filter) throws DataAccessException;

}

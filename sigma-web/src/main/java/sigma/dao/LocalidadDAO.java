package sigma.dao;

import java.util.List;

import sigma.entities.Localidad;
import sigma.exceptions.DataAccessException;
import sigma.filters.LocalidadFilter;

public interface LocalidadDAO extends GenericDAO<Localidad> {

	List<Localidad> search(LocalidadFilter filter) throws DataAccessException;

}

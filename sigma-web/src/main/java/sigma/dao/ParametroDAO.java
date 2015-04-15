package sigma.dao;

import sigma.entities.Parametro;
import sigma.exceptions.DataAccessException;

public interface ParametroDAO extends GenericDAO<Parametro> {

	int getMaxAttempts() throws DataAccessException;

}

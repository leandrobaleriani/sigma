package sigma.dao;

import java.util.List;

import sigma.exceptions.DataAccessException;

public interface GenericDAO<E> {

	List<E> getAll() throws DataAccessException;

	E getById(Long id) throws DataAccessException;

	void saveOrUpdate(E e) throws DataAccessException;

	void delete(E e) throws DataAccessException;

	void delete(Long id) throws DataAccessException;

}

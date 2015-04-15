package sigma.bo;

import java.util.List;

import sigma.entities.Atencion;
import sigma.exceptions.BusinessException;
import sigma.filters.AtencionFilter;

public interface AtencionBO {

	List<Atencion> search(AtencionFilter filter) throws BusinessException;

	void saveOrUpdate(Atencion atencion) throws BusinessException;

	Atencion getById(Long id) throws BusinessException;

	List<Atencion> getAtencionesEnEspera(Long idLugarAtencion)  throws BusinessException;
}

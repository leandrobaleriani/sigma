package sigma.bo;

import java.util.List;

import sigma.entities.Atencion;
import sigma.exceptions.BusinessException;
import sigma.filters.AtencionFilter;

public interface AtencionBO {

	List<Atencion> search(AtencionFilter filter) throws BusinessException;

	void saveOrUpdate(Atencion atencion) throws BusinessException;

	Atencion getById(Long id) throws BusinessException;

	List<Atencion> getAtencionesEnEspera() throws BusinessException;

	List<Atencion> getAtenciones() throws BusinessException;

	List<Atencion> getAtencionesPendientes(Long idUsuario)
			throws BusinessException;

	boolean atender(Long idAtencion, Long idUsuarioAtencion)
			throws BusinessException;

	List<Atencion> getAtencionesUltimas(Long idUsuario)
			throws BusinessException;

	void finalizar(Long idAtencion, String diagnostico)
			throws BusinessException;

	List<Atencion> getAtencionesHistorial(Long idPersona)
			throws BusinessException;


}

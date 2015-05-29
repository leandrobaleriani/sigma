package sigma.bo;

import java.util.List;

import sigma.entities.Atencion;
import sigma.entities.Persona;
import sigma.entities.TipoAtencionEnum;
import sigma.exceptions.BusinessException;
import sigma.filters.AtencionFilter;

public interface AtencionBO {

	List<Atencion> buscar(AtencionFilter filter) throws BusinessException;

	Atencion obtener(Long id) throws BusinessException;

	List<Atencion> obtenerEnEsperaEnAtencion() throws BusinessException;

	List<Atencion> obtenerEnEspera() throws BusinessException;

	List<Atencion> obtenerEnAtencion() throws BusinessException;

	List<Atencion> obtenerPendientesPorUsuario(Long idUsuario)
			throws BusinessException;

	boolean atender(Long idAtencion, Long idUsuarioAtencion)
			throws BusinessException;

	List<Atencion> obtenerUltimasAtenciones(Long idUsuario)
			throws BusinessException;

	void finalizar(Long idAtencion, String diagnostico)
			throws BusinessException;

	void finalizarMotivo(Long idAtencion, String motivo)
			throws BusinessException;

	List<Atencion> obtenerHistorial(Long idPersona) throws BusinessException;
	
	void recepcionarActualizar(Long idUsuario, TipoAtencionEnum tipoAtencion,
			Persona persona, Long idLugarAtencion) throws BusinessException;
	
	void recepcionarIngresar(Long idUsuario, TipoAtencionEnum tipoAtencion,
			Persona persona, Long idLugarAtencion) throws BusinessException;

}

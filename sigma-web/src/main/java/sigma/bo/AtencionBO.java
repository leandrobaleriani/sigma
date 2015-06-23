package sigma.bo;

import java.util.Date;
import java.util.List;

import sigma.dto.DashboardDTO;
import sigma.entities.Atencion;
import sigma.entities.Persona;
import sigma.entities.TipoAtencionEnum;
import sigma.exceptions.BusinessException;

public interface AtencionBO {

	Atencion obtener(Long id) throws BusinessException;

	List<Atencion> obtenerEnEsperaEnAtencion() throws BusinessException;

	List<Atencion> obtenerEnEspera() throws BusinessException;

	List<Atencion> obtenerEnAtencion() throws BusinessException;

	List<Atencion> obtenerFinalizadas(Date desde, Date hasta, Long idUsuario)
			throws BusinessException;

	List<Atencion> obtenerPendientesPorUsuario(Long idUsuario)
			throws BusinessException;

	boolean atender(Long idAtencion, Long idUsuarioAtencion)
			throws BusinessException;

	List<Atencion> obtenerUltimasAtenciones(Long idUsuario)
			throws BusinessException;

	void finalizar(Long idAtencion, String diagnostico, boolean internacion)
			throws BusinessException;

	void finalizarMotivo(Long idAtencion, String motivo,
			Long idUsuarioCancelacion) throws BusinessException;

	List<Atencion> obtenerHistorial(Long idPersona) throws BusinessException;

	void recepcionarActualizar(Long idUsuario, TipoAtencionEnum tipoAtencion,
			Persona persona, Long idLugarAtencion) throws BusinessException;

	void recepcionarIngresar(Long idUsuario, TipoAtencionEnum tipoAtencion,
			Persona persona, Long idLugarAtencion) throws BusinessException;
	
	DashboardDTO obtenerDashboard() throws BusinessException;

}

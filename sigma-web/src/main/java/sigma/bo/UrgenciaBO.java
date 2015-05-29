package sigma.bo;

import java.util.List;

import sigma.entities.User;
import sigma.exceptions.BusinessException;

public interface UrgenciaBO {

	List<User> obtenerEnUrgencia() throws BusinessException;
	
	List<User> obtenerEnAtencion() throws BusinessException;

	void establecerUrgencia(List<Long> idsUsuario) throws BusinessException;
	
	void finalizarUrgencia(List<Long> idsUsuario) throws BusinessException;
}

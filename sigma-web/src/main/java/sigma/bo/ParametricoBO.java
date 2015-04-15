package sigma.bo;

import java.util.List;

import sigma.entities.Barrio;
import sigma.entities.Localidad;
import sigma.entities.LugarAtencion;
import sigma.entities.ObraSocial;
import sigma.entities.Provincia;
import sigma.exceptions.BusinessException;

public interface ParametricoBO {

	List<Provincia> getAllProvincias() throws BusinessException;
	
	List<Localidad> getLocalidadesByProvincia(Long idProvincia) throws BusinessException;
	
	List<ObraSocial> getAllObrasSociales() throws BusinessException;
	
	List<Barrio> getAllBarrios() throws BusinessException;
	
	List<LugarAtencion> getAllLugaresAtencion() throws BusinessException;
}

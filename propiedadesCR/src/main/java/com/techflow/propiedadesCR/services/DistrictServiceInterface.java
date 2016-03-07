package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;

/**
<h1>Interfaz a implementar por el DistrictService</h1>
* Interfaz para todas las operaciones del distrito
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface DistrictServiceInterface {
	/**
	  * Retorna todas las entidades de tipo DistrictsPOJO
	  *  
	  * @return las entidades del tipo.
	  */
	List<DistrictPOJO> getAll();
	
	/**
	  * Retorna una sola entidad por su id.
	  *  
	  * @param pIdDistrict - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	Tdistrict getDistrictById(int pIdDistrict);
}

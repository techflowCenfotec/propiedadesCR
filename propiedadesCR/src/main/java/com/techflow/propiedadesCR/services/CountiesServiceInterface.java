package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.CountiesRequest;
import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.pojo.CountyPOJO;

/**
* <h1>Interfaz a implementar por el CountiesService</h1>
* Interfaz para las operaciones de los cantones.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface CountiesServiceInterface {

	/**
	  * Retorna todas las entidades del tipo.
	  *  
	  * @return List<CountyPOJO> Todas las entidades del tipo.
	  */
	List<CountyPOJO> getAll();
	
	/**
	  * Retorna una sola entidad por su id.
	  *  
	  * @param pIdCounty - Id del cantón. No debe ser nulo.
	  * @return Tcounty Una entidad del tipo.
	  */
	Tcounty getCountyById(int pIdCounty);
}

package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;

/**
* <h1>Interfaz a implementar por la provincia</h1>
* Interfaz para todas las operaciones de las provincias
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface ProvinceServiceInterface {
	/**
	  * Retorna todas las entidades de tipo ProvincePOJO
	  *  
	  * @return las entidades del tipo.
	  */
	List<ProvincePOJO> getAll();
	/**
	  * Retorna una sola entidad por su id.
	  *  
	  * @param pIdProvince - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	Tprovince getProvinceById(int pIdProvince);
}

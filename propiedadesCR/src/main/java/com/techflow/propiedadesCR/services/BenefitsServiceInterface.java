package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.BenefitsRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;

/**
* <h1>Interfaz a implementar por el BenefitsService</h1>
* Interfaz para las operaciones de los beneficios.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface BenefitsServiceInterface {

	/**
	  * Retorna todas las entidades del tipo.
	  *  
	  * @return todas las entidades del tipo.
	  */
	List<BenefitsPOJO> getAll();
	
	/**
	  * Retorna una sola entidad por su id.
	  *  
	  * @param pIdBenefit - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	Tbenefit getBenefitById(int pIdBenefit);
	
	/**
	  * Retorna una lista de todas las entidades por su id.
	  * 
	  * @param pBenefits - no debe ser nula.
	  * @return todas las entidades del tipo.
	  */

	List<Tbenefit> getBenefits(int[] pBenefits);
}

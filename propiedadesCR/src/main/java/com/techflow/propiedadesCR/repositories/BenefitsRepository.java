package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tbenefit;
/**
* <h1>Repositorio de los Beneficios</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementación
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface BenefitsRepository extends CrudRepository<Tbenefit, Integer> {

	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<Tbenefit> - las entidades del tipo
	  */
	List<Tbenefit> findAll();
	
	/**
	  * Retorna una lista de entidades. El arreglo que recibe contiene los ids de
	  * los beneficios a buscar especificamente.
	  * 
	  * @param pBenefits - no debe ser nula.
	  * @return List<Tbenefit> - las entidades del tipo
	  */
	List<Tbenefit> findByIdBenefitIn(int[] pBenefits);
}

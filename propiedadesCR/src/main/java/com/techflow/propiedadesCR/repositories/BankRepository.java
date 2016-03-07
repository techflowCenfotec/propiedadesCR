package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tbank;
/**
* <h1>Repositorio de bancos</h1>
* Esta clase se encarga de la comunicación con la base de datos
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
public interface BankRepository extends CrudRepository<Tbank, Integer>{
	/**
	 * Método que se encarga de realizar una consulta de todos los bancos
	 * que existen en la base de datos
	 */
	List<Tbank> findAll();
}

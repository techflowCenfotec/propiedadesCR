package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Trole;

/**
 * <h1>Repositorio del rol</h1>
 * Clase comunicarse con la base de datos y guardar los datos en ella.
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/26/2016
 */

public interface RoleRepository extends CrudRepository<Trole, Integer>{
	/**
	 * Método que se encarga de realizar la consulta a la base de datos
	 */
	List<Trole> findAll();
}

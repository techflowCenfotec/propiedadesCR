package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Tpermission;

/**
 * <h1>Repositorio de permisos</h1>
 * Clase que se comunica con la base de datos y guarda los datos en ella.
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/29/2016
 */
public interface PermissionsRepository extends CrudRepository<Tpermission, Integer>{
	/**
	 * Método que se encarga de realizar una consulta de todos los permisos
	 * que existen en la base de datos
	 */
	List<Tpermission> findAll();
}

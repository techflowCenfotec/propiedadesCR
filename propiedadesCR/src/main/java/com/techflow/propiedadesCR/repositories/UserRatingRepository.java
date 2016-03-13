/**
* <h1>Repositorio de rating de usuarios</h1>
* 
* Esta clase se encarga de la comunicación con la BD
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 12/3/2016
*/

package com.techflow.propiedadesCR.repositories;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TuserRating;;

public interface UserRatingRepository extends CrudRepository<TuserRating, Integer> {

}

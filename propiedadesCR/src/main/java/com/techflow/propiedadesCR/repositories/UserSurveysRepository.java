package com.techflow.propiedadesCR.repositories;


import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TuserSurvey;
/**
* <h1>Repositorio de cuestionarios de los usuarios</h1>
* Esta clase se encarga de la comunicación con la base de datos
*
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
public interface UserSurveysRepository extends CrudRepository<TuserSurvey, Integer>{

}

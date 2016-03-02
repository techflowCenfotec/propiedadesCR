package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface UsersServiceInterface {
	/**
	  * Descripción de lo que hace la función.
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param usersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return response Retorna la respuesta del repositorio hacia el controlador.
	  */
	List<UserPOJO> getAll(UsersRequest pr);
	Tuser saveUser(UsersRequest pr);
}

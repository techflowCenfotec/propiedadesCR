/**
* <h1>Controlador de rating de usuarios</h1>
* 
* Esta clase se encarga de realizar la 
* comunicación entre el backend y el frontend.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 12/3/2016
*/

package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.UserRatingRequest;
import com.techflow.propiedadesCR.contracts.UserRatingResponse;
import com.techflow.propiedadesCR.pojo.UserRatingPOJO;
import com.techflow.propiedadesCR.services.UserRatingServiceInterface;



@RestController
@RequestMapping(value="rest/protected/userRating")
public class UserRatingController {

	/** 
     * Este objeto proporciona los diferentes servicios para los usuarios
     */
	@Autowired private UserRatingServiceInterface userRatingService;
	
	/**
	    * Este método retorna el rating del usuario.
	    *
	    * @param puserRequest Este parámetro encapsula la información solicitada en el metodo.
		*
	    * @return userRatingResponse Retorna la respuesta del sevicio hacia el frontend.
	    */
	@RequestMapping(value="/saveRating", method = RequestMethod.POST)
	public UserRatingResponse saveRating(@RequestBody UserRatingRequest puserRequest) {
		UserRatingResponse userRatingResponse = new UserRatingResponse();
	
		if(userRatingService.saveRating(puserRequest)!=null)
			userRatingResponse.setCode(200);
		else
			userRatingResponse.setCode(400);
		
	return userRatingResponse;	
	
	}
	
	/**
	    * Este método retorna el rating que realizo un cliente a un vendedor.
	    *
	    * @param puserRequest Este parámetro encapsula la información solicitada en el metodo.
		*
	    * @return userRatingResponse Retorna la respuesta del sevicio hacia el frontend.
	    */
	@RequestMapping(value="/getRating", method = RequestMethod.POST)
	public UserRatingResponse getRating(@RequestBody UserRatingRequest puserRating) {
		UserRatingResponse userRatingResponse = new UserRatingResponse();
		UserRatingPOJO userRating = userRatingService.getRating(puserRating);
		
		if(userRating!=null){
			userRatingResponse.setUserRating(userRating);
			userRatingResponse.setCode(200);
		}
		else
			userRatingResponse.setCode(400);
		
	return userRatingResponse;	
	
	}
	
	/**
	    * Este método retorna el rating modificado del usuario.
	    *
	    * @param puserRequest Este parámetro encapsula la información solicitada en el metodo.
		*
	    * @return userRatingResponse Retorna la respuesta del sevicio hacia el frontend.
	    */
	
	@RequestMapping(value="/modifyRating", method = RequestMethod.POST)
	public UserRatingResponse modifyRating(@RequestBody UserRatingRequest puserRequest) {
		UserRatingResponse userRatingResponse = new UserRatingResponse();
	
		if(userRatingService.modifyRating(puserRequest)!=null)
			userRatingResponse.setCode(200);
		else
			userRatingResponse.setCode(400);
		
	return userRatingResponse;	
	
	}
}

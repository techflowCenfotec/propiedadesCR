/**
* <h1>Servicio de rating de usuarios</h1> 
* Esta clase  se encarga de comunicarse con el repositorio.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 12/3/2016
*/



package com.techflow.propiedadesCR.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.UserRatingRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.ejb.TuserRating;
import com.techflow.propiedadesCR.pojo.UserRatingPOJO;
import com.techflow.propiedadesCR.repositories.UserRatingRepository;

@Service
public class UserRatingService implements UserRatingServiceInterface{
	/**	 
     * El objeto usersRatingRepository es el que se encarga de la comunicación con la BD. 
     */
	@Autowired private UserRatingRepository userRatingRepository;
	
	/**
	  * Este método registra el rating de un usuario en el sistema.
	  *
	  * @param puserRating Encapsula los datos requeridos por el usuario.
      * 
	  * @return nRating Retorna el rating registrado a un usuario.
	  */
	@Override
	public TuserRating saveRating(UserRatingRequest puserRating) {
		// TODO Auto-generated method stub
		TuserRating userRating = new TuserRating();
		Tuser client = new Tuser();
		Tuser vendor = new Tuser();
		BeanUtils.copyProperties(puserRating.getRating(), userRating);
		client.setIdUser(puserRating.getIdClient());
		vendor.setIdUser(puserRating.getIdVendor());
		userRating.setTuser1(client);
		userRating.setTuser2(vendor);
		
		TuserRating nRating = userRatingRepository.save(userRating);
		
		return nRating;
	}
	
	/**
	  * Este método consulta el rating de un usuario a un vendedor
	  *
	  * @param puserRating Encapsula los datos requeridos por el usuario.
      * 
	  * @return nRating Retorna el rating del usuario a un vendedor.
	  */
	@Override
	public UserRatingPOJO getRating(UserRatingRequest puserRating) {
		TuserRating userRating = new TuserRating();
		UserRatingPOJO userRatingPOJO = new UserRatingPOJO();
		Tuser client = new Tuser();
		Tuser vendor = new Tuser();
		client.setIdUser(puserRating.getIdClient());
		vendor.setIdUser(puserRating.getIdVendor());
		userRating = userRatingRepository.findByTuser1AndTuser2(client, vendor);
		BeanUtils.copyProperties(userRating, userRatingPOJO);
		
		return userRatingPOJO;
	}
	
	/**
	  * Este método modifica el rating de un usuario en el sistema.
	  *
	  * @param puserRating Encapsula los datos requeridos por el usuario.
      * 
	  * @return nRating Retorna el rating modificado a un usuario.
	  */
	@Override
	public TuserRating modifyRating(UserRatingRequest puserRating) {
		TuserRating userRating = new TuserRating();
		Tuser client = new Tuser();
		Tuser vendor = new Tuser();
		BeanUtils.copyProperties(puserRating.getRating(), userRating);
		client.setIdUser(puserRating.getIdClient());
		vendor.setIdUser(puserRating.getIdVendor());
		userRating.setTuser1(client);
		userRating.setTuser2(vendor);
		userRating.setIdRating(puserRating.getRating().getIdRating());
		TuserRating nRating = userRatingRepository.save(userRating);
		
		return nRating;
		
	}
	
	
}

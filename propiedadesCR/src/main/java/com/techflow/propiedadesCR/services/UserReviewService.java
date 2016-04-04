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

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.UserReviewRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.ejb.TuserReview;
import com.techflow.propiedadesCR.pojo.UserReviewPOJO;
import com.techflow.propiedadesCR.repositories.UserReviewRepository;

@Service
public class UserReviewService implements UserReviewServiceInterface{
	/**	 
     * El objeto usersRatingRepository es el que se encarga de la comunicación con la BD. 
     */
	@Autowired private UserReviewRepository userRatingRepository;
	
	/**
	  * Este método registra el rating de un usuario en el sistema.
	  *
	  * @param puserRating Encapsula los datos requeridos por el usuario.
      * 
	  * @return nRating Retorna el rating registrado a un usuario.
	  */
	@Override
	public TuserReview saveRating(UserReviewRequest puserRating) {
		// TODO Auto-generated method stub
		TuserReview userReview = new TuserReview();
		Tuser client = new Tuser();
		Tuser vendor = new Tuser();
		BeanUtils.copyProperties(puserRating.getRating(), userReview);
		client.setIdUser(puserRating.getIdClient());
		vendor.setIdUser(puserRating.getIdVendor());
		userReview.setTuser1(client);
		userReview.setTuser2(vendor);
		userReview.setComment("Sup");
		userReview.setRegistrationDate(new Date());
		TuserReview nRating = userRatingRepository.save(userReview);
		
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
	public UserReviewPOJO getRating(UserReviewRequest puserRating) {
		TuserReview userRating = new TuserReview();
		UserReviewPOJO userRatingPOJO = new UserReviewPOJO();
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
	public TuserReview modifyRating(UserReviewRequest puserReview) {
		TuserReview userReview = new TuserReview();
		Tuser client = new Tuser();
		Tuser vendor = new Tuser();
		BeanUtils.copyProperties(puserReview.getRating(), userReview);
		client.setIdUser(puserReview.getIdClient());
		vendor.setIdUser(puserReview.getIdVendor());
		userReview.setTuser1(client);
		userReview.setTuser2(vendor);
		userReview.setIdReview(puserReview.getRating().getIdReview());
		userReview.setRegistrationDate(new Date());
		TuserReview nRating = userRatingRepository.save(userReview);
		
		return nRating;
		
	}
	
	
}

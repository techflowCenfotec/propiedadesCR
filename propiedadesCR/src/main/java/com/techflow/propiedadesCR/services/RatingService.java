package com.techflow.propiedadesCR.services;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.ReviewPropertyRequest;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyReview;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.ReviewPropertyPOJO;
import com.techflow.propiedadesCR.repositories.ReviewRepository;

/**
* <h1>Servicio de Calificaciones</h1>
* Servicio que provee una implementación a la interface
* de los métodos addRating(RatingRequest pRating).
*
* @author  Walter Gómez
* @version 1.0
* @since 16/3/2016
*/
@Service
public class RatingService implements RatingServiceInterface {

	/**
	 * Atributo de acceso al repositorio de las calificaciones de
	 * las propiedades.
	 */
	@Autowired private ReviewRepository reviewRepository;
	
	/**
	  * Agrega la calificación de la propiedad. Retorna la entidad almacenada por si hay que realizar operaciones adicionales
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @param pRating Contiene la infomarción a almacenar a la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	  * @return rating Una entidad del tipo.
	  */
	@Override
	@Transactional
	public TpropertyReview addRating(ReviewPropertyRequest pRating) {
		TpropertyReview rating = new TpropertyReview();
		Tuser user = new Tuser();
		Tproperty property =  new Tproperty();
		
		BeanUtils.copyProperties(pRating.getRating(), rating);
		user.setIdUser(pRating.getRating().getTuser().getIdUser());
		property.setIdProperty(pRating.getRating().getTproperty().getIdProperty());
		rating.setTuser(user);
		rating.setTproperty(property);
		rating.setRegistrationDate(new Date());
		
		TpropertyReview nRating = reviewRepository.save(rating);
		
		return nRating;
	}


	/**
	  * Método encargado de modificar la calificación de la propiedad
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @author Valeria Ramírez
	  * 
	  * @param pRating Contiene la infomarción a editar en la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	  * 
	  * @return nRating Una entidad del tipo.
	  */
	public TpropertyReview editRating(ReviewPropertyRequest pRating) {
		TpropertyReview rating = new TpropertyReview();
		Tuser user = new Tuser();
		Tproperty property =  new Tproperty();
	
		BeanUtils.copyProperties(pRating.getRating(), rating);
		user.setIdUser(pRating.getRating().getTuser().getIdUser());
		property.setIdProperty(pRating.getRating().getTproperty().getIdProperty());
		rating.setIdReview(pRating.getRating().getIdReview());
		rating.setTuser(user);
		rating.setTproperty(property);
		rating.setRegistrationDate(new Date());
	
		TpropertyReview nRating = reviewRepository.save(rating);
	
		return nRating;
	}
	
	/**
	 * Método que se encarga de levantar la consulta de  un rating
	 * 
	 * @author Valeria Ramírez
	 *
	 * @param pratingRequest Parámetro que contiene información del rating 
	 * que se desea consultar
	 * 
	 * @return ratingData Objeto que contiene la información del rating consultado
	 */
	public ReviewPropertyPOJO getRatingById(ReviewPropertyRequest pratingRequest){
		//TpropertyRating propertyRating = ratingRepository.findOne(pratingRequest.getRating().getIdRating());
		Tuser user = new Tuser();
		Tproperty property = new Tproperty();
		BeanUtils.copyProperties(pratingRequest.getRating().getTuser(), user);
		BeanUtils.copyProperties(pratingRequest.getRating().getTproperty(), property);
		TpropertyReview propertyRating = reviewRepository.findByTuserAndTproperty(user,property);
		ReviewPropertyPOJO ratingData = new ReviewPropertyPOJO();
		BeanUtils.copyProperties(propertyRating, ratingData);
		ratingData.setAverageRating(propertyRating.getAverageRating());
		ratingData.setIdReview(propertyRating.getIdReview());
		return ratingData;
	}

}
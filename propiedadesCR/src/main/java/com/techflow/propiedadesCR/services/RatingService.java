package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.RatingRequest;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.RatingPOJO;
import com.techflow.propiedadesCR.repositories.RatingRepository;

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
	@Autowired private RatingRepository ratingRepository;
	
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
	public TpropertyRating addRating(RatingRequest pRating) {
		TpropertyRating rating = new TpropertyRating();
		Tuser user = new Tuser();
		Tproperty property =  new Tproperty();
		
		BeanUtils.copyProperties(pRating.getRating(), rating);
		user.setIdUser(pRating.getRating().getTuser().getIdUser());
		property.setIdProperty(pRating.getRating().getTproperty().getIdProperty());
		rating.setTuser(user);
		rating.setTproperty(property);
		
		TpropertyRating nRating = ratingRepository.save(rating);
		
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
	public TpropertyRating editRating(RatingRequest pRating) {
		TpropertyRating rating = new TpropertyRating();
		Tuser user = new Tuser();
		Tproperty property =  new Tproperty();
	
		BeanUtils.copyProperties(pRating.getRating(), rating);
		user.setIdUser(pRating.getRating().getTuser().getIdUser());
		property.setIdProperty(pRating.getRating().getTproperty().getIdProperty());
		rating.setIdRating(pRating.getRating().getIdRating());
		rating.setTuser(user);
		rating.setTproperty(property);
	
		TpropertyRating nRating = ratingRepository.save(rating);
	
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
	public RatingPOJO getRatingById(RatingRequest pratingRequest){
		//TpropertyRating propertyRating = ratingRepository.findOne(pratingRequest.getRating().getIdRating());
		TpropertyRating propertyRating = ratingRepository.findOne(pratingRequest.getRating().getTproperty().getIdProperty());
		RatingPOJO ratingData = new RatingPOJO();
		BeanUtils.copyProperties(propertyRating, ratingData);
		ratingData.setAverageRating(propertyRating.getAverageRating());
		ratingData.setIdRating(propertyRating.getIdRating());
		return ratingData;
	}

}
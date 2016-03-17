package com.techflow.propiedadesCR.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.RatingRequest;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
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
		
		BeanUtils.copyProperties(pRating, rating);
				
		TpropertyRating nRating = ratingRepository.save(rating);
		
		return nRating;
	}

}

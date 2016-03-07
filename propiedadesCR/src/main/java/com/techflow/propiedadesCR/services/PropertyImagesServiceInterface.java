package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;

/**
* <h1>Interfaz a implementar por el PropertyImageService</h1>
* Interfaz para las operaciones de las imagenes.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertyImagesServiceInterface {

	/**
	  * Retorna todas las entidades del tipo.
	  *  
	  * @return List<PropertyImagePOJO> Todas las entidades del tipo.
	  */
	List<PropertyImagePOJO> getAll();
	
	/**
	  * Retorna una entidad del tipo por el id de
	  * la propiedad.
	  * 
	  * @param pIdPropiedad Ids de la propiedad. No debe ser nulo.
	  * @return TpropertyImage Una entidad del tipo.
	  */
	TpropertyImage getImageById(int pIdPropiedad);
	
}

package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;
import com.techflow.propiedadesCR.repositories.PropertyImageRepository;

/**
* <h1>Servicio para obtener las imagenes de la propiedad</h1>
* Servicio que provee la implementación de los métodos 
* getAll() y getImageById().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class PropertyImageService implements PropertyImagesServiceInterface {

	/**
	 * Atributo de acceso al repositorio de las imagenes de las propiedades.
	 */
	@Autowired private PropertyImageRepository imageRepository;
	
	/**
	  * Retorna una lista de objetos PropertyImagePOJO
	  * 
	  * @return uiProperties Todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<PropertyImagePOJO> getAll() {
		List<TpropertyImage> images = imageRepository.findAll();
		return generateImageDtos(images);
	}

	/**
	  * Toma las imagenes de las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pPropertyImages Lista de ejb de imágenes. No debe ser nula.
	  * @return uiProperties Todas las entidades de tipo POJO.
	  */
	private List<PropertyImagePOJO> generateImageDtos(List<TpropertyImage> pPropertyImages) {
		List<PropertyImagePOJO> uiProperties = new ArrayList<PropertyImagePOJO>();
		pPropertyImages.stream().forEach(u -> {
			PropertyImagePOJO dto = new PropertyImagePOJO();
			BeanUtils.copyProperties(u, dto);
			uiProperties.add(dto);
		});
		return uiProperties;
	}
	
	/**
	  * Alamacena la entidad. Retorna la entidad almacenada por si hay que realizar operaciones adicionales
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @param pPropertyImg Contiene la infomarción a almacenar a la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	  * @return propImg Una entidad del tipo.
	  */
	@Override
	@Transactional
	public TpropertyImage savePropertyImg(TpropertyImage pPropertyImg) {
		TpropertyImage propImg = imageRepository.save(pPropertyImg);
		return propImg;
	}
	
	/**
	  * Retorna a través del repositorio el ejb de la imagen de la propiedad.
	  * 
	  * @param pIdPropiedad Id de la propiedad a buscar. No debe ser nulo.
	  * @return TpropertyImage Una entidad del tipo.
	  */
	@Override
	@Transactional
	public TpropertyImage getImageById(int pIdPropiedad) {
		return imageRepository.findOne(pIdPropiedad);
	}
}

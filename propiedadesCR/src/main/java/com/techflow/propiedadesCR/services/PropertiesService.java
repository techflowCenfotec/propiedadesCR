package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.repositories.PropertiesRepository;

/**
* <h1>Servicio para obtener las propiedades</h1>
* Servicio que provee la implementación de los métodos 
* getAll(), getPropertyById() y saveProperty().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class PropertiesService implements PropertiesServiceInterface {

	@Autowired private PropertiesRepository propertiesRepository;
	@Autowired private DistrictServiceInterface districtService;
	@Autowired private PropertyImagesServiceInterface imageService;
	
	
	/**
	  * Retorna una lista de objetos PropertyPOJO
	  * 
	  * @return todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<PropertyPOJO> getAll() {
		List<Tproperty> properties = propertiesRepository.findAll();
		return generatePropDtos(properties);
	}
	
	/**
	  * Toma las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pProperties - no debe ser nula.
	  * @return todas las entidades de tipo POJO.
	  */
	private List<PropertyPOJO> generatePropDtos(List<Tproperty> pProperties) {
		List<PropertyPOJO> uiProperties = new ArrayList<PropertyPOJO>();
		pProperties.stream().forEach(u -> {
			PropertyPOJO dto = new PropertyPOJO();
			DistrictPOJO district = new DistrictPOJO();
			PropertyImagePOJO image = new PropertyImagePOJO();
			List<PropertyImagePOJO> imgList = new ArrayList<PropertyImagePOJO>();
			
			Tdistrict tdist = districtService.getDistrictById(u.getTdistrict().getIdDisctrict());
			BeanUtils.copyProperties(tdist, district);
			
			//Debería traer una lista desde el servivio
			//Implementar método en el repositorio [PENDIENTE]
			TpropertyImage img = imageService.getImageById(u.getIdProperty());
			BeanUtils.copyProperties(img, image);
			imgList.add(image);
			
			BeanUtils.copyProperties(u, dto);
			dto.setTbenefits(null);
			dto.setTdistrict(district);
			dto.setTpropertyType(null);
			dto.setTuser(null);
			dto.setTpropertyComments(null);
			dto.setTpropertyRatings(null);
			dto.setTusers(null);
			dto.setTpropertyImages(imgList);
			uiProperties.add(dto);
		});
		return uiProperties;
	}

	/**
	  * Alamacena la entidad. Retorna la entidad almacenada por si hay que realizar operaciones adicionales
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @param pProperty - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tproperty saveProperty(Tproperty pProperty) {
		Tproperty nProperty =  propertiesRepository.save(pProperty);
		return nProperty;
	}

	/**
	  * Retorna a través del repositorio el ejb de la propiedad.
	  * 
	  * @param pIdProperty - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	@Override
	public Tproperty getPropertyById(int pIdProperty) {
		return propertiesRepository.findOne(pIdProperty);
	}

}

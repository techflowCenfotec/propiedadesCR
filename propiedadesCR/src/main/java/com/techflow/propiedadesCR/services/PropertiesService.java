package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyComment;
import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;
import com.techflow.propiedadesCR.pojo.CommentsPOJO;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;
import com.techflow.propiedadesCR.pojo.RatingPOJO;
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

	/**
	 * Atributo de acceso al repositorio de las propiedades.
	 */
	@Autowired private PropertiesRepository propertiesRepository;
	
	/**
	  * Retorna una lista de objetos PropertyPOJO
	  * 
	  * @return uiProperties Todas las entidades del tipo.
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
	  * @param pProperties Lista de ejb de propiedades. No debe ser nula.
	  * @return uiProperties Todas las entidades de tipo POJO.
	  */
	private List<PropertyPOJO> generatePropDtos(List<Tproperty> pProperties) {
		List<PropertyPOJO> uiProperties = new ArrayList<PropertyPOJO>();
		pProperties.stream().forEach(u -> {
			PropertyPOJO dto = new PropertyPOJO();
			BeanUtils.copyProperties(u, dto);
			BeanUtils.copyProperties(u.getTdistrict(), dto.getTdistrict());
			BeanUtils.copyProperties(u.getTpropertyType(), dto.getTpropertyType());
			dto.setTbenefits(benefitsDtos(u.getTbenefits()));
			dto.setTuser(null);
			dto.setTpropertyImages(imagesDtos(u.getTpropertyImages()));
			dto.setTpropertyComments(null);
			dto.setTpropertyRatings(null);
			dto.setTusers(null);
			uiProperties.add(dto);
		});
		return uiProperties;
	}
	
	/**
	  * Toma los beneficios de los ejbs y los convierte en POJOs.
	  * 
	  * @param pBenefits Lista de ejb de beneficios. No debe ser nula.
	  * @return benList Todas las entidades de tipo POJO.
	  */
	private List<BenefitsPOJO> benefitsDtos(List<Tbenefit> pBenefits) {
		List<BenefitsPOJO> benefitsList = new ArrayList<BenefitsPOJO>();
		pBenefits.stream().forEach(u -> {
			BenefitsPOJO dto =  new BenefitsPOJO();
			BeanUtils.copyProperties(u, dto);
			benefitsList.add(dto);
		});
		return benefitsList;
	}
	
	/**
	  * Toma las imágenes de los ejbs y los convierte en POJOs.
	  * 
	  * @param pImages Lista de ejb de imágenes. No debe ser nula.
	  * @return imageList Todas las entidades de tipo POJO.
	  */
	private List<PropertyImagePOJO> imagesDtos(List<TpropertyImage> pImages) {
		List<PropertyImagePOJO> imageList = new ArrayList<PropertyImagePOJO>();
		pImages.stream().forEach(u -> {
			PropertyImagePOJO dtoImage = new PropertyImagePOJO();
			BeanUtils.copyProperties(u, dtoImage);
			imageList.add(dtoImage);
		});
		return imageList;
	}
	
	/**
	  * Toma los comentarios de los ejbs y los convierte en POJOs.
	  * 
	  * @param pComments Lista de ejb de comentarios. No debe ser nula.
	  * @return commentsList Todas las entidades de tipo POJO.
	  */
	private List<CommentsPOJO> commentsDtos(List<TpropertyComment> pComments) {
		List<CommentsPOJO> commentsList = new ArrayList<CommentsPOJO>();
		pComments.stream().forEach(u -> {
			
		});
		return commentsList;
	}
	
	/**
	  * Toma las calificaciones de los ejbs y los convierte en POJOs.
	  * 
	  * @param pRatings Lista de ejb de calificaciones. No debe ser nula.
	  * @return ratingsList Todas las entidades de tipo POJO.
	  */
	private List<RatingPOJO> ratingDtos(List<TpropertyRating> pRatings) {
		List<RatingPOJO> ratingsList = new ArrayList<RatingPOJO>();
		pRatings.stream().forEach(u -> {
			
		});
		return ratingsList;
	}

	/**
	  * Alamacena la entidad. Retorna la entidad almacenada por si hay que realizar operaciones adicionales
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @param pProperty Contiene la infomarción a almacenar a la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	  * @return nProperty Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tproperty saveProperty(PropertiesRequest pProperty) {
		
		List<Tbenefit> lBenefits = new ArrayList<Tbenefit>();
		Tdistrict district = new Tdistrict();
		TpropertyType pType = new TpropertyType();
		Tproperty nProperty = new Tproperty();
		
		for (int i = 0; i < pProperty.getIdBenefits().size(); i++) {
			Tbenefit benefit = new Tbenefit();
			benefit.setIdBenefit(pProperty.getIdBenefits().get(i).intValue());
			lBenefits.add(benefit);
		}
		
		DistrictPOJO dist = pProperty.getProperty().getTdistrict();
		BeanUtils.copyProperties(dist, district);
		PropertyTypePOJO type = pProperty.getProperty().getTpropertyType();
		BeanUtils.copyProperties(type, pType);
		PropertyPOJO prop = pProperty.getProperty();
		BeanUtils.copyProperties(prop, nProperty);
		nProperty.setTdistrict(district);
		nProperty.setTpropertyType(pType);
		nProperty.setTbenefits(lBenefits);
		nProperty.setActive((byte)1);
		
		Tproperty resProperty =  propertiesRepository.save(nProperty);
		return resProperty;
	}

	/**
	  * Retorna a través del repositorio el ejb de la propiedad.
	  * 
	  * @param pIdProperty Id de la propiedad a buscar. No debe ser nulo.
	  * @return Tproperty Una entidad del tipo.
	  */
	@Override
	public Tproperty getPropertyById(int pIdProperty) {
		return propertiesRepository.findOne(pIdProperty);
	}

	/**
	  * Retorna a trav�s del repositorio el ejb de la propiedad.
	  * 
	  * @param pIdProperty Id de la propiedad a buscar. No debe ser nulo.
	  * @return PropertyPOJO Una entidad del tipo.
	  */
	@Override
	public PropertyPOJO getByPropertyId(int pIdProperty) {
		Tproperty  property = propertiesRepository.findByIdProperty(pIdProperty);
		PropertyPOJO nProperty = null;
		
		if(property != null) {
			nProperty = new PropertyPOJO();
			BeanUtils.copyProperties(property, nProperty);
			BeanUtils.copyProperties(property.getTdistrict(), nProperty.getTdistrict());
			BeanUtils.copyProperties(property.getTpropertyType(), nProperty.getTpropertyType());
		}
		
		return nProperty;
	}
	
	/**
	  * Retorna una lista de objetos PropertyPOJO con su lista de BenefitsPOJO
	  * 
	  * @return uiProperties Todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public ArrayList<PropertyPOJO> getPropertiesWithBenefits() {
		List<Tproperty> propertiesEjb = propertiesRepository.findAll();
		
		ArrayList<PropertyPOJO> uiProperties = new ArrayList<PropertyPOJO>();
		
		propertiesEjb.stream().forEach(property->{
			PropertyPOJO dto = new PropertyPOJO();
			dto.setIdProperty(property.getIdProperty());
			
			ArrayList<BenefitsPOJO> propertyBenefits = new ArrayList<BenefitsPOJO>();
			
			property.getTbenefits().stream().forEach(benefit->{
				BenefitsPOJO nbenefit = new BenefitsPOJO();
				BeanUtils.copyProperties(benefit, nbenefit);
				nbenefit.setTproperties(null);
				propertyBenefits.add(nbenefit);
			});
			dto.setTbenefits(propertyBenefits);
			
			uiProperties.add(dto);
		});
		
		return uiProperties;
	}
}

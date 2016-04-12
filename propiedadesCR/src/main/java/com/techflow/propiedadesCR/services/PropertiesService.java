package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.ejb.TpropertyReview;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;
import com.techflow.propiedadesCR.pojo.ReviewPropertyPOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.BenefitsRepository;
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
	 * Atributo de acceso al repositorio de los beneficios de las propiedades.
	 */
	@Autowired private BenefitsRepository benefitsRepository;
	
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
			UserPOJO user = new UserPOJO();
			BeanUtils.copyProperties(u.getTuser(),user);
			dto.setTuser(user);
			dto.setTpropertyImages(imagesDtos(u.getTpropertyImages()));
			dto.setTpropertyReviews(null);
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
	  * Toma las calificaciones de los ejbs y los convierte en POJOs.
	  * 
	  * @param pRatings Lista de ejb de calificaciones. No debe ser nula.
	  * @return ratingsList Todas las entidades de tipo POJO.
	  */
	private List<ReviewPropertyPOJO> reviewsDtos(List<TpropertyReview> pRatings) {
		List<ReviewPropertyPOJO> ratingsList = new ArrayList<ReviewPropertyPOJO>();
		pRatings.stream().forEach(u -> {
			ReviewPropertyPOJO dtoReview = new ReviewPropertyPOJO();
			UserPOJO user = new UserPOJO();
			BeanUtils.copyProperties(u, dtoReview);
			BeanUtils.copyProperties(u.getTuser(),user);
			dtoReview.setTuser(user);
			ratingsList.add(dtoReview);
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
		Tuser user = new Tuser();
		Tproperty nProperty = new Tproperty();
		if(pProperty.getIdBenefits() != null){
			for (int i = 0; i < pProperty.getIdBenefits().size(); i++) {
				Tbenefit benefit = new Tbenefit();
				benefit.setIdBenefit(pProperty.getIdBenefits().get(i).intValue());
				lBenefits.add(benefit);
			}
		}
		BeanUtils.copyProperties(pProperty.getProperty().getTdistrict(), district);
		BeanUtils.copyProperties(pProperty.getProperty().getTpropertyType(), pType);
		BeanUtils.copyProperties(pProperty.getProperty().getTuser(), user);
		BeanUtils.copyProperties(pProperty.getProperty(), nProperty);
		nProperty.setTdistrict(district);
		nProperty.setTpropertyType(pType);
		nProperty.setTuser(user);
		nProperty.setTbenefits(lBenefits);
		nProperty.setActive((byte)1);
		
		Tproperty resProperty =  propertiesRepository.save(nProperty);
		return resProperty;
	}
	
	/**
	 * Actualiza los datos de la propiedad deseada. Retorna la entidad almacenada por si hay que 
	 * realizar operaciones adicionales.
	 * 
	 * @param pProperty Contiene la infomarción a almacenar a la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	 * @param pIdProperty Id de la propiedad. No debe ser nulo.
	 * @return nProperty Una entidad del tipo.
	 */
	@Override
	@Transactional
	public Tproperty updateProperty(PropertiesRequest pProperty, 
			int pIdProperty) {
		List<Tbenefit> lBenefits = new ArrayList<Tbenefit>();
		Tdistrict district = new Tdistrict();
		TpropertyType pType = new TpropertyType();
		Tproperty nProperty = getPropertyById(pIdProperty);
		
		pProperty.getProperty().getTbenefits().stream().forEach(u -> {
			Tbenefit benefit = benefitsRepository.findOne(u.getIdBenefit());
			lBenefits.add(benefit);
		});
		
		BeanUtils.copyProperties(pProperty.getProperty().getTdistrict(), district);
		BeanUtils.copyProperties(pProperty.getProperty().getTpropertyType(), pType);
		nProperty.setTdistrict(district);
		nProperty.setTpropertyType(pType);
		nProperty.setTbenefits(lBenefits);
		nProperty.setAddress(pProperty.getProperty().getAddress());
		nProperty.setPrice(pProperty.getProperty().getPrice());
		nProperty.setSquareMeters(pProperty.getProperty().getSquareMeters());
		nProperty.setCoordinates(pProperty.getProperty().getCoordinates());
		
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
	  * Retorna a través del repositorio el ejb de la propiedad.
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
			nProperty.setTpropertyReviews(reviewsDtos(property.getTpropertyReviews()));
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
	
	/**
	  * Método encargado de poner una propiedad en oferta.
	  * @author Valeria Ramírez Cordero
	  * @param ppropertyRequest Objeto que contiene el porcentaje de la oferta.
	  * @return newProperty Devuelve el objeto modificado con la oferta de la propiedad.
	  */
	public Tproperty setPropertyOnSale(PropertiesRequest ppropertyRequest){
		Tproperty property = propertiesRepository.findByIdProperty(ppropertyRequest.getProperty().getIdProperty());
		property.setOfferPercentage(ppropertyRequest.getProperty().getOfferPercentage());
		Tproperty newProperty = propertiesRepository.save(property);
		return newProperty;
	}
	/*
	 * Actualiza el estado de la propiedad de activo a inactivo para
	 * propósitos de reportes de las propiedades.
	 * 
	 */
	@Override
	@Transactional
	public void deleteProperty(Tproperty pProperty) {
		propertiesRepository.save(pProperty);

	}
	/*
	  * Este metodo le suma una vista a la propiedad.
	  * 
	  * @param pidProperty Identificador de la propiedad que se esta viendo.
	  * @param  request Contiene la infomarción a almacenar.
	  * 
	  * @return response Retorna la propiedad que se esta modificando.
	  */
	@Override
	public PropertyPOJO propertyViews(PropertiesRequest request) {
		Tproperty property = new Tproperty();
		property = getPropertyById(request.getProperty().getIdProperty());
		PropertyPOJO newProperty = new PropertyPOJO();
		newProperty.setTdistrict(new DistrictPOJO());
		newProperty.setTpropertyType(new PropertyTypePOJO());
		property.setTotalViews(property.getTotalViews()+1);
		BeanUtils.copyProperties(property, newProperty);
		BeanUtils.copyProperties(property.getTdistrict(),newProperty.getTdistrict());
		BeanUtils.copyProperties(property.getTpropertyType(),newProperty.getTpropertyType());
		
		newProperty.setTbenefits(benefitsDtos(property.getTbenefits()));
		newProperty.setTuser(request.getProperty().getTuser());
		newProperty.setTpropertyImages(imagesDtos(property.getTpropertyImages()));
		newProperty.setTpropertyType(new PropertyTypePOJO());
		BeanUtils.copyProperties(property.getTpropertyType(),newProperty.getTpropertyType());
		
		newProperty.setTusers(null);
		newProperty.setTpropertyReviews(null);
		request.setProperty(newProperty);
		updateProperty(request,newProperty.getIdProperty());
		return newProperty;
	}

	/**
	 * Retorna la propiedad que se vendio.
	 * @param pProperty Contiene la informacion a almacenar en la base de datos.
	 * @return propertySold retorna el objeto propiedad que se vendio. 
	 * @author María Jesús Gutiérrez Calvo.
	 */
	@Override
	
	public Tproperty setPropertySold(PropertiesRequest pProperty){
		Tproperty property = propertiesRepository.findByIdProperty(pProperty.getProperty().getIdProperty());
		property.setIsSold((byte) 1);
		property.setSoldDate(new Date());
		Tproperty propertySold= propertiesRepository.save(property);
		return propertySold;
	}
	
	/**
	 * Retorna la propiedad que se vendio.
	 * @param pProperty Contiene la informacion del vendedor.
	 * @return propertySold retorna la lista de objetos property. 
	 * @author Jimmi Vila
	 */
	@Override
	public List<PropertyPOJO> getPropertiesByIdVendor(PropertiesRequest pPropertiesRequest) {
		ArrayList<PropertyPOJO> vendorProperties = new ArrayList<PropertyPOJO>();
		
		List<Tproperty> pProperties= propertiesRepository.findAll();
		
		List<PropertyPOJO> uiProperties = new ArrayList<PropertyPOJO>();
		pProperties.stream().forEach(u -> {
			PropertyPOJO dto = new PropertyPOJO();
			BeanUtils.copyProperties(u, dto);
			BeanUtils.copyProperties(u.getTdistrict(), dto.getTdistrict());
			BeanUtils.copyProperties(u.getTpropertyType(), dto.getTpropertyType());
			dto.setTbenefits(benefitsDtos(u.getTbenefits()));
			dto.getTuser().setIdUser(u.getTuser().getIdUser());
			dto.setSoldDate(u.getSoldDate());
			dto.setTpropertyImages(imagesDtos(u.getTpropertyImages()));
			dto.setTpropertyReviews(null);
			dto.setTusers(null);
			uiProperties.add(dto);
		});
		
		int idVendor = pPropertiesRequest.getProperty().getTuser().getIdUser();
		
		uiProperties.stream().forEach(property->{
			if(property.getTuser().getIdUser() == idVendor){
				property.setTbenefits(null);
				property.setTpropertyType(null);
				property.getTdistrict().setTcounty(null);
				property.setTuser(null);
				vendorProperties.add(property);
			}
		});
		
		return vendorProperties;
	}
}

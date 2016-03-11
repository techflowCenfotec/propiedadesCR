package com.techflow.propiedadesCR.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.services.BenefitsServiceInterface;
import com.techflow.propiedadesCR.services.DistrictServiceInterface;
import com.techflow.propiedadesCR.services.PropertiesServiceInterface;
import com.techflow.propiedadesCR.services.PropertyImagesServiceInterface;
import com.techflow.propiedadesCR.services.PropertyTypeServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

/**
* <h1>Controlador de las propiedades</h1>
* Controlador que envía o solicita información a través del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value="rest/protected/properties")
public class PropertiesController {

	/**
	 * Atributo que brinda un ServletContext
	 */
	@Autowired private ServletContext servletContext;
	/**
	 * Atributo de la interfaz de los distritos.
	 */
	@Autowired private DistrictServiceInterface districtService;
	/**
	 * Atributo de la interfaz de los tipos de propiedades.
	 */
	@Autowired private PropertyTypeServiceInterface propertyTypeService;
	/**
	 * Atributo de la interfaz de las propiedades.
	 */
	@Autowired private PropertiesServiceInterface propertiesService;
	/**
	 * Atributo de la interfaz de los beneficios.
	 */
	@Autowired private BenefitsServiceInterface benefitsService;
	/**
	 * Atributo de la interfaz de las imágenes de las propiedades.
	 */
	@Autowired private PropertyImagesServiceInterface imagesService;
	
	/**
	 * Solicita la información de las propiedades a través del servicio.
	 *  
	 * @return response Un objeto response de la propiedad.
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public PropertiesResponse getAll() {
		PropertiesResponse response = new PropertiesResponse();
		response.setCode(200);
		response.setCodeMessage("Properties fetch successful");
		response.setProperties(propertiesService.getAll());
		
		return response;
	}
	
	/**
	  * Envía los datos a almacenar a la base de datos por medio de su servicio. 
	  * 
	  * @param pSquareMeters Tamaño de la propiedad.
	  * @param pPrice Precio de la propiedad en dólares.
	  * @param pIdDistrict Id del distrito al que pertenece.
	  * @param pBenefits Lista de beneficios de la propiedad.
	  * @param pIdPropertyType Tipo de la propiedad.
	  * @param pAddress Dirección exacta de la propiedad.
	  * @param pPropertyImages Imágenes de la propiedad.
	  * @return response La entidad del objeto creado.
	  */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public PropertiesResponse create(
			@RequestParam("square_meters") double pSquareMeters,
			@RequestParam("price") double pPrice,
			@RequestParam("idDistrict") int pIdDistrict,
			@RequestParam("benefits") String pBenefits,
			@RequestParam("id_property_type") int pIdPropertyType,
			@RequestParam("address") String pAddress,
			@RequestParam("file") MultipartFile pPropertyImages) {
		PropertiesResponse response = new PropertiesResponse();
		
		String resultFileName = Utils.writeToFile(pPropertyImages, servletContext);
		
		if (!resultFileName.equals("")) {
			
			int[] ben = Arrays.stream(pBenefits.substring(1, pBenefits.length() -1)
					.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
			
			List<TpropertyImage> images = new ArrayList<TpropertyImage>();
			TpropertyImage image =  new TpropertyImage();
			image.setPropertyImage(resultFileName);
			images.add(image);
			
			Tproperty property = new Tproperty();
			property.setSquareMeters(pSquareMeters);
			property.setPrice(pPrice);
			property.setTdistrict(districtService.getDistrictById(pIdDistrict));
			property.setTbenefits(benefitsService.getBenefits(ben));
			property.setActive((byte)1);
			property.setTpropertyType(propertyTypeService.getPropertyTypeById(pIdPropertyType));
			property.setAddress(pAddress);
			property.setTpropertyImages(images);
			
			
			Tproperty nProperty = propertiesService.saveProperty(property);
			
			if(nProperty!=null){
				response.setCode(200);
				response.setCodeMessage("Property created succesfully!");
			}
		} else {
			response.setCode(400);
			response.setCodeMessage("Could not register property");
		}
		return response;
	}
	
	/**
	 * Solicita la información de la propiedad a través del servicio.
	 *  
	 *  @param pIdProprty Id de la propiedad. No debe ser nulo.
	 * @return response Un objeto response de la propiedad.
	 */
	@RequestMapping(value="getByPropertyId/{pIdProperty}", method=RequestMethod.GET)
	public PropertiesResponse getRequestedProperty(@PathVariable int pIdProperty) {
		PropertiesResponse response = new PropertiesResponse();
		
		PropertyPOJO property = propertiesService.getPropertyById(pIdProperty);
		
		response.setCode(200);
		response.setCodeMessage("Property fetch succesful");
		response.setProperty(property);
		
		return response;
	}
}

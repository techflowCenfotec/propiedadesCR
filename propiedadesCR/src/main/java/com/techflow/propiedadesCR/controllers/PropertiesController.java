package com.techflow.propiedadesCR.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.contracts.PropertyImageResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.services.PropertiesServiceInterface;
import com.techflow.propiedadesCR.services.PropertyImagesServiceInterface;
import com.techflow.propiedadesCR.services.UsersServiceInterface;
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
	 * Atributo de la interfaz de las propiedades.
	 */
	@Autowired private PropertiesServiceInterface propertiesService;
	/**
	 * Atributo de la interfaz de las imágenes de las propiedades.
	 */
	@Autowired private PropertyImagesServiceInterface imagesService;
	/**
	 * Atributo de la interfaz de los usuarios.
	 */
	@Autowired private UsersServiceInterface usersService;
	
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
	  * Envía la información a almacenar a la base de datos por medio de su servicio. 
	  * 
	  * @param pPropRequest Petición que contiene la información de la entidad que
	  * se desea crear.
	  * @return response La entidad del objeto creado.
	  */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public PropertiesResponse create(@RequestBody PropertiesRequest pPropRequest) {
		
		PropertiesResponse response = new PropertiesResponse();
		PropertyPOJO property = new PropertyPOJO();
		
		Tproperty nProperty = propertiesService.saveProperty(pPropRequest);

		property.setIdProperty(nProperty.getIdProperty());
		
		if(nProperty!=null){
			response.setCode(200);
			response.setCodeMessage("Property created succesfully!");
			response.setProperty(property);
		}
		
		return response;
	}
	
	/**
	  * Envía las imágenes a almacenar a la base de datos por medio de su servicio. 
	  * 
	  * @param pPropertyImages Imágenes de la propiedad.
	  * @return response La entidad del objeto creado.
	  */
	@RequestMapping(value="/createImage", method = RequestMethod.POST)
	public PropertyImageResponse createImage(
			@RequestParam("userId") int pIdProperty,
			@RequestParam("file") MultipartFile pPropertyImage) {
		PropertyImageResponse response = new PropertyImageResponse();
		
		String resultFileName = Utils.writeToFile(pPropertyImage, servletContext);
		
		Tproperty property =  new Tproperty();
		property = propertiesService.getPropertyById(pIdProperty);
		
		if (!resultFileName.equals("")) {
			TpropertyImage propImage = new TpropertyImage();
			propImage.setPropertyImage(resultFileName);
			propImage.setTproperty(property);
			
			TpropertyImage nPropImage = imagesService.savePropertyImg(propImage);
			
			if (nPropImage!=null) {
				response.setCode(200);
				response.setCodeMessage("Image inserted");
			}
		}
		return response;
	}
	
	/**
	 * Solicita la información de la propiedad a través del servicio.
	 *  
	 *  @param pIdProperty Id de la propiedad. No debe ser nulo.
	 * @return response Un objeto response de la propiedad.
	 */
	@RequestMapping(value="getByPropertyId/{pIdProperty}", method=RequestMethod.GET)
	public PropertiesResponse getRequestedProperty(@PathVariable int pIdProperty) {
		PropertiesResponse response = new PropertiesResponse();
		
		PropertyPOJO property = propertiesService.getByPropertyId(pIdProperty);
		
		response.setProperty(property);
		
		return response;
	}
	
	/**
	 * Solicita la información de las propiedades a través del servicio.
	 *  
	 * @return propertiesWithBenefits Una lista con las propiedades y sus beneficios.
	 */
	public List<PropertyPOJO> getPropertiesWithBenefits() {
		ArrayList<PropertyPOJO> propertiesWithBenefits = propertiesService.getPropertiesWithBenefits();
		return propertiesWithBenefits;
	}
}

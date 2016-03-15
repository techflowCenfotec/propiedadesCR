package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;
import com.techflow.propiedadesCR.repositories.PropertyTypeRepository;

/**
* <h1>Servicio para los tipos de propiedad</h1>
* Servicio que provee la implementacion de los m̩todos
* getAll() y getPropertyTypeById().
*
* @author  Walter Gomez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class PropertyTypeService implements PropertyTypeServiceInterface {

	/**
	 * Atributo de acceso al repositorio de los tipos de propiedad.
	 */
	@Autowired private PropertyTypeRepository pTypeRepository;
	
	/**
	  * Retorna una lista de objetos PropertyTypePOJO
	  * 
	  * @return uiPTypes Todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<PropertyTypePOJO> getAll() {
		List<TpropertyType> pTypes =  pTypeRepository.findAll();
		return generatePTypeDtos(pTypes);
	}

	/**
	  * Toma las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pTypes Lista de ejb de tipos de propiedad. No debe ser nula.
	  * @return uiPTypes Todas las entidades de tipo POJO.
	  */
	private List<PropertyTypePOJO> generatePTypeDtos(List<TpropertyType> pTypes) {
		List<PropertyTypePOJO> uiPTypes = new ArrayList<PropertyTypePOJO>();
		pTypes.stream().forEach(u -> {
			PropertyTypePOJO dto = new PropertyTypePOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTproperties(null);
			uiPTypes.add(dto);
		});
		
		return uiPTypes;
	}
	
	/**
	  * Retorna a trav̩s del repositorio el ejb del tipo
	  * de propiedad.
	  * 
	  * @param pIdPropertyType Id de la propiedad a buscar. No debe ser nulo.
	  * @return TpropertyType Una entidad del tipo.
	  */
	@Override
	public TpropertyType getPropertyTypeById(int pIdPropertyType) {
		return pTypeRepository.findOne(pIdPropertyType);
	}
}

package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.pojo.CountyPOJO;
import com.techflow.propiedadesCR.repositories.CountiesRepository;

/**
* <h1>Servicio para obtener los cantones</h1>
* Servicio que provee la implementacion de los metodos 
* getAll() y getCountyById().
*
* @author  Walter G—mez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class CountiesService implements CountiesServiceInterface {

	/**
	 * Atributo de acceso al repositorio de los cantones.
	 */
	@Autowired private CountiesRepository countiesRepository;
	
	/**
	  * Retorna una lista de objetos CountyPOJO
	  * 
	  * @return uiCounties Todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<CountyPOJO> getAll() {
		List<Tcounty> counties = countiesRepository.findAll();
		return generateCountyDtos(counties);
	}
	
	/**
	  * Toma las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pCounties Lista de ejb de cantones. No debe ser nula.
	  * @return uiCounties Todas las entidades de tipo POJO.
	  */
	private List<CountyPOJO> generateCountyDtos(List<Tcounty> pCounties) {
		List<CountyPOJO> uiCounties = new ArrayList<CountyPOJO>();
		pCounties.stream().forEach(u -> {
			CountyPOJO dto = new CountyPOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTdistricts(null);
			dto.setTprovince(null);
			uiCounties.add(dto);
		});
		return uiCounties;
	}

	/**
	  * Retorna a traves del repositorio el ejb del canton.
	  * 
	  * @param pIdCounty Id del canton a buscar. No debe ser nulo.
	  * @return Tcounty Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tcounty getCountyById(int pIdCounty) {
		return countiesRepository.findOne(pIdCounty);
	}

}

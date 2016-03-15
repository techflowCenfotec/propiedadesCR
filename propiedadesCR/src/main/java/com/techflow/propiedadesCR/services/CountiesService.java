package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.pojo.CountyPOJO;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.pojo.RatingPOJO;
import com.techflow.propiedadesCR.repositories.CountiesRepository;

/**
* <h1>Servicio para obtener los cantones</h1>
* Servicio que provee la implementación de los métodos 
* getAll() y getCountyById().
*
* @author  Walter Gómez
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
			BeanUtils.copyProperties(u.getTprovince(), dto.getTprovince());
			dto.setTdistricts(districtDtos(u.getTdistricts()));
			uiCounties.add(dto);
		});
		return uiCounties;
	}
	
	/**
	  * Toma los distritos de los ejbs y los convierte en POJOs.
	  * 
	  * @param pDistricts Lista de ejb de distritos. No debe ser nula.
	  * @return districtList Todas las entidades de tipo POJO.
	  */
	private List<DistrictPOJO> districtDtos(List<Tdistrict> pDistricts) {
		List<DistrictPOJO> districtList = new ArrayList<DistrictPOJO>();
		pDistricts.stream().forEach(u -> {
			DistrictPOJO dto = new DistrictPOJO();
			BeanUtils.copyProperties(u, dto);
			districtList.add(dto);
		});
		return districtList;
	}

	/**
	  * Retorna a través del repositorio el ejb del cantón.
	  * 
	  * @param pIdCounty Id del cantón a buscar. No debe ser nulo.
	  * @return Tcounty Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tcounty getCountyById(int pIdCounty) {
		return countiesRepository.findOne(pIdCounty);
	}

}

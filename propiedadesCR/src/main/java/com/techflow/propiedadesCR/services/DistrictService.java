package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.CountyPOJO;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;
import com.techflow.propiedadesCR.repositories.DistrictRepository;

/**
* <h1>Servicio del Distrito</h1>
* Servicio que provee una implementación a la interface
* de los métodos getAll() y getDistrictById().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class DistrictService implements DistrictServiceInterface {

	/**
	 * Atributo de acceso al repositorio de los distritos.
	 */
	@Autowired private DistrictRepository districtRepository;
	/**
	 * Atributo de la interfaz de los cantones.
	 */
	@Autowired private CountiesServiceInterface countyService;
	/**
	 * Atributo de la interfaz de las provincias.
	 */
	@Autowired private ProvinceServiceInterface provinceService;
	
	/**
	  * Retorna una lista de objetos DistrictPOJO
	  * 
	  * @return uiDistricts Todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<DistrictPOJO> getAll() {
		List<Tdistrict> districts = districtRepository.findAll();
		return generateDistrictDtos(districts);
	}
	
	/**
	  * Toma las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pDistricts Lista de ejb de distritos. No debe ser nula.
	  * @return uiDistricts Todas las entidades de tipo POJO.
	  */
	private List<DistrictPOJO> generateDistrictDtos(List<Tdistrict> pDistricts) {
		List<DistrictPOJO> uiDistricts = new ArrayList<DistrictPOJO>();
		pDistricts.stream().forEach(u -> {
			DistrictPOJO dto = new DistrictPOJO();
			BeanUtils.copyProperties(u, dto);
			BeanUtils.copyProperties(u.getTcounty(), dto.getTcounty());
			uiDistricts.add(dto);
		});
		return uiDistricts;
	}

	/**
	  * Retorna a trav̩s del repositorio el ejb del distrito.
	  * 
	  * @param pIdDistrict Id del distrito a buscar. No debe ser nulo.
	  * @return Tdistrict Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tdistrict getDistrictById(int pIdDistrict) {
		return districtRepository.findOne(pIdDistrict);
	}

}

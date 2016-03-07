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
	  * @return uiDistricts - todas las entidades del tipo.
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
	  * @param pDistricts - no debe ser nula.
	  * @return uiDistricts - todas las entidades de tipo POJO.
	  */
	private List<DistrictPOJO> generateDistrictDtos(List<Tdistrict> pDistricts) {
		List<DistrictPOJO> uiDistricts = new ArrayList<DistrictPOJO>();
		pDistricts.stream().forEach(u -> {
			DistrictPOJO dto = new DistrictPOJO();
			CountyPOJO nCounty = new CountyPOJO();
			ProvincePOJO nProvince = new ProvincePOJO();
			
			Tcounty county = countyService.getCountyById(u.getTcounty().getIdCounty());
			BeanUtils.copyProperties(county, nCounty);
			
			Tprovince province = provinceService.getProvinceById(county.getTprovince().getIdProvince());
			BeanUtils.copyProperties(province, nProvince);
			nProvince.setTproperties(null);
			nCounty.setTprovince(nProvince);
			
			BeanUtils.copyProperties(u, dto);
			dto.setTcounty(null);
			uiDistricts.add(dto);
		});
		return uiDistricts;
	}

	/**
	  * Retorna a través del repositorio el ejb del distrito.
	  * 
	  * @param pIdDistrict - no debe ser nulo.
	  * @return Tdistrict - una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tdistrict getDistrictById(int pIdDistrict) {
		return districtRepository.findOne(pIdDistrict);
	}

}

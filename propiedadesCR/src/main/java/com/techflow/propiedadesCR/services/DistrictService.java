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
	  * Retorna a través del repositorio el ejb del distrito.
	  * 
	  * @param pIdDistrict Id del distrito a buscar. No debe ser nulo.
	  * @return districtDto Una entidad del tipo.
	  */
	@Override
	@Transactional
	public DistrictPOJO getDistrictById(int pIdDistrict) {
		Tdistrict district = districtRepository.findOne(pIdDistrict);
		DistrictPOJO  districtDto = null;
		
		if (district != null) {
			districtDto = new DistrictPOJO();
			BeanUtils.copyProperties(district, districtDto);
			districtDto.setTcounty(countyDto(district.getTcounty()));
		}
		
		return districtDto;
	}
	
	/**
	  * Toma el ejb del cantón y los convierte en POJO.
	  * 
	  * @param pCounty Entidad de ejb del cantón. No debe ser nula.
	  * @return nCounty Una entidad de tipo POJO.
	  */
	private CountyPOJO countyDto(Tcounty pCounty) {
		CountyPOJO nCounty = new CountyPOJO();
		
		BeanUtils.copyProperties(pCounty, nCounty);
		
		return nCounty;
	}

}

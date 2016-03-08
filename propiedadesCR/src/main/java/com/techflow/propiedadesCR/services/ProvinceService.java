package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.CountyPOJO;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;
import com.techflow.propiedadesCR.repositories.ProvinceRepository;

/**
* <h1>Servicio de Provincias</h1>
* Servicio que provee una implementación a la interface
* de los métodos getAll() y getProvinceById().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class ProvinceService implements ProvinceServiceInterface {

	/**
	 * Atributo de acceso al repositorio de las provincias.
	 */
	@Autowired private ProvinceRepository provinceRepository;
	/**
	 * Atributo de la interfaz de los cantones.
	 */
	@Autowired private CountiesServiceInterface countyService;
	
	/**
	  * Retorna una lista de objetos ProvincePOJO
	  * 
	  * @return uiProvinces Todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<ProvincePOJO> getAll() {
		List<Tprovince> provinces = provinceRepository.findAll();
		return generateProvinceDtos(provinces);
	}
	
	/**
	  * Toma las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pProvinces Lista de ejb de provincias. No debe ser nula.
	  * @return uiProvinces Todas las entidades de tipo POJO.
	  */
	private List<ProvincePOJO> generateProvinceDtos(List<Tprovince> pProvinces) {
		List<ProvincePOJO> uiProvinces = new ArrayList<ProvincePOJO>();
		
		pProvinces.stream().forEach(u ->{
			ProvincePOJO dto = new ProvincePOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTcounties(null);
			dto.setTproperties(null);
			uiProvinces.add(dto);
		});
		return uiProvinces;
	}

	/**
	  * Retorna a través del repositorio el ejb de la provincia.
	  * 
	  * @param pIdProvince Id de la provincia a buscar. No debe ser nulo.
	  * @return Tprovince Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tprovince getProvinceById(int pIdProvince) {
		return provinceRepository.findOne(pIdProvince);
	}

}

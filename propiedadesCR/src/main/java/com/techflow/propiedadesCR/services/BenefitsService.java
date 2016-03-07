package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.BenefitsRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;
import com.techflow.propiedadesCR.repositories.BenefitsRepository;

/**
* <h1>Servicio para obtener los beneficios</h1>
* Servicio que provee la implementación de los métodos 
* getAll(), getBenefitById() y getBenefits().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class BenefitsService implements BenefitsServiceInterface {

	@Autowired private BenefitsRepository benefitsRepository;
	
	/**
	  * Retorna una lista de objetos BenefitsPOJO
	  * 
	  * @return todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<BenefitsPOJO> getAll() {
		List<Tbenefit> benefits = benefitsRepository.findAll();
		return generateBenefitsDtos(benefits);
	}

	/**
	  * Toma las propiedades de los ejbs y los convierte en POJOs.
	  * 
	  * @param pBenefits - no debe ser nula.
	  * @return todas las entidades de tipo POJO.
	  */
	private List<BenefitsPOJO> generateBenefitsDtos(List<Tbenefit> pBenefits) {
		List<BenefitsPOJO> uiBenefits = new ArrayList<BenefitsPOJO>();
		pBenefits.stream().forEach(u -> {
			BenefitsPOJO dto = new BenefitsPOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTproperties(null);
			uiBenefits.add(dto);
		});
		return uiBenefits;
	}

	/**
	  * Retorna a través del repositorio el ejb del beneficio.
	  * 
	  * @param pIdBenefit - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tbenefit getBenefitById(int pIdBenefit) {
		return benefitsRepository.findOne(pIdBenefit);
	}

	/**
	  * Retorna a través del repositorio una lista de 
	  * ejbs de beneficios.
	  * 
	  * @param pBenefits - no debe ser nula.
	  * @return todas las entidades del tipo.
	  */
	@Override
	@Transactional
	public List<Tbenefit> getBenefits(int[] pBenefits) {
		return benefitsRepository.findByIdBenefitIn(pBenefits);
	}
}

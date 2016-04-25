package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.GuidesRequest;
import com.techflow.propiedadesCR.contracts.GuidesResponse;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tbank;
import com.techflow.propiedadesCR.ejb.Tguide;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.BankPOJO;
import com.techflow.propiedadesCR.pojo.GuidePOJO;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.GuidesRepository;


@Service
public class GuidesService implements GuidesServiceInterface{

	/**
	 * Objeto que se comunica con la base de datos
	 */
	@Autowired private GuidesRepository guidesRepository;
	
	/**
	 * Método que se encarga de almacenar una guia en el sistema
	 * @param pguide Parámetro que contiene información del objeto a almacenar
	 * @return createdGuide Devuelve el objeto Tguide creado
	 */
	@Override
	@Transactional
	public Tguide saveGuide(GuidePOJO pguide) {
		Tguide newGuide = new Tguide();
		newGuide.setUrl(pguide.getUrl());
		newGuide.setName(pguide.getName());
		newGuide.setTbank(new Tbank());
		newGuide.getTbank().setIdBank(pguide.getTbank().getIdBank());
		Tguide createdGuide = guidesRepository.save(newGuide);
		
		return createdGuide;
	}
	/**
	 * Método que se encarga de retornar las guías de los bancos
	 * @param pguidesRequest Petición del usuario
	 * @return createdGuide Devuelve una lista de guías de un banco
	 */
	@Override
	@Transactional
	public GuidesResponse getAllByBank(GuidesRequest pguidesRequest) {
		GuidesResponse response = new GuidesResponse();
		Tbank bank = new Tbank();
		bank.setIdBank(pguidesRequest.getGuide().getTbank().getIdBank());
		Page<Tguide> guides = guidesRepository.findAllByTbank(bank,new PageRequest(pguidesRequest.getPageNumber(),pguidesRequest.getPageSize()));
		response.setGuides(generateGuidesDtos(guides.getContent()));
		response.setCode(200);
		return response;
	}
	/**
	 * Método que se encarga de retornar todas guías de los bancos
	 * @param pguidesRequest Petición del usuario
	 * @return createdGuide Devuelve una lista de guías
	 */
	private List<GuidePOJO> generateGuidesDtos(List<Tguide> pguides) {
		List<GuidePOJO> uiGuides = new ArrayList<GuidePOJO>();
		pguides.stream().forEach(u -> {
			Tbank bank =u.getTbank();			
			BankPOJO bankPOJO = new BankPOJO();
			GuidePOJO dto = new GuidePOJO();
			BeanUtils.copyProperties(bank, bankPOJO);
			BeanUtils.copyProperties(u, dto);
			dto.setTbank(bankPOJO);
			uiGuides.add(dto);
		});
		return uiGuides;
	}

}

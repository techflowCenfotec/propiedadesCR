package com.techflow.propiedadesCR.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tbank;
import com.techflow.propiedadesCR.ejb.Tguide;
import com.techflow.propiedadesCR.pojo.GuidePOJO;
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

}

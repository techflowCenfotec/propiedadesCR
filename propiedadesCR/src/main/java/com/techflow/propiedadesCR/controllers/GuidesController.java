package com.techflow.propiedadesCR.controllers;

import java.text.ParseException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.GuidesResponse;
import com.techflow.propiedadesCR.ejb.Tguide;
import com.techflow.propiedadesCR.pojo.BankPOJO;
import com.techflow.propiedadesCR.pojo.GuidePOJO;
import com.techflow.propiedadesCR.services.GuidesServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/protected/guides")
public class GuidesController {
	
	/**
	 * Objeto que ofrece los servicios de las guias.
	 */
	@Autowired private GuidesServiceInterface guidesService;
	
	/**
	 * Objeto usado para el manejo de archivos.
	 */
	@Autowired private ServletContext servletContext;

	/**
	 * 
	 * Este método envía los datos a la base de datos para registrar la guia.
	 * 
	 * @param pfile Se el pdf a registrar.
	 * @param pidBank Se recibe el identificador del banco al que pertenece la guia.
	 * @return response Retorna la respuesta del BackEnd al FrondEnd
	 * 
	 */
	
	@RequestMapping(value="/saveguide",method=RequestMethod.POST)
	public GuidesResponse saveGuide(
			@RequestParam ("file") MultipartFile pfile,
			@RequestParam ("idBank") int pidBank, 
			@RequestParam ("name") String pname){
		
		GuidesResponse response = new GuidesResponse();
		
		String acceptedExtension = ".pdf";
		String receivedExtension = pfile.getOriginalFilename().substring(pfile.getOriginalFilename().length()-4);
		String resultFileName = "";
		
		if(acceptedExtension.equals(receivedExtension)){
			resultFileName = Utils.writeToFile(pfile,servletContext,"guides");
		}
		
		GuidePOJO newGuide = new GuidePOJO();
		newGuide.setUrl(resultFileName);
		newGuide.setName(pname);
		newGuide.setTbank(new BankPOJO());
		newGuide.getTbank().setIdBank(pidBank);
		
		Tguide createdGuide = guidesService.saveGuide(newGuide);
		if(createdGuide!=null){
			response.setCode(200);
			response.setCodeMessage("created succesfully");
		}
		
		return response;
	}
}

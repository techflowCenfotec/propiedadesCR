package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.RatingRequest;
import com.techflow.propiedadesCR.contracts.RatingResponse;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.pojo.RatingPOJO;
import com.techflow.propiedadesCR.services.RatingServiceInterface;

@RestController
@RequestMapping(value="rest/protected/rating")
public class RatingController {

	@Autowired private RatingServiceInterface ratingService;
	
	/**
	  * Envía la información a almacenar a la base de datos por medio de su servicio. 
	  * 
	  * @param pRatings Petición que contiene la información de la entidad que
	  * se desea crear.
	  * @return response La entidad del objeto creado.
	  */
	@RequestMapping(value="/addRating", method = RequestMethod.POST)
	public RatingResponse addRating(@RequestBody RatingRequest pRating) {
		RatingResponse response =  new RatingResponse();
		RatingPOJO rating = new RatingPOJO();
		
		TpropertyRating nRating = ratingService.addRating(pRating);
		
		rating.setIdRating(nRating.getIdRating());
		
		if (nRating != null) {
			response.setCode(200);
			response.setCodeMessage("Property rated succesfully");
			response.setRating(rating);
		}
		
		return response;
	}
	
	@RequestMapping(value="/editRating", method = RequestMethod.POST)
	public RatingResponse editRating(@RequestBody RatingRequest pRating) {
		RatingResponse response =  new RatingResponse();
		RatingPOJO rating = new RatingPOJO();
		
		TpropertyRating nRating = ratingService.editRating(pRating);
		
		rating.setIdRating(nRating.getIdRating());
		
		if (nRating != null) {
			response.setCode(200);
			response.setCodeMessage("Propertie's rating edited succesfully");
			response.setRating(rating);
		}
		
		return response;
	}
	
	@RequestMapping(value="/getRatingById", method = RequestMethod.POST)
	public RatingResponse getRatingById(@RequestBody RatingRequest pRating) {
		RatingResponse ratingResponse = new RatingResponse();
		
		
		ratingResponse.setCode(200);
		ratingResponse.setCodeMessage("rating fetch success");
		ratingResponse.setRating(ratingService.getRatingById(pRating));
		return ratingResponse;
	}
}

package com.techflow.propiedadesCR.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.UserSurveyMatchResultResponse;
import com.techflow.propiedadesCR.contracts.UserSurveysRequest;
import com.techflow.propiedadesCR.contracts.UserSurveysResponse;
import com.techflow.propiedadesCR.ejb.TuserSurvey;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.pojo.UserSurveyPOJO;
import com.techflow.propiedadesCR.services.PropertiesServiceInterface;
import com.techflow.propiedadesCR.services.UserSurveysServiceInterface;

@RestController
@RequestMapping(value="rest/protected/usersurveys")
public class UserSurveysController {

	@Autowired private UserSurveysServiceInterface userSurveysService;
	
	@Autowired private PropertiesServiceInterface propertiesService;

	@RequestMapping(value="/create",method=RequestMethod.POST)
	public UserSurveysResponse createUserSurvey(@RequestBody UserSurveysRequest puserSurveysRequest){
		UserSurveysResponse response = new UserSurveysResponse();
		TuserSurvey userSurvey = userSurveysService.createUserSurvey(puserSurveysRequest);
		if(userSurvey!=null){
			response.setUserSurveys(new ArrayList<UserSurveyPOJO>());
			response.getUserSurveys().add(new UserSurveyPOJO());
			response.getUserSurveys().get(0).setIdSurvey(userSurvey.getIdSurvey());
			response.setCodeMessage("nice nice");
			response.setCode(200);
		}
		return response;
	}
	
	@RequestMapping(value="/generatematchbysurvey",method=RequestMethod.POST)
	public UserSurveyMatchResultResponse GenerateMatchBySurvey(@RequestBody UserSurveysRequest puserSurveyRequest){
		//response
		UserSurveyMatchResultResponse response = new UserSurveyMatchResultResponse();
		//lista de los porcentajes de match
		ArrayList<Double> porcentages = new ArrayList<Double>();
		//lista de propiedades con almenos un match
		ArrayList<Integer> idsProperties = new ArrayList<Integer>();
		ArrayList<PropertyPOJO> propertiesResult = new ArrayList<PropertyPOJO>();
		// lista de propiedades con beneficios
		List<PropertyPOJO> properties = propertiesService.getPropertiesWithBenefits();
		// lista con las respuestas del usuario
		UserSurveyPOJO userSurvey = getUserSurveyById(puserSurveyRequest.getUserSurvey().getIdSurvey());
		//calcular el porcentage del match
		int userTags = userSurvey.getTanswers().size();
		
		for (PropertyPOJO property : properties) {
			int matchedTags = 0;
			for (BenefitsPOJO benefit : property.getTbenefits()) {
				
				for(int i=0;i<userTags;i++){
					if(benefit.getBenefit().equals(userSurvey.getTanswers().get(i).getResult())){
						matchedTags++;
					}
				}
			}
			
			if(matchedTags>0){
				double matchPorcentage = getMatchPorcentage(userTags,matchedTags);
				//propertiesResult.add(property);
				idsProperties.add(property.getIdProperty());
				porcentages.add(matchPorcentage);
			}
		}
		
		//levantar atributos de las propiedades con match para listarlos!
		List<PropertyPOJO> allProperties = propertiesService.getAll();
		
		allProperties.stream().forEach(property->{
			for (int i = 0; i < idsProperties.size(); i++) {
				if(property.getIdProperty()==idsProperties.get(i)){
					propertiesResult.add(property);
				}
			}	
		});
		
		response.setProperties(propertiesResult);
		response.setPorcentages(porcentages);
		
		return response;
	}
	
	private double getMatchPorcentage(int userTags, int matchedTags) {
		return matchedTags*100/userTags;
	}

	public UserSurveyPOJO getUserSurveyById(int idUserSurvey){
		return userSurveysService.getUserSurveyById(idUserSurvey);
	}

}

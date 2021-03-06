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
/**
* <h1>Controlador de los cuestionarios de los usuarios</h1>
* Esta clase es la encargada de recibir los pedidos del front-end
* y manejar todo lo relacionado a los cuestionarios de los usuarios
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/

@RestController
@RequestMapping(value="rest/protected/usersurveys")
public class UserSurveysController {

	/**
     * Objeto que ofrece los servicios de los cuestionarios de los usuarios
     */
	@Autowired private UserSurveysServiceInterface userSurveysService;
	
	/**
     * Objeto que ofrece los servicios de las propiedades
     */
	@Autowired private PropertiesServiceInterface propertiesService;

	/**
	  * Este metodo sirve para almacenar un cuestionario y sus respuestas para un usuario del sistema
	  * @param puserSurveysRequest Este parametro es la peticion del front-end que contiene el objeto
	  * que se usa para acceder al metodo deseado y completar la funcion
	  * @return nuserSurvey Cuestionario creado
	  */
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
	
	/**
	  * Este metodo sirve para generar la lista de propiedades recomendadas y la lista de porcentajes para un usuario
	  * apartir de un cuestionario
	  * @param puserSurveyRequest Este parametro es la peticion del front-end que contiene el objeto
	  * que se usa para acceder al metodo deseado y completar la funcion
	  * @return response Lista con las propiedades y porcentajes de match
	  */
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
				idsProperties.add(property.getIdProperty());
				porcentages.add(matchPorcentage);
			}
		}
		
		//levantar atributos de las propiedades con match para listarlos!
		List<PropertyPOJO> allProperties = propertiesService.getAll();
		
		allProperties.stream().forEach(property->{
			property.setTbenefits(null);
			property.setTpropertyType(null);
			property.setTdistrict(null);
			property.setTuser(null);
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
	
	/**
	  * Este metodo sirve para generar el porcentaje de match por cada propiedad
	  * @param puserTags Cantidad de tags por usuario
	  * @param pmatchedTags Cantidad de tags que hicieron match con la propiedad
	  * @return result Porcentage de match
	  */
	private double getMatchPorcentage(int puserTags, int pmatchedTags) {
		double result = pmatchedTags*100/puserTags;
		return result;
	}
	
	/**
	  * Este metodo sirve para obtener un cuestionario y sus respuestas para un usuario del sistema
	  * @param idUserSurvey Este parametro es el id del cuestionario a consultar
	  * @return surveyWithAnswers Cuestionario consultado
	  */
	public UserSurveyPOJO getUserSurveyById(int idUserSurvey){
		return userSurveysService.getUserSurveyById(idUserSurvey);
	}

}

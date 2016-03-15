package com.techflow.propiedadesCR.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.UserSurveysRequest;
import com.techflow.propiedadesCR.ejb.Tanswer;
import com.techflow.propiedadesCR.ejb.Tquestion;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.ejb.TuserSurvey;
import com.techflow.propiedadesCR.pojo.AnswerPOJO;
import com.techflow.propiedadesCR.pojo.UserSurveyPOJO;
import com.techflow.propiedadesCR.repositories.AnswersRepository;
import com.techflow.propiedadesCR.repositories.UserSurveysRepository;
/**
* <h1>Servicio de los cuestionarios de los usuarios</h1>
* Esta clase es la encargada de ofrecer los servicios
* y administrar las transacciones al repositorio
*
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/

@Service
public class UserSurveysService implements UserSurveysServiceInterface{
	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private UserSurveysRepository userSurveysRepository;
	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private AnswersRepository answersRepository;

	/**
	  * Este metodo sirve para almacenar un cuestionario y sus respuestas para un usuario del sistema
	  * @param puserSurveysRequest Este parametro es la peticion del front-end que contiene el objeto
	  * que se usa para acceder al metodo deseado y completar la funcion
	  * @return nuserSurvey Cuestionario creado
	  */
	@Override
	@Transactional
	public TuserSurvey createUserSurvey(UserSurveysRequest puserSurveysRequest) {
		
		TuserSurvey userSurvey = new TuserSurvey();
		UserSurveyPOJO userSurveyPOJO = puserSurveysRequest.getUserSurvey();
		
		BeanUtils.copyProperties(userSurveyPOJO, userSurvey);
		userSurvey.setTuser(new Tuser());
		//BeanUtils.copyProperties(userSurveyPOJO.getTuser(), userSurvey.getTuser());
		userSurvey.getTuser().setIdUser(userSurveyPOJO.getTuser().getIdUser());
		
		TuserSurvey nuserSurvey = userSurveysRepository.save(userSurvey);
		
		//ArrayList<Tanswer> answersList = new ArrayList<Tanswer>();
		userSurveyPOJO.getTanswers().stream().forEach(answer->{
			Tanswer nanswer = new Tanswer();
			BeanUtils.copyProperties(answer, nanswer);
			nanswer.setTquestion(new Tquestion());
			BeanUtils.copyProperties(answer.getTquestion(), nanswer.getTquestion());
			nanswer.setTuserSurvey(userSurvey);
			answersRepository.save(nanswer);
			//answersList.add(nanswer);
		});
		
		//userSurvey.setTanswers(answersList);
		
		return nuserSurvey;
	}
	
	/**
	  * Este metodo sirve para obtener un cuestionario y sus respuestas para un usuario del sistema
	  * @param pidUserSurvey Este parametro es la peticion del front-end que contiene el objeto
	  * que se usa para acceder al metodo deseado y completar la funcion
	  * @return surveyWithAnswers Cuestionario con sus respuestas
	  */
	@Override
	public UserSurveyPOJO getUserSurveyById(int pidUserSurvey) {
		TuserSurvey userSurvey = userSurveysRepository.findOne(pidUserSurvey);
		UserSurveyPOJO surveyWithAnswers = new UserSurveyPOJO();
		
		BeanUtils.copyProperties(userSurvey, surveyWithAnswers);
		
		ArrayList<AnswerPOJO> surveyAnswers = new ArrayList<AnswerPOJO>();
		userSurvey.getTanswers().stream().forEach(answer->{
			AnswerPOJO nanswer = new AnswerPOJO();
			BeanUtils.copyProperties(answer, nanswer);
			surveyAnswers.add(nanswer);
		});
		surveyWithAnswers.setTanswers(surveyAnswers);
		return surveyWithAnswers;
	}

}

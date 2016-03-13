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
import com.techflow.propiedadesCR.pojo.UserSurveyPOJO;
import com.techflow.propiedadesCR.repositories.AnswersRepository;
import com.techflow.propiedadesCR.repositories.UserSurveysRepository;

@Service
public class UserSurveysService implements UserSurveysServiceInterface{
	
	@Autowired private UserSurveysRepository userSurveysRepository;
	@Autowired private AnswersRepository answersRepository;

	@Override
	@Transactional
	public TuserSurvey createUserSurvey(UserSurveysRequest puserSurveysRequest) {
		
		TuserSurvey userSurvey = new TuserSurvey();
		UserSurveyPOJO userSurveyPOJO = puserSurveysRequest.getUserSurvey();
		
		BeanUtils.copyProperties(userSurveyPOJO, userSurvey);
		userSurvey.setTuser(new Tuser());
		//BeanUtils.copyProperties(userSurveyPOJO.getTuser(), userSurvey.getTuser());
		userSurvey.getTuser().setIdUser(userSurveyPOJO.getTuser().getIdUsuario());
		
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

}

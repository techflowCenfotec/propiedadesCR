package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tquestion;

import com.techflow.propiedadesCR.pojo.OptionPOJO;
import com.techflow.propiedadesCR.pojo.QuestionPOJO;
import com.techflow.propiedadesCR.repositories.QuestionsRepository;

@Service
public class QuestionsService implements QuestionsServiceInterface{
	
	@Autowired private QuestionsRepository questionsRepository;

	@Override
	@Transactional
	public List<QuestionPOJO> getQuestionsWithOptions() {
		List<Tquestion> questions = questionsRepository.findAll();
		return generateQuestionsDtos(questions);
	}

	private List<QuestionPOJO> generateQuestionsDtos(List<Tquestion> pquestions) {
		List<QuestionPOJO> uiquestions = new ArrayList<QuestionPOJO>();
		pquestions.stream().forEach(u -> {
			QuestionPOJO dto = new QuestionPOJO();
			BeanUtils.copyProperties(u, dto);
			
			List<OptionPOJO> uioptions = new ArrayList<OptionPOJO>();
			u.getToptions2().stream().forEach(opt->{
				OptionPOJO dtoOption = new OptionPOJO();
				BeanUtils.copyProperties(opt, dtoOption);
				if(opt.getTquestion1() != null){
					dtoOption.setIdNextQuestion(opt.getTquestion1().getIdQuestion());
				}
				uioptions.add(dtoOption);
				
			});
			dto.setToptions2(uioptions);
			
			
			uiquestions.add(dto);
		});
		return uiquestions;
	}

}

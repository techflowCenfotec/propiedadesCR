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
/**
* <h1>Servicio de las preguntas</h1>
* Esta clase es la encargada de ofrecer los servicios
* y administrar las transacciones al repositorio
*
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
@Service
public class QuestionsService implements QuestionsServiceInterface{
	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private QuestionsRepository questionsRepository;
	
	/**
	  * Este metodo sirve para obtener una lista de preguntas y sus opciones
	  * @return uiquestions Lista de preguntas con opciones
	  */
	@Override
	@Transactional
	public List<QuestionPOJO> getQuestionsWithOptions() {
		List<Tquestion> questions = questionsRepository.findAll();
		return generateQuestionsDtos(questions);
	}
	
	/**
	  * Este metodo sirve para copiar los atributos de los objetos levantados a los POJOs
	  * @param pquestions Este parametro es la lista preguntas levantadas del repositorio
	  * @return uiquestions Lista de preguntas con opciones
	  */
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

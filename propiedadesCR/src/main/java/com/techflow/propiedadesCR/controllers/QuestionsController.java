package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.QuestionsRequest;
import com.techflow.propiedadesCR.contracts.QuestionsResponse;
import com.techflow.propiedadesCR.services.QuestionsServiceInterface;
/**
* <h1>Controlador de las preguntas</h1>
* Controlador que envía o solicita información a través del servicio.
*
* @author  Jimmi Vila
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value = "rest/protected/questions")
public class QuestionsController {
	/**
     * Objeto que ofrece los servicios de las preguntas
     */
	@Autowired private QuestionsServiceInterface questionsService;
	/**
	  * Este metodo sirve para cargar todas las opciones del sistema
	  * @param pquestionRequest Este parametro es la peticion del front-end
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de preguntas del sistema
	  */
	@RequestMapping(value="/getquestionswithoptions", method= RequestMethod.POST)
	public QuestionsResponse getQuestionsWithOptions(@RequestBody QuestionsRequest pquestionRequest){
		QuestionsResponse response = new QuestionsResponse();
		response.setQuestions(questionsService.getQuestionsWithOptions());
		return response;
	}

}

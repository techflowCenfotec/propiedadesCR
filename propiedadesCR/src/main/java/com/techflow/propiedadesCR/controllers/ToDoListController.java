package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.contracts.ToDoListResponse;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.services.ToDoListServiceInterface;

/**
* <h1>Controlador del to-do list</h1>
* Esta clase es la encargada de recibir los pedidos del front-end
* y manejar todo lo relacionado a los to-do list de los usuarios
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/

@RestController
@RequestMapping(value= "rest/protected/todolist")
public class ToDoListController {
	
	/**
     * Objeto que ofrece los servicios de los to-do list de los usuarios
     */
	@Autowired private ToDoListServiceInterface toDoListService;
	
	  /**
	  * Este metodo sirve para cargar todos los to-do list del sistema
	  * @param ptoDoListRequest Este parametro es la peticion del usuario
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de to-do list del sistema
	  */
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public ToDoListResponse getAll(@RequestBody ToDoListRequest ptoDoListRequest){
		ToDoListResponse response = new ToDoListResponse();
		response.setCode(200);
		response.setCodeMessage("ToDoList fetch successful");
		response.setToDoList(toDoListService.getAll(ptoDoListRequest));
		return response;
	}
	
	/**
	  * Este metodo sirve para crear un todo-list nuevo para un usuario especifico
	  * @param ptoDoListRequest Este parametro es la peticion del usuario
	  * que se usa para obtener el objeto nuevo que se va ingresar al sistema
	  * @return response Resultado de la peticion
	  */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ToDoListResponse createToDoList(@RequestBody ToDoListRequest ptoDoListRequest){
		ToDoListResponse response = new ToDoListResponse();
		boolean idUser = false;
		
		if(ptoDoListRequest.getToDoList().getTuser()!=null){
			idUser=true;
		}
		
		if(idUser){
			TToDoList toDoList = toDoListService.saveToDoList(ptoDoListRequest);
			if(toDoList != null){
				response.setCode(200);
				response.setCodeMessage("to-do list created successfully");
			}
		}
		return response;
	}

}

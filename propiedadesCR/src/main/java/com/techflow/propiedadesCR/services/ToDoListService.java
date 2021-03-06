package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.ToDoListPOJO;
import com.techflow.propiedadesCR.repositories.ToDoListRepository;
/**
* <h1>Servicio de los to-do list de los usuarios</h1>
* Esta clase es la encargada de ofrecer los servicios
* y administrar las transacciones al repositorio
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/

@Service
public class ToDoListService implements ToDoListServiceInterface{
	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private ToDoListRepository toDoListRepository;
	
	/**
	  * Este metodo sirve para levantar todos los to-do list de los usuarios del sistema
	  * @param ptoDoListRequest Este parametro es la peticion del front-end que contiene
	  * que se usa para acceder al metodo deseado
	  * @return uiToDoList Lista de to-do list de los usuarios del sistema
	  */
	@Override
	@Transactional
	public List<ToDoListPOJO> getAll(ToDoListRequest ptoDoListRequest) {
		List<TToDoList> toDoList = toDoListRepository.findAll();
		return generateToDoListDtos(toDoList);
	}
	
	/**
	  * Este metodo sirve para copiar los atributos de los objetos levantados a los POJOs
	  * @param ptoDoListList Este parametro es la lista de to-do list levantados del repositorio
	  * @return uiToDoList Lista de to-do list de los bancos
	  */
	private List<ToDoListPOJO> generateToDoListDtos(List<TToDoList> ptoDoListList) {
		List<ToDoListPOJO> uiToDoList = new ArrayList<ToDoListPOJO>();
		ptoDoListList.stream().forEach(u -> {
			ToDoListPOJO dto = new ToDoListPOJO();
			BeanUtils.copyProperties(u, dto);
			uiToDoList.add(dto);
		});
		return uiToDoList;
	}

	/**
	  * Este metodo sirve para crear o guardar un objeto todo-list de un usuario
	  * @param ptoDoListRequest Este parametro es la peticion del front-end que contiene el objeto
	  * con la relacion a el to-do list del banco para guardarlo en la base de datos
	  * @return newToDoList todo list creado o modificado
	  */
	@Override
	@Transactional
	public TToDoList saveToDoList(ToDoListRequest ptoDoListRequest) {
		TToDoList toDoList = new TToDoList();
		BeanUtils.copyProperties(ptoDoListRequest.getToDoList(), toDoList);
		toDoList.setTuser(new Tuser());
		BeanUtils.copyProperties(ptoDoListRequest.getToDoList().getTuser(), toDoList.getTuser());
		TToDoList newToDoList = toDoListRepository.save(toDoList);
		
		return newToDoList;
	}

}

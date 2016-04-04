package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.ejb.Titem;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;
import com.techflow.propiedadesCR.pojo.ToDoListPOJO;
import com.techflow.propiedadesCR.repositories.ToDoListItemsRepository;
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
     * Objeto que se comunica con la base de datos
     */
	@Autowired private ToDoListItemsRepository toDoListItemsRepository;
	
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
		List<TToDoList> correctToDos= new ArrayList<TToDoList>();
		toDoList.stream().forEach(toDo -> {
			TToDoList tToDoList = toDoListRepository.findOne(toDo.getIdToDoList());
			if(toDo.getActive()==1 && toDo.getTuser().getIdUser() == ptoDoListRequest.getToDoList().getTuser().getIdUser()){
				correctToDos.add(tToDoList);
			}
		});
		return generateToDoListDtos(correctToDos);
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
			dto.setTuser(null);
			dto.setTitems(null);
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

	
	/**
	 * Este método elimina lógicamente un toDoList en el sistema.
	 *
	 * @param ptoDoListRequest Contiene información del objeto a eliminar.
     * 
	 * @return newToDo Devuelve el toDoList eliminado con sus nuevos datos.
	 *
	 *@author  Valeria Ramírez 
	 */
	@Override
	@Transactional
	public TToDoList deleteToDoList(ToDoListRequest ptoDoListRequest){
		TToDoList tTodoList = toDoListRepository.findOne(ptoDoListRequest.getToDoList().getIdToDoList());
		tTodoList.setActive((byte) 0);
		TToDoList newToDo = toDoListRepository.save(tTodoList);
		return newToDo;
	}

	@Override
	public TToDoList generateUserToDoList(BankToDoListPOJO pbankToDoList, int pidUser) {
		
		//ArrayList<Titem> items = new ArrayList<Titem>();
		
		TToDoList newToDoList = new TToDoList();
		newToDoList.setName(pbankToDoList.getName());
		newToDoList.setDescription(pbankToDoList.getDescription());
		newToDoList.setTuser(new Tuser());
		newToDoList.getTuser().setIdUser(pidUser);
		newToDoList.setActive((byte) 1);
		
		TToDoList newToDo = toDoListRepository.save(newToDoList);
		
		if(pbankToDoList.getTbankItems() != null){
			pbankToDoList.getTbankItems().stream().forEach(item->{
				Titem newItem = new Titem();
				BeanUtils.copyProperties(item, newItem);
				newItem.setTToDoList(newToDoList);
				newItem.getTToDoList().setIdToDoList(newToDo.getIdToDoList());
				toDoListItemsRepository.save(newItem);
				//items.add(newItem);
			});
		}
		
		//newToDoList.setTitems(items);
		
		
		return newToDo;
	}

}


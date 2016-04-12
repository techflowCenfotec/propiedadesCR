package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.ejb.Titem;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;
import com.techflow.propiedadesCR.pojo.ToDoListItemPOJO;
import com.techflow.propiedadesCR.pojo.ToDoListPOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.ToDoListItemsRepository;
import com.techflow.propiedadesCR.repositories.ToDoListRepository;

import antlr.StringUtils;
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
			if(toDo.getTuser().getIdUser() == ptoDoListRequest.getToDoList().getTuser().getIdUser()){
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
			UserPOJO user = new UserPOJO();
			BeanUtils.copyProperties(u, dto);
			BeanUtils.copyProperties(u.getTuser(), user);
			dto.setTuser(user);
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
	public boolean deleteToDoList(ToDoListRequest ptoDoListRequest){
		TToDoList tTodoList = toDoListRepository.findOne(ptoDoListRequest.getToDoList().getIdToDoList());
		tTodoList.getTitems().stream().forEach(u ->{
			toDoListItemsRepository.delete(u);
		});
		toDoListRepository.delete(tTodoList);
		return true;
		
	}

	@Override
	public TToDoList generateUserToDoList(BankToDoListPOJO pbankToDoList, int pidUser) {
				
		TToDoList newToDoList = new TToDoList();
		pbankToDoList.setTbank(pbankToDoList.getTbank());
		newToDoList.setName(pbankToDoList.getTbank().getName());
		newToDoList.setDescription(pbankToDoList.getDescription());
		newToDoList.setTuser(new Tuser());
		newToDoList.getTuser().setIdUser(pidUser);
		newToDoList.setIsDone((byte) 0);	
		
		TToDoList newToDo = toDoListRepository.save(newToDoList);
		
		if(pbankToDoList.getTbankItems() != null){
			pbankToDoList.getTbankItems().stream().forEach(item->{
				Titem newItem = new Titem();
				BeanUtils.copyProperties(item, newItem);
				newItem.setTToDoList(newToDoList);
				newItem.getTToDoList().setIdToDoList(newToDo.getIdToDoList());
				toDoListItemsRepository.save(newItem);
			
			});
		}
		

		return newToDo;
	}

	@Override
	 public ToDoListPOJO getMyItems(ToDoListRequest ptoDoListRequest) {
	  TToDoList todoList = new TToDoList();
	  ToDoListPOJO pojo = new ToDoListPOJO();
	  todoList = toDoListRepository.getByIdToDoList(ptoDoListRequest.getToDoList().getIdToDoList());
	  BeanUtils.copyProperties(todoList, pojo);
	  todoList.setTitems(todoList.getTitems());
	  generateItemsDtos(todoList.getTitems(),pojo);
	  return pojo;
	 }
	 private void generateItemsDtos(List<Titem> pItems,ToDoListPOJO pojo) {
	  List<ToDoListItemPOJO> uiRatings = new ArrayList<ToDoListItemPOJO>();
	  pItems.stream().forEach(u -> {
	  ToDoListItemPOJO itemPOJO = new ToDoListItemPOJO();
	  BeanUtils.copyProperties(u, itemPOJO);
	   
	   uiRatings.add(itemPOJO);
	  });
	  pojo.setTitems(uiRatings);
	 }

	@Override
	public ToDoListPOJO saveMyItems(ToDoListRequest ptoDoListRequest) {
		TToDoList toDoList = toDoListRepository.findOne(ptoDoListRequest.getToDoList().getIdToDoList());
		ToDoListPOJO pojo = new ToDoListPOJO();
		boolean isDone = true;
		List<ToDoListItemPOJO> uiItems = new ArrayList<ToDoListItemPOJO>();
		BeanUtils.copyProperties(toDoList, pojo);
		
		ptoDoListRequest.getToDoList().getTitems().stream().forEach(u ->{
			Titem item = new Titem();
			ToDoListItemPOJO itemPOJO = new ToDoListItemPOJO();
			BeanUtils.copyProperties(u, item);
			item.setTToDoList(toDoList);
			BeanUtils.copyProperties(toDoListItemsRepository.save(item),itemPOJO);
			uiItems.add(itemPOJO);
		});
		for (ToDoListItemPOJO toDoListItemPOJO : uiItems) {
			if(toDoListItemPOJO.getDone()==0){
				isDone = false;
			}
		}
		
		if(isDone){
			toDoList.setIsDone((byte)1);
			toDoList.setRegistrationDate(new Date());
			
		}else{
			toDoList.setIsDone((byte)0);
			toDoList.setRegistrationDate(null);
		}
		toDoListRepository.save(toDoList);	
		
		pojo.setTitems(uiItems);
		return pojo;
	}

	/**
	  * Este metodo sirve para cargar todos los to-do list que esten completos del sistema
	  * @param ptoDoListRequest Este parametro es la peticion del usuario
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de to-do list del sistema
	  */
	@Override
	public List<ToDoListPOJO> getAllFinished() {
		List<TToDoList> toDoList = toDoListRepository.findAll();
		List<TToDoList> correctToDos= new ArrayList<TToDoList>();
		toDoList.stream().forEach(toDo -> {
			TToDoList tToDoList = toDoListRepository.findOne(toDo.getIdToDoList());
			if(toDo.getIsDone()==1
					&& toDo.getRegistrationDate().toString().substring(0, 4).equals(new Date().toString().substring(24, 28))){
				
				correctToDos.add(tToDoList);
			}
		});
		return generateToDoListDtos(correctToDos);
		
	}


}


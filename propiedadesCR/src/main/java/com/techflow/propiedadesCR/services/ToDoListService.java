package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.pojo.ToDoListPOJO;
import com.techflow.propiedadesCR.repositories.ToDoListRepository;

@Service
public class ToDoListService implements ToDoListServiceInterface{

	@Autowired private ToDoListRepository toDoListRepository;
	
	@Override
	@Transactional
	public List<ToDoListPOJO> getAll(ToDoListRequest ptoDoListRequest) {
		List<TToDoList> toDoList = toDoListRepository.findAll();
		return generateToDoListDtos(toDoList);
	}
	
	private List<ToDoListPOJO> generateToDoListDtos(List<TToDoList> toDoList) {
		List<ToDoListPOJO> uiToDoList = new ArrayList<ToDoListPOJO>();
		toDoList.stream().forEach(u -> {
			ToDoListPOJO dto = new ToDoListPOJO();
			BeanUtils.copyProperties(u, dto);
			uiToDoList.add(dto);
		});
		return uiToDoList;
	}

}

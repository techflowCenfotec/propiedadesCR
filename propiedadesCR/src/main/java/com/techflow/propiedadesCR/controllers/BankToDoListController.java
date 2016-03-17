package com.techflow.propiedadesCR.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.BankToDoListItemRequest;
import com.techflow.propiedadesCR.contracts.BankToDoListItemResponse;
import com.techflow.propiedadesCR.contracts.BankToDoListRequest;
import com.techflow.propiedadesCR.contracts.BankToDoListResponse;
import com.techflow.propiedadesCR.ejb.TbankItem;
import com.techflow.propiedadesCR.ejb.TbankToDoList;
import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;
import com.techflow.propiedadesCR.services.BankToDoListServiceInterface;
import com.techflow.propiedadesCR.services.ToDoListServiceInterface;
/**
* <h1>Controlador del to-do list de los bancos</h1>
* Esta clase es la encargada de recibir los pedidos del front-end
* y manejar todo lo relacionado a los to-do list de los bancos
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/

@RestController
@RequestMapping(value = "rest/protected/banktodolist")
public class BankToDoListController {

	/**
     * Objeto que ofrece los servicios de los to-do list de los bancos
     */
	@Autowired private BankToDoListServiceInterface bankToDoListService;
	
	/**
	  * Este metodo sirve para cargar todos los to-do list de todos los bancos del sistema
	  * @param pbankToDoListRequest Este parametro es la peticion del front-end
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de to-do list del sistema
	  */
	@RequestMapping(value="/getAll",method = RequestMethod.POST)
	public BankToDoListResponse getAll(@RequestBody BankToDoListRequest pbankToDoListRequest){
		BankToDoListResponse response = new BankToDoListResponse();
		response.setCode(200);
        response.setCodeMessage("banktodolist fetch successful");
        response.setBankToDoList(bankToDoListService.getAll(pbankToDoListRequest));
        return response;
	}
	
	/**
	  * Este metodo sirve para crear un to-do list nuevo para un banco especifico
	  * @param pbankToDoListRequest Este parametro es la peticion del front-end
	  * que se usa para obtener el objeto nuevo que se va ingresar al sistema
	  * @return response Resultado de la peticion
	  */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public BankToDoListResponse create(@RequestBody BankToDoListRequest pbankToDoListRequest){
		BankToDoListResponse response = new BankToDoListResponse();
		if(pbankToDoListRequest.getBankToDoList().getTbank()!=null){
			TbankToDoList bankToDoList = bankToDoListService.saveBankToDoList(pbankToDoListRequest);
			if(bankToDoList!=null){
				response.setCode(200);
				response.setCodeMessage("created successfully");
			}
		}else{
			response.setCode(400);
			response.setCodeMessage("the request object is not valid, please select a bank");
		}
		return response;
	}

	/**
	  * Este metodo sirve para crear un item y asignarlo a un to-do list especifico
	  * @param pbankToDoListItemRequest Este parametro es la peticion del front-end
	  * que se usa para obtener el objeto nuevo que se va ingresar al sistema
	  * @return response Resultado de la peticion
	  */
	@RequestMapping(value="/createItem",method=RequestMethod.POST)
	public BankToDoListItemResponse createItem(@RequestBody BankToDoListItemRequest pbankToDoListItemRequest){
		BankToDoListItemResponse response = new BankToDoListItemResponse();
		if(pbankToDoListItemRequest.getBankToDoListItem().getTbankToDoList()!=null){
			TbankItem bankToDoListItem = bankToDoListService.saveBankToDoListItem(pbankToDoListItemRequest);
			if(bankToDoListItem!=null){
				response.setCode(200);
				response.setCodeMessage("created successfully");
			}
		}
		return response;
	}
	
	/**
	  * Este metodo sirve para levantar un to-do list especifico con sus items
	  * @param pbankToDoListItemRequest Este parametro es la peticion del front-end
	  * que se usa para obtener el id del to-do list a consultar
	  * @return response Resultado de la peticion
	  */
	@RequestMapping(value="/getById", method=RequestMethod.POST)
	public BankToDoListResponse getById(@RequestBody BankToDoListRequest pbankToDoListRequest){
		BankToDoListResponse response = new BankToDoListResponse();
		
		if(pbankToDoListRequest.getBankToDoList().getIdtBank_to_do_list()!= 0){
			BankToDoListPOJO bankToDoList = bankToDoListService.getBankToDoListById(pbankToDoListRequest);
			response.setBankToDoList(new ArrayList<BankToDoListPOJO>() {{add(bankToDoList);}});
		}
		
		return response;
	}
	
}

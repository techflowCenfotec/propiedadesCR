package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.BankRequest;
import com.techflow.propiedadesCR.contracts.BankResponse;
import com.techflow.propiedadesCR.services.BankServiceInterface;
/**
* <h1>Controlador de los bancos</h1>
* Esta clase es la encargada de recibir los pedidos del front-end
* y manejar todo lo relacionado a los bancos
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
@RestController
@RequestMapping(value = "rest/protected/banks")
public class BankController {
	
	/**
     * Objeto que ofrece los servicios del banco
     */
	@Autowired private BankServiceInterface bankService;
	/**
	  * Este metodo sirve para cargar todos los bancos del sistema
	  * @param pbankRequest Este parametro es la peticion del front-end
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de bancos del sistema
	  */
	@RequestMapping(value="/getAll",method = RequestMethod.POST)
	public BankResponse getAll(@RequestBody BankRequest pbankRequest){
		BankResponse response = new BankResponse();
		response.setBanks(bankService.getAll());
		response.setCode(200);
		response.setCodeMessage("fetch successful");
		
		return response;
	}

}

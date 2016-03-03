package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.BankToDoListItemRequest;
import com.techflow.propiedadesCR.contracts.BankToDoListRequest;
import com.techflow.propiedadesCR.ejb.Tbank;
import com.techflow.propiedadesCR.ejb.TbankItem;
import com.techflow.propiedadesCR.ejb.TbankToDoList;
import com.techflow.propiedadesCR.pojo.BankPOJO;
import com.techflow.propiedadesCR.pojo.BankToDoListItemPOJO;
import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;
import com.techflow.propiedadesCR.repositories.BankToDoListItemRespository;
import com.techflow.propiedadesCR.repositories.BankToDoListRepository;
/**
* <h1>Servicio de los to-do list de los bancos</h1>
* Esta clase es la encargada de ofrecer los servicios
* y administrar las transacciones al repositorio
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
@Service
public class BankToDoListService implements BankToDoListServiceInterface{
	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private BankToDoListRepository bankToDoListRepository;
	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private BankToDoListItemRespository bankToDoListItemRepository;
	
	/**
	  * Este metodo sirve para levantar todos los to-d0 list de los bancos del sistema
	  * @return uiBankToDoList Lista de to-do list de los bancos del sistema
	  */
	@Override
	@Transactional
	public List<BankToDoListPOJO> getAll(BankToDoListRequest pbankToDoListRequest) {
		List<TbankToDoList> bankToDoList = bankToDoListRepository.findAll();
		return generateBankToDoListDtos(bankToDoList);
	}
	
	/**
	  * Este metodo sirve para guardar un objeto en el sistema
	  * @param pbankToDoListRequest Este parametro es la peticion del front-end que contiene
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de bancos del sistema
	  */
	@Override
	@Transactional
	public TbankToDoList saveBankToDoList(BankToDoListRequest pbankToDoListRequest) {
		TbankToDoList bankToDoList = new TbankToDoList();
		BeanUtils.copyProperties(pbankToDoListRequest.getBankToDoList(), bankToDoList);
		bankToDoList.setTbank(new Tbank());
		BeanUtils.copyProperties(pbankToDoListRequest.getBankToDoList().getTbank(), bankToDoList.getTbank());

		TbankToDoList newbankToDoList = bankToDoListRepository.save(bankToDoList);
		return newbankToDoList;
	}
	
	/**
	  * Este metodo sirve para copiar los atributos de los objetos levantados a los POJOs
	  * @param pbankToDoListList Este parametro es la lista de to-do list levantados del repositorio
	  * @return uiBankToDoList Lista de to-do list de los bancos
	  */
	private List<BankToDoListPOJO> generateBankToDoListDtos(List<TbankToDoList> pbankToDoListList) {
		List<BankToDoListPOJO> uiBankToDoList = new ArrayList<BankToDoListPOJO>();
		pbankToDoListList.stream().forEach(u -> {
			BankToDoListPOJO dto = new BankToDoListPOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTbank(new BankPOJO());
			BeanUtils.copyProperties(u.getTbank(), dto.getTbank());
			dto.setTbankItems(null);
			uiBankToDoList.add(dto);
		});
		return uiBankToDoList;
	}
	
	/**
	  * Este metodo sirve para guardar un objeto item y asignarlo a un todo-list de un banco en el sistema
	  * @param pbankToDoListItemRequest Este parametro es la peticion del front-end que contiene el objeto
	  * con la relacion a el to-do list del banco para guardarlo en la base de datos
	  * @return newBankToDoListItem Item insertado
	  */
	@Override
	@Transactional
	public TbankItem saveBankToDoListItem(BankToDoListItemRequest pbankToDoListItemRequest) {
		TbankItem bankToDoListItem = new TbankItem();
		BeanUtils.copyProperties(pbankToDoListItemRequest.getBankToDoListItem(), bankToDoListItem);
		TbankItem newBankToDoListItem = bankToDoListItemRepository.save(bankToDoListItem);
		return newBankToDoListItem;
	}

	/**
	  * Este metodo sirve para guardar un objeto en el sistema
	  * @param pbankToDoListRequest Este parametro es la peticion del front-end que contiene
	  * que se usa para acceder al metodo deseado
	  * @return response Resultado con la lista de bancos del sistema
	  */
	@Override
	@Transactional
	public BankToDoListPOJO getBankToDoListById(BankToDoListRequest pbankToDoListRequest) {
		TbankToDoList bankToDoList = new TbankToDoList();
		bankToDoList = bankToDoListRepository.findOne(Integer.parseInt(pbankToDoListRequest.getSearchTerm()));

		BankToDoListPOJO dto = new BankToDoListPOJO();
		BeanUtils.copyProperties(bankToDoList, dto);
		
		dto.setTbank(new BankPOJO());
		BeanUtils.copyProperties(bankToDoList.getTbank(), dto.getTbank());
		
		ArrayList<BankToDoListItemPOJO> temp = new ArrayList<>();
		bankToDoList.getTbankItems().stream().forEach(u -> {
			BankToDoListItemPOJO item = new BankToDoListItemPOJO();
			BeanUtils.copyProperties(u, item);
			item.setTbankToDoList(null);
			temp.add(item);
		});
		
		dto.setTbankItems(temp);
		
		return dto;
	}


}

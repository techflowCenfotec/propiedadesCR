package com.techflow.propiedadesCR.services;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.BankToDoListItemRequest;
import com.techflow.propiedadesCR.contracts.BankToDoListRequest;
import com.techflow.propiedadesCR.ejb.TbankItem;
import com.techflow.propiedadesCR.ejb.TbankToDoList;
import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;
import com.techflow.propiedadesCR.repositories.BankToDoListItemRespository;
import com.techflow.propiedadesCR.repositories.BankToDoListRepository;

@Service
public class BankToDoListService implements BankToDoListServiceInterface{

	@Autowired private BankToDoListRepository bankToDoListRepository;
	@Autowired private BankToDoListItemRespository bankToDoListItemRepository;
	
	@Override
	@Transactional
	public List<BankToDoListPOJO> getAll(BankToDoListRequest pbankToDoListRequest) {
		List<TbankToDoList> bankToDoList = bankToDoListRepository.findAll();
		return generateBankToDoListDtos(bankToDoList);
	}

	@Override
	@Transactional
	public TbankToDoList saveBankToDoList(BankToDoListRequest pbankToDoListRequest) {
		TbankToDoList bankToDoList = new TbankToDoList();
		BeanUtils.copyProperties(pbankToDoListRequest.getBankToDoList(), bankToDoList);
		
		TbankToDoList newbankToDoList = bankToDoListRepository.save(bankToDoList);
		return newbankToDoList;
	}
	
	private List<BankToDoListPOJO> generateBankToDoListDtos(List<TbankToDoList> pbankToDoListList) {
		List<BankToDoListPOJO> uiBankToDoList = new ArrayList<BankToDoListPOJO>();
		pbankToDoListList.stream().forEach(u -> {
			BankToDoListPOJO dto = new BankToDoListPOJO();
			BeanUtils.copyProperties(u, dto);
			uiBankToDoList.add(dto);
		});
		return uiBankToDoList;
	}

	@Override
	@Transactional
	public TbankItem saveBankToDoListItem(BankToDoListItemRequest pbankToDoListItemRequest) {
		TbankItem bankToDoListItem = new TbankItem();
		BeanUtils.copyProperties(pbankToDoListItemRequest.getBankToDoListItem(), bankToDoListItem);
		TbankItem newBankToDoListItem = bankToDoListItemRepository.save(bankToDoListItem);
		return newBankToDoListItem;
	}


}

package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TbankItem;

public interface BankToDoListItemRespository extends CrudRepository<TbankItem, Integer>{
	List<TbankItem> findAll();
}

package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tbank;
import com.techflow.propiedadesCR.pojo.BankPOJO;
import com.techflow.propiedadesCR.repositories.BankRepository;

@Service
public class BankService implements BankServiceInterface{

	@Autowired private BankRepository bankRepository;

	@Override
	@Transactional
	public List<BankPOJO> getAll() {
		List<Tbank> banks = bankRepository.findAll();
		return generateBanksDtos(banks);
	}

	private List<BankPOJO> generateBanksDtos(List<Tbank> pbanks) {
		List<BankPOJO> uiBanks = new ArrayList<BankPOJO>();
		pbanks.stream().forEach(u -> {
			BankPOJO dto = new BankPOJO();
			BeanUtils.copyProperties(u, dto);
			uiBanks.add(dto);
		});
		return uiBanks;
	}

}

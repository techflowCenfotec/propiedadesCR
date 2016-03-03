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
/**
* <h1>Servicio de los bancos</h1>
* Esta clase es la encargada de ofrecer los servicios
* y administrar las transacciones al repositorio
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
@Service
public class BankService implements BankServiceInterface{

	/**
     * Objeto que se comunica con la base de datos
     */
	@Autowired private BankRepository bankRepository;

	/**
	  * Este metodo sirve para levantar todos los bancos del sistema
	  * @return uiBanks Lista de bancos del sistema
	  */
	@Override
	@Transactional
	public List<BankPOJO> getAll() {
		List<Tbank> banks = bankRepository.findAll();
		return generateBanksDtos(banks);
	}

	/**
	  * Este metodo sirve copiar los atributos de los objetos levantados a los POJOs
	  * @param pbanks Este parametro es la lista de bancos levantados del repositorio
	  * @return uiBanks Lista de bancos del sistema
	  */
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

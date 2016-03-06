package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbank_item database table.
 * 
 */
@Entity
@Table(name="tbank_item")
@NamedQuery(name="TbankItem.findAll", query="SELECT t FROM TbankItem t")
public class TbankItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idtBank_item;
	private String name;
	private TbankToDoList tbankToDoList;

	public TbankItem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdtBank_item() {
		return this.idtBank_item;
	}

	public void setIdtBank_item(int idtBank_item) {
		this.idtBank_item = idtBank_item;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TbankToDoList
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_bank_to_do_list")
	public TbankToDoList getTbankToDoList() {
		return this.tbankToDoList;
	}

	public void setTbankToDoList(TbankToDoList tbankToDoList) {
		this.tbankToDoList = tbankToDoList;
	}

}
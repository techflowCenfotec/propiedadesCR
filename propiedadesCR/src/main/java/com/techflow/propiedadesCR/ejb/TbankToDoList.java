package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbank_to_do_list database table.
 * 
 */
@Entity
@Table(name="tbank_to_do_list")
@NamedQuery(name="TbankToDoList.findAll", query="SELECT t FROM TbankToDoList t")
public class TbankToDoList implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idtBank_to_do_list;
	private String description;
	private String name;
	private List<TbankItem> tbankItems;
	private Tbank tbank;

	public TbankToDoList() {
	}


	@Id
	public int getIdtBank_to_do_list() {
		return this.idtBank_to_do_list;
	}

	public void setIdtBank_to_do_list(int idtBank_to_do_list) {
		this.idtBank_to_do_list = idtBank_to_do_list;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TbankItem
	@OneToMany(mappedBy="tbankToDoList")
	public List<TbankItem> getTbankItems() {
		return this.tbankItems;
	}

	public void setTbankItems(List<TbankItem> tbankItems) {
		this.tbankItems = tbankItems;
	}

	public TbankItem addTbankItem(TbankItem tbankItem) {
		getTbankItems().add(tbankItem);
		tbankItem.setTbankToDoList(this);

		return tbankItem;
	}

	public TbankItem removeTbankItem(TbankItem tbankItem) {
		getTbankItems().remove(tbankItem);
		tbankItem.setTbankToDoList(null);

		return tbankItem;
	}


	//bi-directional many-to-one association to Tbank
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_bank")
	public Tbank getTbank() {
		return this.tbank;
	}

	public void setTbank(Tbank tbank) {
		this.tbank = tbank;
	}

}
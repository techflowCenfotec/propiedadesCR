package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbank database table.
 * 
 */
@Entity
@NamedQuery(name="Tbank.findAll", query="SELECT t FROM Tbank t")
public class Tbank implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idBank;
	private String name;
	private List<TbankToDoList> tbankToDoLists;
	private List<Tguide> tguides;

	public Tbank() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_bank")
	public int getIdBank() {
		return this.idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TbankToDoList
	@OneToMany(mappedBy="tbank")
	public List<TbankToDoList> getTbankToDoLists() {
		return this.tbankToDoLists;
	}

	public void setTbankToDoLists(List<TbankToDoList> tbankToDoLists) {
		this.tbankToDoLists = tbankToDoLists;
	}

	public TbankToDoList addTbankToDoList(TbankToDoList tbankToDoList) {
		getTbankToDoLists().add(tbankToDoList);
		tbankToDoList.setTbank(this);

		return tbankToDoList;
	}

	public TbankToDoList removeTbankToDoList(TbankToDoList tbankToDoList) {
		getTbankToDoLists().remove(tbankToDoList);
		tbankToDoList.setTbank(null);

		return tbankToDoList;
	}


	//bi-directional many-to-one association to Tguide
	@OneToMany(mappedBy="tbank")
	public List<Tguide> getTguides() {
		return this.tguides;
	}

	public void setTguides(List<Tguide> tguides) {
		this.tguides = tguides;
	}

	public Tguide addTguide(Tguide tguide) {
		getTguides().add(tguide);
		tguide.setTbank(this);

		return tguide;
	}

	public Tguide removeTguide(Tguide tguide) {
		getTguides().remove(tguide);
		tguide.setTbank(null);

		return tguide;
	}

}
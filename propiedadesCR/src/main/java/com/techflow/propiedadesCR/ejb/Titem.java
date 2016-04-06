package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the titems database table.
 * 
 */
@Entity
@Table(name="titems")
@NamedQuery(name="Titem.findAll", query="SELECT t FROM Titem t")
public class Titem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idItem;
	private byte done;
	private String name;
	private TToDoList TToDoList;

	public Titem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdItem() {
		return this.idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}


	public byte getDone() {
		return this.done;
	}

	public void setDone(byte done) {
		this.done = done;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TToDoList
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_to_do_list")
	
	public TToDoList getTToDoList() {
		return this.TToDoList;
	}

	public void setTToDoList(TToDoList TToDoList) {
		this.TToDoList = TToDoList;
	}

}
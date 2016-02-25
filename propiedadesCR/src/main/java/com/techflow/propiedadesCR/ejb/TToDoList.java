package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_to_do_list database table.
 * 
 */
@Entity
@Table(name="t_to_do_list")
@NamedQuery(name="TToDoList.findAll", query="SELECT t FROM TToDoList t")
public class TToDoList implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idToDoList;
	private String description;
	private String name;
	private Tuser tuser;
	private List<Titem> titems;

	public TToDoList() {
	}


	@Id
	@Column(name="id_to_do_list")
	public int getIdToDoList() {
		return this.idToDoList;
	}

	public void setIdToDoList(int idToDoList) {
		this.idToDoList = idToDoList;
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


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}


	//bi-directional many-to-one association to Titem
	@OneToMany(mappedBy="TToDoList")
	public List<Titem> getTitems() {
		return this.titems;
	}

	public void setTitems(List<Titem> titems) {
		this.titems = titems;
	}

	public Titem addTitem(Titem titem) {
		getTitems().add(titem);
		titem.setTToDoList(this);

		return titem;
	}

	public Titem removeTitem(Titem titem) {
		getTitems().remove(titem);
		titem.setTToDoList(null);

		return titem;
	}

}
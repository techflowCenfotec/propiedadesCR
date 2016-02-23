package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the toptions database table.
 * 
 */
@Entity
@Table(name="toptions")
@NamedQuery(name="Toption.findAll", query="SELECT t FROM Toption t")
public class Toption implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idOption;
	private String option;
	private String result;
	private Tquestion tquestion1;
	private Tquestion tquestion2;

	public Toption() {
	}


	@Id
	@Column(name="id_option")
	public int getIdOption() {
		return this.idOption;
	}

	public void setIdOption(int idOption) {
		this.idOption = idOption;
	}


	public String getOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}


	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	//bi-directional many-to-one association to Tquestion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_next_question")
	public Tquestion getTquestion1() {
		return this.tquestion1;
	}

	public void setTquestion1(Tquestion tquestion1) {
		this.tquestion1 = tquestion1;
	}


	//bi-directional many-to-one association to Tquestion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_question")
	public Tquestion getTquestion2() {
		return this.tquestion2;
	}

	public void setTquestion2(Tquestion tquestion2) {
		this.tquestion2 = tquestion2;
	}

}
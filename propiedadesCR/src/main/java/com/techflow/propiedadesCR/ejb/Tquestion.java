package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tquestions database table.
 * 
 */
@Entity
@Table(name="tquestions")
@NamedQuery(name="Tquestion.findAll", query="SELECT t FROM Tquestion t")
public class Tquestion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idQuestion;
	private byte active;
	private String question;
	private String tQuestionscol;
	private List<Tanswer> tanswers;
	private List<Toption> toptions1;
	private List<Toption> toptions2;

	public Tquestion() {
	}


	@Id
	@Column(name="id_question")
	public int getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}


	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	public String getTQuestionscol() {
		return this.tQuestionscol;
	}

	public void setTQuestionscol(String tQuestionscol) {
		this.tQuestionscol = tQuestionscol;
	}


	//bi-directional many-to-one association to Tanswer
	@OneToMany(mappedBy="tquestion")
	public List<Tanswer> getTanswers() {
		return this.tanswers;
	}

	public void setTanswers(List<Tanswer> tanswers) {
		this.tanswers = tanswers;
	}

	public Tanswer addTanswer(Tanswer tanswer) {
		getTanswers().add(tanswer);
		tanswer.setTquestion(this);

		return tanswer;
	}

	public Tanswer removeTanswer(Tanswer tanswer) {
		getTanswers().remove(tanswer);
		tanswer.setTquestion(null);

		return tanswer;
	}


	//bi-directional many-to-one association to Toption
	@OneToMany(mappedBy="tquestion1")
	public List<Toption> getToptions1() {
		return this.toptions1;
	}

	public void setToptions1(List<Toption> toptions1) {
		this.toptions1 = toptions1;
	}

	public Toption addToptions1(Toption toptions1) {
		getToptions1().add(toptions1);
		toptions1.setTquestion1(this);

		return toptions1;
	}

	public Toption removeToptions1(Toption toptions1) {
		getToptions1().remove(toptions1);
		toptions1.setTquestion1(null);

		return toptions1;
	}


	//bi-directional many-to-one association to Toption
	@OneToMany(mappedBy="tquestion2")
	public List<Toption> getToptions2() {
		return this.toptions2;
	}

	public void setToptions2(List<Toption> toptions2) {
		this.toptions2 = toptions2;
	}

	public Toption addToptions2(Toption toptions2) {
		getToptions2().add(toptions2);
		toptions2.setTquestion2(this);

		return toptions2;
	}

	public Toption removeToptions2(Toption toptions2) {
		getToptions2().remove(toptions2);
		toptions2.setTquestion2(null);

		return toptions2;
	}

}
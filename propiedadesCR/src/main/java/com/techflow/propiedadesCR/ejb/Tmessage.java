package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tmessages database table.
 * 
 */
@Entity
@Table(name="tmessages")
@NamedQuery(name="Tmessage.findAll", query="SELECT t FROM Tmessage t")
public class Tmessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idMessage;
	private byte active;
	private String message;
	private Date registrationDate;
	private String subject;
	private Tuser tuser1;
	private Tuser tuser2;

	public Tmessage() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_message")
	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}


	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	@Lob
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registration_date")
	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_receiver")
	public Tuser getTuser1() {
		return this.tuser1;
	}

	public void setTuser1(Tuser tuser1) {
		this.tuser1 = tuser1;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sender")
	public Tuser getTuser2() {
		return this.tuser2;
	}

	public void setTuser2(Tuser tuser2) {
		this.tuser2 = tuser2;
	}

}
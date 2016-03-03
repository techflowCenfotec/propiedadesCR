package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tguides database table.
 * 
 */
@Entity
@Table(name="tguides")
@NamedQuery(name="Tguide.findAll", query="SELECT t FROM Tguide t")
public class Tguide implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idGuides;
	private String url;
	private Tbank tbank;

	public Tguide() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_guides")
	public int getIdGuides() {
		return this.idGuides;
	}

	public void setIdGuides(int idGuides) {
		this.idGuides = idGuides;
	}


	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
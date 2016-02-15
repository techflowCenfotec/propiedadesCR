package com.techflow.propiedadesCR.ejb;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tresidence_type database table.
 * 
 */
@Entity
@Table(name="tresidence_type")
@NamedQuery(name="TresidenceType.findAll", query="SELECT t FROM TresidenceType t")
public class TresidenceType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idResidenceType;
	private String name;
	private List<Tresidence> tresidences;

	public TresidenceType() {
	}


	@Id
	@Column(name="id_residence_type")
	public int getIdResidenceType() {
		return this.idResidenceType;
	}

	public void setIdResidenceType(int idResidenceType) {
		this.idResidenceType = idResidenceType;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Tresidence
	@OneToMany(mappedBy="tresidenceType")
	public List<Tresidence> getTresidences() {
		return this.tresidences;
	}

	public void setTresidences(List<Tresidence> tresidences) {
		this.tresidences = tresidences;
	}

	public Tresidence addTresidence(Tresidence tresidence) {
		getTresidences().add(tresidence);
		tresidence.setTresidenceType(this);

		return tresidence;
	}

	public Tresidence removeTresidence(Tresidence tresidence) {
		getTresidences().remove(tresidence);
		tresidence.setTresidenceType(null);

		return tresidence;
	}

}
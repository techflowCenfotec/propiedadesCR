package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbenefits database table.
 * 
 */
@Entity
@Table(name="tbenefits")
@NamedQuery(name="Tbenefit.findAll", query="SELECT t FROM Tbenefit t")
public class Tbenefit implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idBenefit;
	private String benefit;
	private List<Tproperty> tproperties;

	public Tbenefit() {
	}


	@Id
	@Column(name="id_benefit")
	public int getIdBenefit() {
		return this.idBenefit;
	}

	public void setIdBenefit(int idBenefit) {
		this.idBenefit = idBenefit;
	}


	public String getBenefit() {
		return this.benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}


	//bi-directional many-to-many association to Tproperty
	@ManyToMany(mappedBy="tbenefits")
	public List<Tproperty> getTproperties() {
		return this.tproperties;
	}

	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}

}
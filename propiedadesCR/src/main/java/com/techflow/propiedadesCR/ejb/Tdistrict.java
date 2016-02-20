package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tdistricts database table.
 * 
 */
@Entity
@Table(name="tdistricts")
@NamedQuery(name="Tdistrict.findAll", query="SELECT t FROM Tdistrict t")
public class Tdistrict implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idDisctrict;
	private int code;
	private String name;
	private Tcounty tcounty;

	public Tdistrict() {
	}


	@Id
	@Column(name="id_disctrict")
	public int getIdDisctrict() {
		return this.idDisctrict;
	}

	public void setIdDisctrict(int idDisctrict) {
		this.idDisctrict = idDisctrict;
	}


	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Tcounty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_county")
	public Tcounty getTcounty() {
		return this.tcounty;
	}

	public void setTcounty(Tcounty tcounty) {
		this.tcounty = tcounty;
	}

}
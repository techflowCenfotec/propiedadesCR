package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tpermissions database table.
 * 
 */
@Entity
@Table(name="tpermissions")
@NamedQuery(name="Tpermission.findAll", query="SELECT t FROM Tpermission t")
public class Tpermission implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPermissions;
	private String description;
	private List<Trole> troles;

	public Tpermission() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_permissions")
	public int getIdPermissions() {
		return this.idPermissions;
	}

	public void setIdPermissions(int idPermissions) {
		this.idPermissions = idPermissions;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	//bi-directional many-to-many association to Trole
	@ManyToMany(mappedBy="tpermissions")
	public List<Trole> getTroles() {
		return this.troles;
	}

	public void setTroles(List<Trole> troles) {
		this.troles = troles;
	}

}
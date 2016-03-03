package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the troles database table.
 * 
 */
@Entity
@Table(name="troles")
@NamedQuery(name="Trole.findAll", query="SELECT t FROM Trole t")
public class Trole implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRole;
	private String rolName;
	private List<Tpermission> tpermissions;
	private List<Tuser> tusers;

	public Trole() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_role")
	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}


	@Column(name="rol_name")
	public String getRolName() {
		return this.rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}


	//bi-directional many-to-many association to Tpermission
	@ManyToMany
	@JoinTable(
		name="tpermissions_per_rol"
		, joinColumns={
			@JoinColumn(name="id_rol")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_permission")
			}
		)
	public List<Tpermission> getTpermissions() {
		return this.tpermissions;
	}

	public void setTpermissions(List<Tpermission> tpermissions) {
		this.tpermissions = tpermissions;
	}


	//bi-directional many-to-one association to Tuser
	@OneToMany(mappedBy="trole")
	public List<Tuser> getTusers() {
		return this.tusers;
	}

	public void setTusers(List<Tuser> tusers) {
		this.tusers = tusers;
	}

	public Tuser addTuser(Tuser tuser) {
		getTusers().add(tuser);
		tuser.setTrole(this);

		return tuser;
	}

	public Tuser removeTuser(Tuser tuser) {
		getTusers().remove(tuser);
		tuser.setTrole(null);

		return tuser;
	}

}
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
	private int id_Rol;
	private String nombreRol;
	private List<Tpermission> tpermissions;
	private List<Tuser> tusers;

	public Trole() {
	}


	@Id
	public int getId_Rol() {
		return this.id_Rol;
	}

	public void setId_Rol(int id_Rol) {
		this.id_Rol = id_Rol;
	}


	@Column(name="nombre_rol")
	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
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
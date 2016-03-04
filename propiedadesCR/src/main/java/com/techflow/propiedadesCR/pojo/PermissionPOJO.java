package com.techflow.propiedadesCR.pojo;
/**
 * <h1>POJO del rol</h1>
 * Enfatiza el uso de la clase simple "permission"
 *@author Valeria Ramírez
 *@version 1.0
 *@since 03/03/2016
 */
public class PermissionPOJO {
	/*
	 * Id del permiso
	 */
	private int idPermissions;
	/*
	 * descripcion del permiso
	 */
	private String description;
	
	public PermissionPOJO(){
		super();
	}

	
	public int getIdPermissions() {
		return idPermissions;
	}

	public void setIdPermissions(int idPermissions) {
		this.idPermissions = idPermissions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}

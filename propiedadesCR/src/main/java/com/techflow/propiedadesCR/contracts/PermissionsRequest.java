package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.PermissionPOJO;
/**
 * <h1>Petición de permisos</h1>
 * Esta clase es la encargada de enviar peticiones al objeto que se encuetra levantado
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 03/03/2016
 */
public class PermissionsRequest {
	/**
	* Objeto POJO que contiene la informacion del permiso
	*
	*/ 
	PermissionPOJO permission;
	
	public PermissionsRequest(){
		super();
	}

	public PermissionPOJO getPermission() {
		return permission;
	}

	public void setPermission(PermissionPOJO permission) {
		this.permission = permission;
	}
	
	@Override
	public String toString() {
		return "PermissionRequest [permission=" + permission + "]";
	}	
}

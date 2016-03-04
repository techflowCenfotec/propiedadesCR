package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PermissionPOJO;
/**
 * <h1>Respuesta de permisos</h1>
 * Esta clase es la encargada de devolver una respuesta
 * de peticiones al objeto que se encuetra levantado
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 03/03/2016
 */
public class PermissionsResponse extends BaseResponse {
	/**
	* Lista POJO que contiene la informacion de los permisos
	*
	*/ 
private List<PermissionPOJO> permission;
	
	public PermissionsResponse(){
		super();
	}

	public List<PermissionPOJO> getPermission() {
		return permission;
	}

	public void setPermission(List<PermissionPOJO> permission) {
		this.permission = permission;
	}
	
}

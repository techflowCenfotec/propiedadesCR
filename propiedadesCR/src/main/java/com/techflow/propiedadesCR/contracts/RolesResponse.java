package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.RolePOJO;
/**
 * <h1>Respuesta de Roles</h1>
 * Esta clase es la encargada de enviar peticiones al objeto que se encuetra levantado
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 03/03/2016
 */
public class RolesResponse extends BaseResponse {
	/**
	* Lista POJO que contiene la informacion de los roles
	*
	*/ 
	private List<RolePOJO> role;
	
	public RolesResponse(){
		super();
	}

	public List<RolePOJO> getRole() {
		return role;
	}

	public void setRole(List<RolePOJO> prole) {
		this.role = prole;
	}
	
}

package com.techflow.propiedadesCR.contracts;
import com.techflow.propiedadesCR.pojo.RolePOJO;

/**
 * <h1>Petición de Roles</h1>
 * Esta clase es la encargada de enviar peticiones al objeto que se encuetra levantado
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 03/03/2016
 */
public class RolesRequest extends BaseRequest{
	/**
	* Objeto POJO que contiene la informacion del rol
	*
	*/ 
	RolePOJO role;
	
	public RolesRequest(){
		super();
	}

	public RolePOJO getRole() {
		return role;
	}

	public void setRole(RolePOJO prole) {
		this.role = prole;
	}
	
	@Override
	public String toString() {
		return "RolesRequest [role=" + role + "]";
	}	
}

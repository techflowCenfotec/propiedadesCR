package com.techflow.propiedadesCR.pojo;
import java.util.List;
/**
 * <h1>POJO del rol</h1>
 * Enfatiza el uso de la clase simple "role"
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/26/2016
 */
public class RolePOJO {
	/*
	 * Id del rol
	 */
	private int idRole;

	/*
	 * nombre del rol
	 */
	private String rolName;
	/*
	 * Lista de permisos
	 */
	private List<PermissionPOJO> tpermissions;
	/*
	 * Estado activo o inactivo del rol
	 */
	private byte active;
	
	public RolePOJO(){
		super();
	}


	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public List<PermissionPOJO> getTpermissions() {
		return tpermissions;
	}

	public void setTpermissions(List<PermissionPOJO> tpermissions) {
		this.tpermissions = tpermissions;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public byte getActive() {
		return active;
	}


	public void setActive(byte active) {
		this.active = active;
	}
	

}

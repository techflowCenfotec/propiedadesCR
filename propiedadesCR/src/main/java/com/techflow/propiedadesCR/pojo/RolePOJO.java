package com.techflow.propiedadesCR.pojo;
import java.util.List;

public class RolePOJO {
	private int id_Rol;
	private String nombreRol;
	private List<PermissionPOJO> tpermissions;
	
	public RolePOJO(){
		super();
	}

	public int getId_Rol() {
		return id_Rol;
	}

	public void setId_Rol(int id_Rol) {
		this.id_Rol = id_Rol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<PermissionPOJO> getTpermissions() {
		return tpermissions;
	}

	public void setTpermissions(List<PermissionPOJO> tpermissions) {
		this.tpermissions = tpermissions;
	}

}

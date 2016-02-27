package com.techflow.propiedadesCR.pojo;
import java.util.List;

public class RolePOJO {
	private int idRol;
	private String nombre;
	private List<PermissionPOJO> permissions;
	
	public RolePOJO(){
		super();
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int pidRol) {
		this.idRol = pidRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pnombre) {
		this.nombre = pnombre;
	}

	public List<PermissionPOJO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionPOJO> ppermissions) {
		this.permissions = ppermissions;
	}
	
}

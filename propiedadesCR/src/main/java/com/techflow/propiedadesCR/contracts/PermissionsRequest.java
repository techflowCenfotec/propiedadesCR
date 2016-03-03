package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.PermissionPOJO;

public class PermissionsRequest {
	
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

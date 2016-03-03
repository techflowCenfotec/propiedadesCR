package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PermissionPOJO;

public class PermissionsResponse extends BaseResponse {

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

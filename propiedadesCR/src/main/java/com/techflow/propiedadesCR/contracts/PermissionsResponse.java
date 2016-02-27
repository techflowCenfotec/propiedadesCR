package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PermissionPOJO;

public class PermissionsResponse extends BaseResponse {

private List<PermissionPOJO> role;
	
	public PermissionsResponse(){
		super();
	}

	public List<PermissionPOJO> getRole() {
		return role;
	}

	public void setRole(List<PermissionPOJO> role) {
		this.role = role;
	}
	
}

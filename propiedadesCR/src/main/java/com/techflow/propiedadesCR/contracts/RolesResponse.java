package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.RolePOJO;

public class RolesResponse extends BaseResponse {
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

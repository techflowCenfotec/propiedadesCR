package com.techflow.propiedadesCR.contracts;
import com.techflow.propiedadesCR.pojo.RolePOJO;

public class RolesRequest extends BaseRequest{
	
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

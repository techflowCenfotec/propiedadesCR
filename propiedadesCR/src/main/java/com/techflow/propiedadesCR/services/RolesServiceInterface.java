package com.techflow.propiedadesCR.services;

import java.util.List;
import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.pojo.RolePOJO;

public interface RolesServiceInterface {

	List<RolePOJO> getAll(RolesRequest rs);
	List<RolePOJO> getRoleAndPermissions(RolesRequest rs);
	//List<RolePOJO> getAllByName(RolesRequest rs);
	Boolean saveRole(RolesRequest rs);
}

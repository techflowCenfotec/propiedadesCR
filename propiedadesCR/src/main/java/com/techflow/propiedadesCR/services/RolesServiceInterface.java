package com.techflow.propiedadesCR.services;

import java.util.List;
import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.pojo.RolePOJO;

public interface RolesServiceInterface {

	List<RolePOJO> getAll(RolesRequest rs);
	List<RolePOJO> getRoleAndPermissions(RolesRequest rs);
	Trole saveRole(RolesRequest rs);
	Trole modifyRole(RolesRequest roleRequest);
}

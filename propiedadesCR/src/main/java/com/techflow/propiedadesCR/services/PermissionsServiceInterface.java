package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PermissionsRequest;
import com.techflow.propiedadesCR.pojo.PermissionPOJO;

public interface PermissionsServiceInterface {
	List<PermissionPOJO> getAll(PermissionsRequest ps);
}

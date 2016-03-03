package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.PermissionsRequest;
import com.techflow.propiedadesCR.ejb.Tpermission;
import com.techflow.propiedadesCR.pojo.PermissionPOJO;
import com.techflow.propiedadesCR.repositories.PermissionsRepository;

@Service
public class PermissionsService implements PermissionsServiceInterface {
	@Autowired private PermissionsRepository permissionsRepository;
	
	@Override
	@Transactional
	public List<PermissionPOJO> getAll(PermissionsRequest ppermissionsRequest){
		List<Tpermission> permissions = permissionsRepository.findAll();
		List<PermissionPOJO> dtos = new ArrayList<PermissionPOJO>();
		permissions.stream().forEach(ta ->{
			PermissionPOJO dto = new PermissionPOJO();
			BeanUtils.copyProperties(ta, dto);
			dtos.add(dto);
		});
		return dtos;
	}
}

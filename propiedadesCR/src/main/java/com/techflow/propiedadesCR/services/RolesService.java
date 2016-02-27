package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.pojo.PermissionPOJO;
import com.techflow.propiedadesCR.repositories.RoleRepository;


@Service
public class RolesService implements RolesServiceInterface {

	@Autowired private RoleRepository rolesRepository;
	
	@Override
	@Transactional
	public List<RolePOJO> getAll(RolesRequest rs) {
		List<Trole> roles = rolesRepository.findAll();
		return generateRoleData(roles);
	}
	
	private List<RolePOJO> generateRoleData(List<Trole> roles){
		List<RolePOJO> uiRoles = new ArrayList<RolePOJO>();
		roles.stream().forEach(r -> {
			RolePOJO dto = new RolePOJO();
			BeanUtils.copyProperties(r,dto);
			uiRoles.add(dto);
		});	
		return uiRoles;
	}
	
	@Override
	@Transactional
	public Boolean saveRole(RolesRequest rs) {
	
		Trole role = new Trole();
		BeanUtils.copyProperties(rs.getRole(), role);	
		Trole newRole = rolesRepository.save(role);
		
		return (newRole == null) ? false : true;
		
	}

	
	@Override
	@Transactional
	public List<RolePOJO> getPermissions(RolesRequest rs) {
		Trole role = rolesRepository.findOne(rs.getRole().getIdRol());
		List<RolePOJO> dtos = new ArrayList<RolePOJO>();
		RolePOJO dto = new RolePOJO();
		
		//initial permissions
		BeanUtils.copyProperties(role, dto);
		List<PermissionPOJO> permissions = new ArrayList<PermissionPOJO>();
		role.getTpermissions().stream().forEach(perm -> {
			PermissionPOJO permData = new PermissionPOJO();
			BeanUtils.copyProperties(perm,permData);
			permissions.add(permData);
		});
		dto.setPermissions(permissions); ;
		dtos.add(dto);
		return dtos;
	}
}
package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.ejb.Tpermission;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.pojo.PermissionPOJO;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.repositories.PermissionsRepository;
import com.techflow.propiedadesCR.repositories.RoleRepository;


@Service
public class RolesService implements RolesServiceInterface {

	@Autowired private RoleRepository rolesRepository;
	@Autowired private PermissionsRepository permissionsRepository;
	
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
			dto.setTpermissions(null);
			uiRoles.add(dto);
		});	
		return uiRoles;
	}
	
	
	@Override
	@Transactional
	public Boolean saveRole(RolesRequest proleRequest) {
		
		Trole role = new Trole();
		role.setNombreRol(proleRequest.getRole().getNombreRol());
		
		List<Tpermission> permissions = new ArrayList<Tpermission>();
		proleRequest.getRole().getTpermissions().stream().forEach(perm -> {
			Tpermission p = permissionsRepository.findOne(perm.getIdPermissions());
			permissions.add(p);
		});
		role.setTpermissions(permissions);
		Trole newRole = rolesRepository.save(role);
		
		
//	
//		RolePOJO role = new RolePOJO();
//		Trole trole= new Trole();
//		PermissionsRequest ppermissionsRequest= new PermissionsRequest();
//		BeanUtils.copyProperties(proleRequest.getRole(), role);	
//		
////		ArrayList<Tpermission> permissionsList= new ArrayList<Tpermission>();
////		proleRequest.getRole().getTpermissions().stream().forEach(perm -> {
////			Tpermission permData = new Tpermission();
////		
////				//permData= permissionsListObj.get(perm);
////			BeanUtils.copyProperties(perm,permData);
////			permissionsList.add(permData);
////		});
//		trole.setNombreRol(role.getNombreRol());
//		Trole newRole = rolesRepository.save(trole);
////		Trole role = new Trole();
////		BeanUtils.copyProperties(proleRequest.getRole(), role);	
////		Trole newRole = rolesRepository.save(role);
		return (newRole == null) ? false : true;
	}

	
	@Override
	@Transactional
	public List<RolePOJO> getRoleAndPermissions(RolesRequest proleRequest) {
		Trole role = rolesRepository.findOne(proleRequest.getRole().getId_Rol());
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
		dto.setTpermissions(permissions); ;
		dtos.add(dto);
		return dtos;
	}
}
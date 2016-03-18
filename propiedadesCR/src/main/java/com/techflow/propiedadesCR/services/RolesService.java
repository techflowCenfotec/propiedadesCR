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
/**
 * <h1>Servicio del rol</h1>
 * Clase encargada de ofrecer los servicios del rol
 * y administrar las transacciones al repositorio
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/26/2016
 */

@Service
public class RolesService implements RolesServiceInterface {
	/*
	 * Objeto que se comunica con la base de datos
	 */
	@Autowired private RoleRepository rolesRepository;
	/*
	 * Objeto que se comunica con la base de datos
	 */
	@Autowired private PermissionsRepository permissionsRepository;
	
	/**
	 * Método que levanta todos los roles del sistema
	 * @return generateRoleData Lista que contiene todos los roles del sistema
	 */
	@Override
	@Transactional
	public List<RolePOJO> getAll(RolesRequest prolesRequest) {
		List<Trole> roles = rolesRepository.findAll();
		List<Trole> correctRoles= new ArrayList<Trole>();
		roles.stream().forEach(role -> {
			Trole trole = rolesRepository.findOne(role.getIdRole());
			if(role.getActive()==1){
				correctRoles.add(trole);
			}
		});
		return generateRoleData(correctRoles);
	}
	
	/**
	 * Método que levanta todos los roles del sistema
	 * @param proles Lista de roles levantados por el repositorio
	 * @return uiRoles Lista de bancos del sistema
	 */
	private List<RolePOJO> generateRoleData(List<Trole> proles){
		List<RolePOJO> uiRoles = new ArrayList<RolePOJO>();
		proles.stream().forEach(role -> {
			RolePOJO roleDada = new RolePOJO();
			BeanUtils.copyProperties(role,roleDada);
			roleDada.setTpermissions(null);
			uiRoles.add(roleDada);
		});	
		return uiRoles;
	}
	
	
	@Override
	@Transactional
	/**
	 * Método que se encarga de almacenar los roles en el sistema
	 * @param proleRequest Parámetro que contiene información del objeto a almacenar
	 * @return newRole Devuelve el objeto Trole
	 */
	public Trole saveRole(RolesRequest proleRequest) {
		
		Trole role = new Trole();
		role.setRolName(proleRequest.getRole().getRolName());
		
		List<Tpermission> permissions = new ArrayList<Tpermission>();
		proleRequest.getRole().getTpermissions().stream().forEach(perm -> {
			Tpermission p = permissionsRepository.findOne(perm.getIdPermissions());
			permissions.add(p);
		});
		role.setTpermissions(permissions);
		Trole newRole = rolesRepository.save(role);
		
		return newRole;
	}

	/**
	 * Método que se encarga de levantar la consulta de  un role
	 * @param proleRequest Parámetro que contiene información del role 
	 * que se desea consultar
	 * @return roleDataList Lista que contiene el objeto a ser consultado
	 */
	@Override
	@Transactional
	public List<RolePOJO> getRoleAndPermissions(RolesRequest proleRequest) {
		Trole role = rolesRepository.findOne(proleRequest.getRole().getIdRole());
		List<RolePOJO> roleDataList = new ArrayList<RolePOJO>();
		RolePOJO roleData = new RolePOJO();
		
		//initial permissions
		BeanUtils.copyProperties(role, roleData);
		List<PermissionPOJO> permissions = new ArrayList<PermissionPOJO>();
		
		role.getTpermissions().stream().forEach(perm -> {
			PermissionPOJO permData = new PermissionPOJO();
			BeanUtils.copyProperties(perm,permData);
			permissions.add(permData);
		});
		roleData.setTpermissions(permissions); ;
		roleDataList.add(roleData);
		return roleDataList;
	}
	
	/**
	  * Este método modifica un rol en el sistema.
	  *
	  * @param proleRequest Contiene información del objeto a modificar.
      * 
	  * @return newRole Devuelve el rol modificado con sus nuevos datos.
	  */
	@Override
	@Transactional
	public Trole modifyRole(RolesRequest proleRequest) {
		
		Trole role = new Trole();
		role.setRolName(proleRequest.getRole().getRolName());
		role.setIdRole(proleRequest.getRole().getIdRole());
		
		List<Tpermission> permissions = new ArrayList<Tpermission>();
		proleRequest.getRole().getTpermissions().stream().forEach(perm -> {
			Tpermission p = permissionsRepository.findOne(perm.getIdPermissions());
			permissions.add(p);
		});
		role.setTpermissions(permissions);
		Trole newRole = rolesRepository. save(role);
		
		return newRole;
	
	}
	
	/**
	  * Este método elimina lógicamente un rol en el sistema.
	  *
	  * @param proleRequest Contiene información del objeto a eliminar.
      * 
	  * @return newRole Devuelve el rol eliminado con sus nuevos datos.
	  */
	
	public Trole deleteRole(RolesRequest proleRequest) {
		Trole role = new Trole();
		role.setActive((byte) 0);
		role.setRolName(proleRequest.getRole().getRolName());
		role.setIdRole(proleRequest.getRole().getIdRole());
		Trole newRole = rolesRepository.save(role);
		return newRole;
	}
}
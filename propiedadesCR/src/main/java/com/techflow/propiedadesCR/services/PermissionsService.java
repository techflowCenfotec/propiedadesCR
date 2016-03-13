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
/**
 * <h1>Servicio del permiso</h1>
 * Clase encargada de ofrecer los servicios del permiso
 * y administrar las transacciones al repositorio
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/29/2016
 */
@Service
public class PermissionsService implements PermissionsServiceInterface {
	/*
	 * Objeto que se comunica con la base de datos
	 */
	@Autowired private PermissionsRepository permissionsRepository;
	
	@Override
	@Transactional
	/**
	 * Método que levanta todos los permisos del sistema
	 * @return generateRoleData Lista que contiene todos los roles del sistema
	 */
	public List<PermissionPOJO> getAll(PermissionsRequest ppermissionsRequest){
		List<Tpermission> permissions = permissionsRepository.findAll();
		List<PermissionPOJO> permissionsDataList = new ArrayList<PermissionPOJO>();
		permissions.stream().forEach(permission ->{
			PermissionPOJO permissionData = new PermissionPOJO();
			BeanUtils.copyProperties(permission, permissionData);
			permissionsDataList.add(permissionData);
		});
		return permissionsDataList;
	}
}

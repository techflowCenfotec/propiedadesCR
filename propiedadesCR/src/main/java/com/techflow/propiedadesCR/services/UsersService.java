/**
* <h1>Servicio de usuarios</h1> 
* Esta clase  se encarga de comunicarse con el repositorio.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.security.*;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.PasswordRequest;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.UsersRepository;


@Service
public class UsersService implements UsersServiceInterface{
	
	/**	 
     * El objeto usersRepository es el que se encarga de la comunicación con la BD. 
     */
	@Autowired private UsersRepository usersRepository;
	

	/**
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param pusersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return uiUsers Retorna la respuesta del repositorio hacia el controlador.
	  */
	@Override
	@Transactional
	public List<UserPOJO> getAll(UsersRequest pusersRequest) {
		List<Tuser> users = usersRepository.findAll();
		return generateUserDtos(users);
	}
	

	/**
	  * Este método retorna todos los usuarios vendedores registrados en el sistema
	  *
	  * @param pusersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return uiUsers Retorna la respuesta del repositorio hacia el controlador.
	  */
	public List<UserPOJO> getAllVendors(UsersRequest pusersRequest) {
		Trole role = new Trole();
		role.setIdRole(3);
		List<Tuser> users = usersRepository.findAllByTrole(role);
		return generateUserDtos(users);
	}
	
	/**
	  * Este método genera los objetos POJOS que se retornaran a la UI.
	  *
	  * @param pusers Lista de usuarios.
	  *
	  * @return uiUsers Se retorna una lista de usuarios POJOS.
	  */
	private List<UserPOJO> generateUserDtos(List<Tuser> pusers) {
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		pusers.stream().forEach(u -> {
			Trole role =u.getTrole();			
			RolePOJO rolePOJO = new RolePOJO();
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(role, rolePOJO);
			BeanUtils.copyProperties(u, dto);
			dto.setRole(rolePOJO);
			uiUsers.add(dto);
		});
		return uiUsers;
	}
	
	/**
	  * Este método registra un usuario en el sistema.
	  *
	  * @param puserRequest Encapsula los datos requeridos por el usuario.
	  * @param pidRole Identificador del rol asignado al usuario
      * 
	  * @return nuser Retorna el usuario creado.
	  */
	@Override
	public Tuser saveUser(UsersRequest puserRequest, int pidRole) {
		
		Tuser user = new Tuser();
		Trole role = new Trole();
		role.setIdRole(pidRole);
		BeanUtils.copyProperties(puserRequest.getUser(), user);
		user.setTrole(role);
		StringBuffer md5password = new StringBuffer();
    	
        MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(puserRequest.getUser().getPassword().getBytes());
		        
		        byte byteData[] = messageDigest.digest();
		        //convert the byte to hex format method 1
		       
		        for (int i = 0; i < byteData.length; i++) {
		         md5password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		         user.setPassword(md5password.toString());
		        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tuser nuser = usersRepository.save(user);
		
		return nuser;
	
	}
	
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pidUser Identificador del usuario.
      * 
	  * @return userPOJO Retorna el usuario consultado.
	  */
	@Override
	public UserPOJO consultUser(int pidUser){
		
		Tuser nuser = usersRepository.findByIdUser(pidUser);
		UserPOJO userPOJO =null;
		
		if (null != nuser){
			Trole role =nuser.getTrole();			
			RolePOJO rolePOJO = new RolePOJO();
			BeanUtils.copyProperties(role, rolePOJO);
			userPOJO = new UserPOJO();
			userPOJO.setRole(rolePOJO);
			BeanUtils.copyProperties(nuser, userPOJO);
		}	
		return userPOJO;
		
	}
	
	/**
	  * Actualiza el usuario con la lista de propiedades. Retorna la entidad almacenada por si hay que realizar operaciones adicionales
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @param pUser Contiene la infomarción a almacenar a la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	  * 
	  * @return user Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tuser updateFavorites(Tuser pUser) {
		Tuser user =  usersRepository.save(pUser);
		return user;
	}
	
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pIdUser Identificador del usuario.
      * 
	  * @return nuser Retorna el usuario modificado.
	  */

	
	@Override
	public Tuser modifyUser(UsersRequest puserRequest, int pidRole) {
		
		Tuser user = new Tuser();
		Trole role = new Trole();
		role.setIdRole(pidRole);
		BeanUtils.copyProperties(puserRequest.getUser(), user);
		user.setTrole(role);
		Tuser nuser = usersRepository.save(user);
		
		return nuser;
	
	}

	@Override
	public Tuser getUserByEmail(String pemail){
		
		Tuser nuser = usersRepository.findUserByEmail(pemail);
		return nuser;
		
	}
	
	/**
	  * Este método modifica la contraseña de un usuario
	  *
	  * @param ppasswordRequest Encapsula los datos requeridos 
	  *	pora el cambio  de contraseña.
      * 
	  * @return nuser Retorna el usuario modificado.
	  */
	@Override
	public Tuser changePass(PasswordRequest ppasswordRequest){
		StringBuffer md5password = new StringBuffer();
		MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update( ppasswordRequest.getNewPass().getBytes());
			        
			        byte byteData[] = messageDigest.digest();
			        //convert the byte to hex format method 1
			       
			        for (int i = 0; i < byteData.length; i++) {
			         md5password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	
			        }
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		Tuser user = usersRepository.findByIdUser(ppasswordRequest.getId());
		
		user.setPassword(md5password.toString());
		Tuser modifiedUser = usersRepository.save(user);
	
		return modifiedUser;
		
	}
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pIdUser Identificador del usuario.
     * 
	  * @return Tuser Retorna el usuario consultado.
	  */
	@Override
	@Transactional
	public Tuser getUserByID(int pIdUser) {
		return usersRepository.findOne(pIdUser);

	}

	

}
